package ude.SocialMediaExplorer.analysis;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.util.JCasUtil.select;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.junit.rules.TemporaryFolder;

import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.castransformation.ApplyChangesAnnotator;
import de.tudarmstadt.ukp.dkpro.core.io.bincas.BinaryCasWriter;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpPosTagger;
import de.tudarmstadt.ukp.dkpro.core.textcat.LanguageIdentifier;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import ude.SocialMediaExplorer.data.post.Post;
import ude.SocialMediaExplorer.data.post.PostList;
import ude.SocialMediaExplorer.data.utils.io.CASWriter;
import ude.SocialMediaExplorer.data.utils.time.TimeSpan;



public class Analysis extends Thread{

static SentimentLexicon lexEnglish = new SentimentLexicon("En");
static SentimentLexicon lexGerman= new SentimentLexicon("De");
	private PostList postList;
	
	private List<JCas> tweetCases;
	private CASWriter casWriter;


	public Analysis(PostList postList) {
		this.postList=postList;
		tweetCases= new ArrayList<JCas>();
	}
	
	public void run() {
		casWriter= new CASWriter();
	
//		System.out.println(lexEnglish.getSentiment("abnormal", "En"));
		
//		System.out.println(lexGerman.getSentiment("erzürnte", "De"));
		try {
			// adding information per tweet
			for (Post p : postList){
				tweetCases.add(analyzeTweet(p));
			}
			// form clusters

			for (JCas jcas : tweetCases){
				casWriter.write(jcas);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JCas analyzeTweet(Post p) throws AnalysisEngineProcessException, ResourceInitializationException, CASException {
		
        AggregateBuilder builder = new AggregateBuilder();
        builder.add(createEngineDescription(LanguageIdentifier.class));
        builder.add(createEngineDescription(BreakIteratorSegmenter.class));
        builder.add(createEngineDescription(OpenNlpPosTagger.class));
        builder.add(createEngineDescription(SentimentAnnotator.class));
        builder.add(createEngineDescription(SenseAnnotator.class));
        
        AnalysisEngine engine = builder.createAggregate();

        JCas jcas = engine.newJCas();
        
        jcas.setDocumentText(p.getMessage());

        DocumentMetaData.create(jcas);
        //TODO ID aus Tweet generieren
        DocumentMetaData.get(jcas).setDocumentId(p.getId());
        
        engine.process(jcas);
        
        System.out.println("Original text: "+jcas.getDocumentText());
        FSIterator<AnnotationFS> annotationIterator = jcas.getCas().getAnnotationIndex().iterator();
        while (annotationIterator.hasNext()) {
                AnnotationFS annotation = annotationIterator.next();
                System.out.println("[" + annotation.getCoveredText() + "]");
                System.out.println(annotation.toString());
        }
//        System.out.println("Sentiments : "+jcas.getView("Sentiment").getDocumentText());
//        System.out.println("Senses: "+jcas.getView("Sense").getDocumentText());
        
        return jcas;
	}

}