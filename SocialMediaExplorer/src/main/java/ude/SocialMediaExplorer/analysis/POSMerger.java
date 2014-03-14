package ude.SocialMediaExplorer.analysis;

import static org.apache.uima.fit.util.JCasUtil.select;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import ude.SocialMediaExplorer.analysis.type.ArktweetAnno;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class POSMerger extends JCasAnnotator_ImplBase {
/**
 * this class merges the Arktweet-POS tags with the OpenNLP-POS tags
 * this step is necessary because the Arktweet Tagger is only trained for english data. So the Arktweet-Annotation
 * is useless for the german tweets except for links, emoticons, etc (they are language independet).
 */
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		AnnotationIndex<Annotation> index=aJCas.getAnnotationIndex(ArktweetAnno.type);
		Iterator<Annotation> iterator= index.iterator();
		while(iterator.hasNext()){
			ArktweetAnno anno= (ArktweetAnno) iterator.next();
//			System.out.println("Merge: "+anno.getCoveredText());
			List<Token> tokens = new ArrayList<Token>(select(aJCas, Token.class));
			for (Token t : tokens){
				if(t.getCoveredText().equals(anno.getCoveredText())){
					//if a token is tagged as emoticon or a link it's POS is replaced with the Arktweet-Annotation
					t.setPos(anno.getLinkOrEmoticon());
					t.getPos().addToIndexes();
				}
			}
			
		}
	}

}
