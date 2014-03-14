package ude.SocialMediaExplorer.analysis;

import static org.apache.uima.fit.util.JCasUtil.select;

import java.util.ArrayList;
import java.util.List;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import ude.SocialMediaExplorer.analysis.type.SimpleSenseAnno;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

/**
 * This annotator assignes a helper-annotation to every token if its POS is NN, NE or ADJA.
 * This step is used to speed up the following clustering steps.
 *
 */
public class SimpleSenseAnnotator extends JCasAnnotator_ImplBase{
private List<Token> tokens;
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		tokens = new ArrayList<Token>(select(aJCas, Token.class));
		for (Token t : tokens){
			if(t.getPos().getPosValue().equals("NN")||t.getPos().getPosValue().equals("NE")||t.getPos().getPosValue().equals("ADJA")){
				SimpleSenseAnno annotation = new SimpleSenseAnno(aJCas);
	        	annotation.setBegin(t.getBegin());
	        	annotation.setEnd(t.getEnd());
	        	annotation.setSimpleSense(t.getCoveredText());
	        	annotation.addToIndexes();
				
			}
		}
	}

}
