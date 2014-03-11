package ude.SocialMediaExplorer.analysis;

import static org.apache.uima.fit.util.JCasUtil.select;

import java.util.ArrayList;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import ude.SocialMediaExplorer.analysis.type.SentimentAnno;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import static ude.SocialMediaExplorer.analysis.Analysis.lexGerman;;

public class SentimentAnnotator extends JCasAnnotator_ImplBase{
private ArrayList<Token> tokens;
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		tokens = new ArrayList<Token>(select(aJCas,Token.class));
		String language= aJCas.getDocumentLanguage();
        for (Token t : tokens){
        	SentimentAnno annotation = new SentimentAnno(aJCas);
        	annotation.setBegin(t.getBegin());
        	annotation.setEnd(t.getEnd());
        	annotation.setSentimentValue(isPositiv(t,language));
        	annotation.addToIndexes();
        }
	}
	private double isPositiv(Token t,String language) {
		if(language.equals("de")){
			return lexGerman.getSentiment(t.getCoveredText(), language);
		}
		if(language.equals("en")){
			return 0.0;
		}
		return 0.0;
	}

}
