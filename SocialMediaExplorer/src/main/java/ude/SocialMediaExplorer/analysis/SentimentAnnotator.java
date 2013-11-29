package ude.SocialMediaExplorer.analysis;

import static org.apache.uima.fit.util.JCasUtil.select;

import java.util.ArrayList;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import ude.SocialMediaExplorer.type.SentimentAnno;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class SentimentAnnotator extends JCasAnnotator_ImplBase{
private ArrayList<Token> tokens;
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		
		tokens = new ArrayList<Token>(select(aJCas,Token.class));
        for (Token t : tokens){
        	SentimentAnno annotation = new SentimentAnno(aJCas);
        	annotation.setBegin(t.getBegin());
        	annotation.setEnd(t.getEnd());
        	annotation.setSentimentValue(Boolean.toString(isPositiv(t)));
        	annotation.addToIndexes();
        	
        }
		
	}
	private boolean isPositiv(Token t) {
		
		return false;
	}

}
