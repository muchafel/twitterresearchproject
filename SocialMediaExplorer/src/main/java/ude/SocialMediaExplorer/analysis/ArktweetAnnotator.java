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
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.api.transform.type.SofaChangeAnnotation;

public class ArktweetAnnotator extends JCasAnnotator_ImplBase{

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		List<Token> tokens = new ArrayList<Token>(select(aJCas, Token.class));
		for (Token t : tokens){
//			System.out.println("ARK-Tokens "+t.getPos().getCoveredText()+" value "+t.getPos().getPosValue());
			if(t.getPos().getPosValue().equals("U")||t.getPos().getPosValue().equals("#")){
				//Annotate
				System.out.println("Arktweet: "+t.getPos().getCoveredText());
				ArktweetAnno annotation = new ArktweetAnno(aJCas);
	        	annotation.setBegin(t.getBegin());
	        	annotation.setEnd(t.getEnd());
	        	
	        	annotation.setLinkOrHash(t.getPos().getPosValue()+"_ark");
	        	
	        	annotation.addToIndexes();
			}
		
		}
		List<FeatureStructure> pos = new ArrayList<FeatureStructure>(select(aJCas, POS.class));
		for(FeatureStructure p : pos){
			aJCas.removeFsFromIndexes( p);
		}

		List<POS> pos3 = new ArrayList<POS>(select(aJCas, POS.class));
        for(POS p : pos3){
              p.removeFromIndexes();
        }

		List<Annotation> pos2 = new ArrayList<Annotation>(select(aJCas, POS.class));
		for(Annotation p : pos2){
			p.removeFromIndexes();
		}
		
		AnnotationIndex<Annotation> index=aJCas.getAnnotationIndex(POS.type);
		Iterator<Annotation> iterator= index.iterator();
		while(iterator.hasNext()){
			Annotation anno= iterator.next();
			//anno.removeFromIndexes();
		}
		
		DocumentMetaData.get(aJCas).setLanguage("de");
	}

}
