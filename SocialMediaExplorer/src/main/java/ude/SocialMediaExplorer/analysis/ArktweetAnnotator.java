package ude.SocialMediaExplorer.analysis;

import static org.apache.uima.fit.util.JCasUtil.select;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class ArktweetAnnotator extends JCasAnnotator_ImplBase{

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		List<Token> tokens = new ArrayList<Token>(select(aJCas, Token.class));
		for (Token t : tokens){
//			System.out.println("ARK-Tokens "+t.getPos().getCoveredText()+" value "+t.getPos().getPosValue());
			if(t.getPos().getPosValue().equals("U")){
				System.out.println(t.getPos().getCoveredText());
			}
//			t.getPos().removeFromIndexes();
//			t.getPos().removeFromIndexes(aJCas);
		}
		aJCas.getAnnotationIndex(POS.type).iterator().next().removeFromIndexes();
		DocumentMetaData.get(aJCas).setLanguage("de");
	}

}
