package ude.SocialMediaExplorer.analysis;

import static org.apache.uima.fit.util.JCasUtil.select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;

import ude.SocialMediaExplorer.analysis.type.CleanedSenseAnno;
import ude.SocialMediaExplorer.analysis.type.SenseAnno;
import ude.SocialMediaExplorer.analysis.type.SentimentAnno;
import ude.SocialMediaExplorer.analysis.type.SimpleSenseAnno;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class SenseAnnotator extends JCasAnnotator_ImplBase{

	String newText;
	private ArrayList<Token> tokens;
	private List<String> keyPhrases;
	private List<String> raw;
	private List<String> pos;
	private SenseSlidingWindow senseSlidingWindow;
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {

		raw= new ArrayList<String>();
		pos=new ArrayList<String>();
		senseSlidingWindow= new SenseSlidingWindow();
		
		List<CleanedSenseAnno> cleanedSenses = new ArrayList<CleanedSenseAnno>(select(aJCas, CleanedSenseAnno.class));
		Map <String, String> cleanedSensesMap = new HashMap<String,String>();
		for (CleanedSenseAnno sense : cleanedSenses){
			cleanedSensesMap.put(sense.getCoveredText(), sense.getCleanedSense());
		}
		tokens = new ArrayList<Token>(select(aJCas, Token.class));
		for (Token t : tokens){
//			System.out.println("-----------------------------------------"+t.getCoveredText());
			//if the token contains a cleaned sense the covered Text is replaced by the cleaned sense
			if(cleanedSensesMap.containsKey(t.getCoveredText())){
				raw.add(cleanedSensesMap.get(t.getCoveredText()));
			}
			else{
				raw.add(t.getCoveredText());
			}
			
			pos.add(t.getPos().getPosValue());
		}
		keyPhrases=senseSlidingWindow.getKeyPhrases(raw,pos);
		System.out.println("Cooccurence keyphrases: "+keyPhrases);
		for (Token t : tokens) {
			
			String sense= null;
			for(String phrase :keyPhrases){
				if (cleanedSensesMap.containsKey(t.getCoveredText())) {
					if (phrase.contains(cleanedSensesMap.get(t.getCoveredText()))) {
						sense = phrase;
					}
				}
				
			}
			
			if(sense!=null){
			SenseAnno annotation = new SenseAnno(aJCas);
			annotation.setBegin(t.getBegin());
			annotation.setEnd(t.getEnd());
			
			annotation.setSenseValue(sense);
			
			annotation.addToIndexes();
			}
		}

	}
//	private String getSense(Token t) {
//		if(t.getPos().getPosValue().equals("NN")||t.getPos().getPosValue().equals("NE")||t.getPos().getPosValue().equals("PR")){
//			return t.getCoveredText();
//	}
//		else return null;
//		}

}
