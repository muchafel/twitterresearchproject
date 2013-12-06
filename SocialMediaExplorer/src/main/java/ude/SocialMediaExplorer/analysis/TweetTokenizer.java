package ude.SocialMediaExplorer.analysis;

import java.text.BreakIterator;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import cmu.arktweetnlp.Twokenize;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.SegmenterBase;

public class TweetTokenizer extends SegmenterBase {

	@Override
	protected void process(JCas aJCas, String text, int zoneBegin)
			throws AnalysisEngineProcessException {
		
		// Twokenize tweetTwokenize= new Twokenize();
		List<String> resultingTokens = Twokenize.tokenizeRawTweetText(aJCas
				.getDocumentText());

		int end=zoneBegin;
		int begin= zoneBegin;
		Annotation segment = createSentence(aJCas, 0, aJCas.getDocumentText().length());
		
		for(int i=0;i<resultingTokens.size();i++){
			if(i == resultingTokens.size()-1){
				end+=resultingTokens.get(i).length();
			}
			else{
				end+=resultingTokens.get(i).length()+1;
			}
		
			Annotation token = createToken(aJCas, begin, end);
//			token.addToIndexes();
			System.out.println(resultingTokens.get(i)+" start: "+begin+" end: "+end+" token: '"+token.getCoveredText()+"'");
			begin=end;
		}
//		
		
	}

}
