package ude.SocialMediaExplorer.analysis;

import static org.apache.uima.fit.util.JCasUtil.select;
import static de.tudarmstadt.ukp.dkpro.core.castransformation.ApplyChangesAnnotator.OP_DELETE;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import ude.SocialMediaExplorer.analysis.type.ArktweetAnno;
import ude.SocialMediaExplorer.analysis.type.SentimentAnno;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.TagsetDescription;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.api.transform.type.SofaChangeAnnotation;

public class ArktweetAnnotator extends JCasAnnotator_ImplBase{
/**
 * this class annotates the tweets with a additional helper-annotation. This annotation safes, if a token is a link, a user, a Retweet or a 
 * emoticon. This is necessary to replace the POS tags of links, or emoticon with the arktweet annotation.
 */
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		
		
		Sentence seg = new Sentence(aJCas, 0, aJCas.getDocumentText().length());
		seg.addToIndexes(aJCas);
		//gets all tokens
		List<Token> tokens = new ArrayList<Token>(select(aJCas, Token.class));
		for (Token t : tokens){
			//checks if the Arktweet POS is a U(link), a E (emoticon), a @ (a user) or a RT (retweet)
			if(t.getPos().getPosValue().equals("U")||t.getPos().getPosValue().equals("E")||t.getPos().getPosValue().equals("@")||t.getCoveredText().equals("RT")){
				//Annotate
				System.out.println("Arktweet: "+t.getPos().getCoveredText());
				ArktweetAnno annotation = new ArktweetAnno(aJCas);
	        	annotation.setBegin(t.getBegin());
	        	annotation.setEnd(t.getEnd());
	        	//writes the specified annotations in the helper annotation
	        	annotation.setLinkOrEmoticon(t.getPos());
	        	
	        	annotation.addToIndexes();
			}
		}
		List<AnnotationFS> pos = new ArrayList<AnnotationFS>(select(aJCas, POS.class));
		for(AnnotationFS p : pos){
			aJCas.removeFsFromIndexes( p);
		}
		//sets the document languge to german, in order to have useful results for the next annotation step (OpenNLP-POS-tagging)
		DocumentMetaData.get(aJCas).setLanguage("de");
	}

}
