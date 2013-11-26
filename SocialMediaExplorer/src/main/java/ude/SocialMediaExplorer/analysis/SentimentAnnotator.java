package ude.SocialMediaExplorer.analysis;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;
import static org.apache.uima.fit.util.JCasUtil.toText;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.analysis_component.AnalysisComponent_ImplBase;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.AbstractCas;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureStructureImpl;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.tcas.Annotation_Type;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.TagsetDescription;
import de.tudarmstadt.ukp.dkpro.core.api.resources.MappingProvider;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.api.transform.type.SofaChangeAnnotation;
import de.tudarmstadt.ukp.dkpro.core.umlautnormalizer.UmlautNormalizer;



public class SentimentAnnotator extends JCasAnnotator_ImplBase{

	
String newText;
List<Token> tagdesc;

	@Override
	public void process(JCas cas) throws AnalysisEngineProcessException {
		try {
			
			
			JCas targetView = cas.createView("Sentiment");
			tagdesc = new ArrayList<Token>(select(cas,Token.class));
			
			// Set document language
			targetView.setDocumentLanguage(cas.getDocumentLanguage());
			int i=0;
	        for (Token t : tagdesc) {
	        	String sentiment;
	        	//1.get Phrase
	        	String phrase=getPhrase(t,i);
	        	if(phrase != null){
	        		//2.get SO : '+' for positive and '-' for negative
	        		System.out.println("found phrase: "+phrase);
	        		sentiment=this.getSentiment(phrase);
	        	}
	        	else{
	        		sentiment=null;
	        	}
	        	newText+=sentiment;
	        	i++;
	        }
	        targetView.setDocumentText(newText);
		} catch (CASException e) {
			e.printStackTrace();
		}
	}
	//calculates Phrase containing adverbs or adjectives
	private String getPhrase(Token t, int i) {
		//check for candidates else return null
		if (t.getPos().getPosValue().equals("NN")
				|| t.getPos().getPosValue().equals("NNS")
				|| t.getPos().getPosValue().equals("ADJA")
				|| t.getPos().getPosValue().equals("RB")
				|| t.getPos().getPosValue().equals("RBR")
				|| t.getPos().getPosValue().equals("RBS")
				|| t.getPos().getPosValue().equals("JJ")) {
			// if NN or NNS is followed by ADJA
			if ((t.getPos().getPosValue().equals("NN") || t.getPos()
					.getPosValue().equals("NNS"))
					&& tagdesc.get(i + 1).getPos().getPosValue().equals("ADJA")) {
				return t.getCoveredText() + " "+tagdesc.get(i + 1).getCoveredText();
			}
			// if ADJ is followed by NN or NNS OR ADJ
			if (t.getPos().getPosValue().equals("ADJ")
					&& (tagdesc.get(i + 1).getPos().getPosValue().equals("NNS"))
					|| tagdesc.get(i + 1).getPos().getPosValue().equals("NN")) {
//				String a=t.getCoveredText() ;
//				String b=tagdesc.get(i + 1).getCoveredText();
				return t.getCoveredText() + " "+ tagdesc.get(i + 1).getCoveredText();
			}
			// if ADJ is followed by ADJ
			if (t.getPos().getPosValue().equals("ADJ")
					&& tagdesc.get(i + 1).getPos().getPosValue().equals("ADJA")) {
				return t.getCoveredText() + " "+ tagdesc.get(i + 1).getCoveredText();
			}
			// if RB,RBS,RBR is folledw by ADJA
			if ((t.getPos().getPosValue().equals("RB")
					|| t.getPos().getPosValue().equals("RBR") || t.getPos()
					.getPosValue().equals("RBS"))
					&& tagdesc.get(i + 1).getPos().getPosValue().equals("ADJA")) {
				return t.getCoveredText() + " "+ tagdesc.get(i + 1).getCoveredText();
			}
			// if RB,RBS,RBR is folledw by VB, VBD, VBN or VBG
			if ((t.getPos().getPosValue().equals("RB")
					|| t.getPos().getPosValue().equals("RBR") || t.getPos()
					.getPosValue().equals("RBS"))
					&& (tagdesc.get(i + 1).getPos().getPosValue().equals("VB")
							|| tagdesc.get(i + 1).getPos().getPosValue()
									.equals("VBD")
							|| tagdesc.get(i + 1).getPos().getPosValue()
									.equals("VBN") || tagdesc.get(i + 1)
							.getPos().getPosValue().equals("VBG"))) {
				return t.getCoveredText() + " "+ tagdesc.get(i + 1).getCoveredText();
			}
			// return just token-text
			else
				return t.getCoveredText();

		} else {
			return null;
		}
	}

	// writes a plus if average semantic oreintation is positiv
	private String getSentiment(String t) {
		String a;
		if (so(t)) {
			a = "+";
		} else {
			a = "-";
		}
		return " " + String.valueOf(a);
	}

	// returns the semantic orientation
	private boolean so(String t) {
		if ((pmi(t, true) - pmi(t, false)) > 0) {
			return true;
		} else {
			return false;
		}
	}
	//Pointwise Mutual Information  to excellent or poor
	private double pmi(String text, boolean isPositiv ) {
		double result;
		if(isPositiv){
			result=getPMI(text,"excellent");
			System.out.println("pmi for "+text+" and excellent: "+result);
			return result;
		}
		else{
			result=getPMI(text,"poor");
			System.out.println("pmi for "+text+" and poor : "+result);
			return result;
		}
		
	}
	//calculates the Pointwise Mutual Information 
	private double getPMI(String coveredText, String string) {
		int a=getCount(coveredText);
		int b=getCount(coveredText+" "+string);
		int c= getCount(string);
		
		double pmi=logarithmus(b/(a*c));
		
		return pmi;
	}
	// NEAR query 
	private int getCount(String string) {
		
		return 1;
	}
	//log2
	public double logarithmus( double x )
	{
	  return Math.log( x ) / Math.log( 2.0 );
	}
}
		
	