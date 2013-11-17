package languageProcessing;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.api.transform.type.SofaChangeAnnotation;
import de.tudarmstadt.ukp.dkpro.core.castransformation.ApplyChangesAnnotator;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpPosTagger;
import de.tudarmstadt.ukp.dkpro.core.textcat.LanguageIdentifier;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import de.tudarmstadt.ukp.dkpro.core.umlautnormalizer.UmlautNormalizer;

public class MostFrequentFinder {
	
	public List<String> test(String tweet)
	        throws Exception
	    {
		
		List<String> nouns= new ArrayList<String>();
		
	        AggregateBuilder builder = new AggregateBuilder();
	        builder.add(createEngineDescription(LanguageIdentifier.class));
	        builder.add(createEngineDescription(BreakIteratorSegmenter.class));
	        builder.add(createEngineDescription(OpenNlpPosTagger.class));

	        AnalysisEngine engine = builder.createAggregate();

	        JCas jcas = engine.newJCas();
	        jcas.setDocumentText(tweet);
//	        jcas.setDocumentLanguage("de");
	        DocumentMetaData.create(jcas);

	        engine.process(jcas);
//	        try {
//	            Iterator<JCas> jcasIterator = jcas.getViewIterator();
//	            while (jcasIterator.hasNext()) {
//	                JCas view = jcasIterator.next();
//	                System.out.println(view.getViewName());
//	                System.out.println(view.getDocumentText());
//	                System.out.println();
//	            }
//	        }
//	        catch (CASException e) {
//	            throw new AnalysisEngineProcessException(e);
//	        }
	        
	        FSIterator<AnnotationFS> annotationIterator = jcas.getCas().getAnnotationIndex().iterator();
            while (annotationIterator.hasNext()) {
                    AnnotationFS annotation = annotationIterator.next();
                   
                    try {
                    	int n=0;
                    	String[] annotationList=annotation.toString().split(" ");
//                    	if(annotationList[28].equals("NN")){
//                    		System.out.println("gefunden: [" + annotation.getCoveredText() + "]");
//                    	}
                    	for(String a: annotationList){
                    		
                    		if(n==56 && a.contains("NE")){
//                    			System.out.println("gefunden: [" + annotation.getCoveredText() + "]");
                    			nouns.add(annotation.getCoveredText());
                    			//System.out.println(n+" : "+a+"-----------------------------------------------");
                    		}
                    		n++;
                    	}
                    }
                    catch (IndexOutOfBoundsException e) {
                    	System.out.println("<OFFSETS OUT OF BOUNDS>");
                    }
            }
	     return nouns;
	    }
}
