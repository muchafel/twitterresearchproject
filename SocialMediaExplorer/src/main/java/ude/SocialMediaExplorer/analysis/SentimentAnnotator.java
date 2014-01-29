package ude.SocialMediaExplorer.analysis;

import static org.apache.uima.fit.util.JCasUtil.select;

import java.util.ArrayList;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import ude.SocialMediaExplorer.analysis.type.SentimentAnno;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import static ude.SocialMediaExplorer.analysis.Analysis.lexEnglish;
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
        	
        	annotation.setSentimentValue(Boolean.toString(isPositiv(t,language)));
        	
        	annotation.addToIndexes();
        }
	}
	private boolean isPositiv(Token t,String language) {
		if(language.equals("De")){
			if(lexGerman.getSentiment(t.getCoveredText(), language)==1){
				System.out.println("positive sentiment: "+t.getCoveredText());
				return true;
			}
			else if(lexGerman.getSentiment(t.getCoveredText(), language)==-1){
				System.out.println("negative sentiment: "+t.getCoveredText());
				return false;
			}
		}
		if(language.equals("En")){
			if(lexEnglish.getSentiment(t.getCoveredText(), language)==1){
				return true;
			}
			else if(lexEnglish.getSentiment(t.getCoveredText(), language)==-1){
				return false;
			}
		}
		return false;
	}

}
