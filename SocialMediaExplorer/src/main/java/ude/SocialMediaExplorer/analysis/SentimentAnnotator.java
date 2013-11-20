package ude.SocialMediaExplorer.analysis;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;
import static org.apache.uima.fit.util.JCasUtil.toText;

import java.util.List;

import org.apache.uima.analysis_component.AnalysisComponent_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.AbstractCas;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.resources.MappingProvider;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class SentimentAnnotator extends AnalysisComponent_ImplBase{

	public void process(AbstractCas cas) throws AnalysisEngineProcessException {
		
		
		JCas jcas=(JCas)cas;
		jcas.getCas().createAnnotation(, begin, end)
		
	}

	public boolean hasNext() throws AnalysisEngineProcessException {
		System.out.println("nicht implementiert");
		return false;
	}

	public AbstractCas next() throws AnalysisEngineProcessException {
		System.out.println("nicht implementiert");
		return null;
	}

	public Class<? extends AbstractCas> getRequiredCasInterface() {
		System.out.println("nicht implementiert");
		return null;
	}

	public int getCasInstancesRequired() {
		System.out.println("nicht implementiert");
		return 0;
	}

}
