package ude.SocialMediaExplorer.analysis;

import static org.apache.uima.fit.util.JCasUtil.select;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.JCas;

import ude.SocialMediaExplorer.analysis.type.SenseAnno;
import ude.SocialMediaExplorer.analysis.type.SentimentAnno;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class SenseAnnotator extends JCasAnnotator_ImplBase{

	String newText;
	private ArrayList<Token> tokens;
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {

		List<Token> tagdesc = new ArrayList<Token>(select(aJCas, Token.class));

		tokens = new ArrayList<Token>(select(aJCas, Token.class));

		for (Token t : tokens) {
			
			String sense= getSense(t);
			
			if(sense!=null){
			SenseAnno annotation = new SenseAnno(aJCas);
			annotation.setBegin(t.getBegin());
			annotation.setEnd(t.getEnd());
			
			annotation.setSenseValue(sense);
			
			annotation.addToIndexes();
			}
		}

	}
	private String getSense(Token t) {
		if(t.getPos().getPosValue().equals("NN")||t.getPos().getPosValue().equals("NE")||t.getPos().getPosValue().equals("PR")){
			return t.getCoveredText();
	}
		else return null;
		}

}
