package ude.SocialMediaExplorer.analysis;

import static ude.SocialMediaExplorer.analysis.Clusterer.orthographyClusters;
import static ude.SocialMediaExplorer.analysis.Clusterer.fq;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import ude.SocialMediaExplorer.analysis.type.CleanedSenseAnno;
import ude.SocialMediaExplorer.analysis.type.SimpleSenseAnno;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.SegmenterBase;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

/**
 * this annotator assignes the cleaned senses to all tokens
 *
 */
public class CleanedSenseAnnotator extends SegmenterBase {
	private ArrayList<Token> tokens;
	@Override
	protected void process(JCas aJCas, String text, int zoneBegin)
			throws AnalysisEngineProcessException {
			FSIndex senseIndex = aJCas.getAnnotationIndex(SimpleSenseAnno.type);
			Iterator senseIterator = senseIndex.iterator();
			// gets all SenseAnnos from jcas
			while (senseIterator.hasNext()) {
				SimpleSenseAnno sense = (SimpleSenseAnno) senseIterator.next();
				String rawValue=sense.getSimpleSense();
				//iterate over all keys
				for (String key : orthographyClusters.keySet()) {
					//if the correponding set contains the rawValue
			        if(orthographyClusters.get(key).contains(rawValue)){
			        	// the key is set as the cleaned sense
			        	CleanedSenseAnno annotation = new CleanedSenseAnno(aJCas);
			        	annotation.setBegin(sense.getBegin());
						annotation.setEnd(sense.getEnd());
			        	annotation.setCleanedSense(key);
			        	// get the total Frequency from the frequency distribution
			        	annotation.setTotalFrequenzy((int) fq.getCount(key));
			        	// get the ordinal place from the frequency distribution
			        	int ordinal=getOrder(key);
			        	annotation.setOrderByFreqenzy(ordinal);
			        	annotation.addToIndexes();
			        }
			    }
			}
	}
	/**
	 * Gets the order of a keyword in the frequenzy distribution (orthography-cluster)
	 * @param keyword
	 * @return order
	 */
	private int getOrder(String key) {
		int n=1;
		while(true){
			if(fq.getMostFrequentSamples(n).contains(key)){
			break;
			}
			else n++;
		}
		
		return n;
	}
}


