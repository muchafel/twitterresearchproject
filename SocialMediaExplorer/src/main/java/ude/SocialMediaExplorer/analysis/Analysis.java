package ude.SocialMediaExplorer.analysis;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.castransformation.ApplyChangesAnnotator;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpPosTagger;
import de.tudarmstadt.ukp.dkpro.core.textcat.LanguageIdentifier;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import ude.SocialMediaExplorer.data.model.Post;
import ude.SocialMediaExplorer.data.model.PostList;
import ude.SocialMediaExplorer.data.providing.DataProviding;
import ude.SocialMediaExplorer.data.providing.stored.TwitterJSONFileReader;
import ude.SocialMediaExplorer.data.utils.time.TimeSpan;



public class Analysis extends Thread{

	private PostList postList;
	private List<JCas> tweetCases;

	public Analysis(PostList postList) {
		this.postList=postList;
		tweetCases= new ArrayList<JCas>();
	}
	
	public void run() {
		
		try {
			for (Post p : postList){
				tweetCases.add(analyzeTweet(p));
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

        engine.process(jcas);
        
        System.out.println(jcas.getDocumentText());
        System.out.println(jcas.getView("Sentiment").getDocumentText());
        System.out.println(jcas.getView("Sense").getDocumentText());
        
        return jcas;
	}

}