package ude.SocialMediaExplorer.analysis;

import static org.apache.uima.fit.util.JCasUtil.select;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class SenseAnnotator extends JCasAnnotator_ImplBase{

	String newText;
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		
		try {
			JCas targetView = aJCas.createView("Sense");
			List<Token> tagdesc = new ArrayList<Token>(select(aJCas,Token.class));
			
			// Set document language
			targetView.setDocumentLanguage(aJCas.getDocumentLanguage());
			
	        for (Token t : tagdesc) {
	        	String sense=this.getSense(t);
	        	newText+=sense;
	        }
	        targetView.setDocumentText(newText);
		} catch (CASException e) {
			e.printStackTrace();
		}
	}
	private String getSense(Token t) {
		if(t.getPos().getPosValue().equals("NN")||t.getPos().getPosValue().equals("NE")||t.getPos().getPosValue().equals("PR")){
			return t.getCoveredText()+" ";
	}
		else return null;
		}

}
