package ude.SocialMediaExplorer.analysis;

import static org.apache.uima.fit.util.JCasUtil.select;
import static de.tudarmstadt.ukp.dkpro.core.castransformation.ApplyChangesAnnotator.OP_DELETE;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import ude.SocialMediaExplorer.analysis.type.ArktweetAnno;
import ude.SocialMediaExplorer.analysis.type.SentimentAnno;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.TagsetDescription;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.api.transform.type.SofaChangeAnnotation;

public class ArktweetAnnotator extends JCasAnnotator_ImplBase{

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		
		
		Sentence seg = new Sentence(aJCas, 0, aJCas.getDocumentText().length());
		seg.addToIndexes(aJCas);
		
		List<Token> tokens = new ArrayList<Token>(select(aJCas, Token.class));
		for (Token t : tokens){
			System.out.println("ARK-Tokens "+t.getPos().getCoveredText()+" value "+t.getPos().getPosValue());
			if(t.getPos().getPosValue().equals("U")||t.getPos().getPosValue().equals("E")||t.getPos().getPosValue().equals("@")||t.getCoveredText().equals("RT")){
				//Annotate
				System.out.println("Arktweet: "+t.getPos().getCoveredText());
				ArktweetAnno annotation = new ArktweetAnno(aJCas);
	        	annotation.setBegin(t.getBegin());
	        	annotation.setEnd(t.getEnd());
	        	
	        	annotation.setLinkOrEmoticon(t.getPos());
	        	
	        	annotation.addToIndexes();
			}
		
		}
		List<AnnotationFS> pos = new ArrayList<AnnotationFS>(select(aJCas, POS.class));
		for(AnnotationFS p : pos){
			aJCas.removeFsFromIndexes( p);
		}

		
		DocumentMetaData.get(aJCas).setLanguage("de");
	}

}
