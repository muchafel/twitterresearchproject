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
import org.apache.uima.cas.CASException;
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
import de.tudarmstadt.ukp.dkpro.core.api.transform.type.SofaChangeAnnotation;
import de.tudarmstadt.ukp.dkpro.core.umlautnormalizer.UmlautNormalizer;

public class SentimentAnnotator extends JCasAnnotator_ImplBase{

	
String newText;

	@Override
	public void process(JCas cas) throws AnalysisEngineProcessException {
		try {
			JCas targetView = cas.createView("Sentiment");
			List<Token> tagdesc = new ArrayList<Token>(select(cas,Token.class));
			
			// Set document language
			targetView.setDocumentLanguage(cas.getDocumentLanguage());
			
	        for (Token t : tagdesc) {
	        	String sentiment=this.getSentiment(t);
	        	newText+=sentiment;
	        }
	        targetView.setDocumentText(newText);
		} catch (CASException e) {
			e.printStackTrace();
		}
	}
	private String getSentiment(Token t) {
		double a= Math.random();
		return " "+String.valueOf(a);
	}
}
		
	