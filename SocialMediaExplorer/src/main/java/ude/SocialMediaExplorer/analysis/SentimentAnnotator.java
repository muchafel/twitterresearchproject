package ude.SocialMediaExplorer.analysis;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;
import static org.apache.uima.fit.util.JCasUtil.toText;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.analysis_component.AnalysisComponent_ImplBase;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.AbstractCas;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureStructureImpl;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.tcas.Annotation_Type;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.TagsetDescription;
import de.tudarmstadt.ukp.dkpro.core.api.resources.MappingProvider;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class SentimentAnnotator extends JCasAnnotator_ImplBase{

	


	@Override
	public void process(JCas cas) throws AnalysisEngineProcessException {
		JCas jcas = (JCas) cas;
		FSIterator<AnnotationFS> annotationIterator = jcas.getCas()
				.getAnnotationIndex().iterator();
		while (annotationIterator.hasNext()) {
			AnnotationFS annotation = annotationIterator.next();
			if (annotation.getType().getShortName().equals("NE")) {
				System.out.println("Token " + annotation.getCoveredText()
						+ " Typ: " + annotation.getType().getName());

			}
		}
		
		List<Token> tagdesc = new ArrayList<Token>(select(jcas,Token.class));
        for (Token t : tagdesc) {
        	
			Sentiment sen = new Sentiment(jcas);
			sen.setBegin(t.getBegin());
			sen.setEnd(t.getEnd());
			sen.setPositive(true);
			sen.addToIndexes();
//			System.out.println(sen.getCoveredText());

		}
        tagdesc = new ArrayList<Token>(select(jcas,Token.class));
		for(Token des : tagdesc){
			
			System.out.println(des.getCoveredText()+" : "+des.getPos().getPosValue());
			if(des.getPos().getPosValue().equals("NN")||des.getPos().getPosValue().equals("NE")||des.getPos().getPosValue().equals("PR")){
				System.out.println(des.getCoveredText()+" : "+des.getPos().getPosValue());
				
			   }
			}
		
	
	}
		
	}
		
	