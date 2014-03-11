package ude.SocialMediaExplorer.analysis;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.arktools.ArktweetTagger;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpPosTagger;
import ude.SocialMediaExplorer.Config;
import ude.SocialMediaExplorer.data.post.Post;
import ude.SocialMediaExplorer.data.post.PostList;
import ude.SocialMediaExplorer.data.utils.io.CASWriter;
import ude.SocialMediaExplorer.data.utils.time.TimeStamp;


public class Analysis extends Thread {

	static SentimentLexicon lexEnglish = new SentimentLexicon( "en" );
	static SentimentLexicon lexGerman = new SentimentLexicon( "de" );
	private PostList postList;

	private List<JCas> tweetCases;
	private CASWriter casWriter;
	private SimpleDateFormat format = new SimpleDateFormat( "yyyyMMddHHmmss" );
	private String dateToday = "";
	private Date dateOfLastAnalysis = null;


	public Analysis( PostList postList ) {
		this.postList = postList;
		tweetCases = new ArrayList<JCas>(); // tweetCases nicht neu erzeugen, sondern serialisierte einlesen und dann nur die neuen Analysen hinzufügen
	}
	/**
	 * this method calls the analysis steps:
	 * 1. just calculate new tweets (checking the date of the last analysis)
	 * 2. perform analyzePost() foreach post
	 * 3. safe results
	 */
	public void run( String hashtagToAnalyze ) {
		casWriter = new CASWriter();
		try {
			dateOfLastAnalysis = format.parse( Config.readValueFromXMLFile( "lastAnalysis" ) );
		}
		catch ( ParseException e1 ) {
			e1.printStackTrace();
		}
		try {
			// adding information per tweet
			for ( Post p : postList ) {
				// nur Posts analysieren und als Cas hinzufügen, die neuer als letzte Analyse sind
				if ( p.getDate().after( dateOfLastAnalysis ) ) {
					tweetCases.add( analyzePost( p ) );
				}
			}
			// safes the calculated CASes using a CASWriter
			for ( JCas jcas : tweetCases ) {
				casWriter.write( jcas, hashtagToAnalyze );
			}
			// update date of last analysis
			dateToday = format.format( new Date() );
			Config.updateXMLValue( "lastAnalysis", dateToday );
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	/**
	 * this method enriches the posts with additional information:
	 * 1. Segmentation and twitter-specific POS-tagging with the ArktweetTagger
	 * 2. POS-tagging with the OpenNLPPOSTagger 
	 * 3. POS-merging
	 * 4. SentimentTagging
	 * 5. simple Sense-Annotation that marks NNs, NEs and ADJs to speed up the following clustering process 
	 */
	private JCas analyzePost( Post p ) throws AnalysisEngineProcessException, ResourceInitializationException,
			CASException {

		AggregateBuilder builder = new AggregateBuilder();
		builder.add( createEngineDescription( ArktweetTagger.class, ArktweetTagger.PARAM_VARIANT, "default" ) );
		builder.add( createEngineDescription( ArktweetAnnotator.class ) );
		builder.add( createEngineDescription( OpenNlpPosTagger.class ) );
		builder.add( createEngineDescription( POSMerger.class ) );
		builder.add( createEngineDescription( SentimentAnnotator.class ) );
		builder.add( createEngineDescription( SimpleSenseAnnotator.class ) );

		AnalysisEngine engine = builder.createAggregate();

		JCas jcas = engine.newJCas();

		jcas.setDocumentText( p.getMessage() );

		DocumentMetaData.create( jcas );
		//setting the document ID through the date and the id integer of the post
		DocumentMetaData.get( jcas ).setDocumentId(TimeStamp.createLong(p.getDate()) + "_" + p.getId());
		
		//at first the language is set to english (because o the arktweet-tagger). The ArktweetAnnotator sets the Language to german
		// in order to get a useful result from the OpenNLP-POS-tagger
		DocumentMetaData.get( jcas ).setLanguage( "en" );

		engine.process( jcas );

		return jcas;
	}

}