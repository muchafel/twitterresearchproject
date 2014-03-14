package ude.SocialMediaExplorer.analysis;

import static org.apache.uima.fit.util.JCasUtil.select;

import java.util.ArrayList;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import ude.SocialMediaExplorer.analysis.type.SentimentAnno;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import static ude.SocialMediaExplorer.analysis.Analysis.lexGerman;;

/**
 * this class annotates the tokens with a sentiment that's retrieved from a sentiment lexicon
 *
 */
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
	/**
	 * lookup method from the sentiment lexicon
	 * @param token (Token)
	 * @param language (String)
	 * @return sentiment (double)
	 */
	private double isPositiv(Token t,String language) {
		if(language.equals("de")){
			//gets the specific sentiment of word from the sentiment lexicon
			return lexGerman.getSentiment(t.getCoveredText(), language);
		}
		// TODO add a sentimentlexicon for english
		if(language.equals("en")){
			return 0.0;
		}
		return 0.0;
	}

}
