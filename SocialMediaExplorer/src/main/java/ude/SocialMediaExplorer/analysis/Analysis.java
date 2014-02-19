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



public class Analysis extends Thread{

static SentimentLexicon lexEnglish = new SentimentLexicon("en");
static SentimentLexicon lexGerman= new SentimentLexicon("de");
	private PostList postList;
	
	private List<JCas> tweetCases;
	private CASWriter casWriter;
	private SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
	private String dateToday = "";
	private Date dateOfLastAnalysis = null;


	public Analysis(PostList postList) {
		this.postList=postList;
		tweetCases= new ArrayList<JCas>(); // tweetCases nicht neu erzeugen, sondern serialisierte einlesen und dann nur die neuen Analysen hinzufügen
	}
	
	public void run(String hashtagToAnalyze) {
		casWriter= new CASWriter();
		try {
			dateOfLastAnalysis = format.parse(Config.readValueFromXMLFile("lastAnalysis"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
//		System.out.println(lexEnglish.getSentiment("abnormal", "En"));
//		System.out.println(lexGerman.getSentiment("erzürnte", "De"));
		try {
			// adding information per tweet
			for (Post p : postList){
				// nur Posts analysieren und als Cas hinzufügen, die neuer als letzte Analyse sind
				if (p.getDate().after(dateOfLastAnalysis)) {
					tweetCases.add(analyzeTweet(p));
				}
			}
			// form clusters

			for (JCas jcas : tweetCases){
				casWriter.write(jcas,hashtagToAnalyze);
			}
			
			// update date of last analysis
			dateToday = format.format(new Date());
			Config.updateXMLValue("lastAnalysis", dateToday);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JCas analyzeTweet(Post p) throws AnalysisEngineProcessException, ResourceInitializationException, CASException {
		
        AggregateBuilder builder = new AggregateBuilder();
//        builder.add(createEngineDescription(LanguageIdentifier.class));
//        builder.add(createEngineDescription(TweetTokenizer.class));
//       // builder.add(createEngineDescription(BreakIteratorSegmenter.class));
//        builder.add(createEngineDescription(OpenNlpPosTagger.class));
        builder.add(createEngineDescription(ArktweetTagger.class,ArktweetTagger.PARAM_VARIANT, "default"));
        builder.add(createEngineDescription(ArktweetAnnotator.class));
        builder.add(createEngineDescription(OpenNlpPosTagger.class));
        builder.add(createEngineDescription(POSMerger.class));
        builder.add(createEngineDescription(SentimentAnnotator.class));
        builder.add(createEngineDescription(SimpleSenseAnnotator.class));
        
        AnalysisEngine engine = builder.createAggregate();

        JCas jcas = engine.newJCas();
        
        jcas.setDocumentText(p.getMessage());

        DocumentMetaData.create(jcas);
        //TODO ID aus Tweet generieren
        DocumentMetaData.get(jcas).setDocumentId(p.getId());
        System.out.println("ID: --------"+p.getId());
        DocumentMetaData.get(jcas).setLanguage("en");
        
        engine.process(jcas);
        
        System.out.println("Original text: "+jcas.getDocumentText());
        
        //SYSO
//        FSIterator<AnnotationFS> annotationIterator = jcas.getCas().getAnnotationIndex().iterator();
//        while (annotationIterator.hasNext()) {
//                AnnotationFS annotation = annotationIterator.next();
//                System.out.println("[" + annotation.getCoveredText() + "]");
//                System.out.println(annotation.toString());
//        }
        
        return jcas;
	}

}