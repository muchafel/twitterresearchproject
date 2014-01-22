package ude.SocialMediaExplorer.analysis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;

import ude.SocialMediaExplorer.analysis.type.SenseAnno;
import ude.SocialMediaExplorer.analysis.type.SimpleSenseAnno;

import de.tudarmstadt.ukp.dkpro.core.api.frequency.util.FrequencyDistribution;

public class FrequencyFinder {

	private List<JCas> jCases;
	public FrequencyFinder(List<JCas> jCases) {
		this.jCases=jCases;
		
	}
///This method creates a frequenzy distribution over all senses in all cases. In addition the orthography cluster is used 
///to detect mispelling and include this consideration in the frequenzy distribution
	public FrequencyDistribution<String> getFrequency(Map<String, Set<String>> orthographyClusters) {
		FrequencyDistribution<String> fq= new FrequencyDistribution<String>();
		for (JCas jcas : jCases) {
			FSIndex senseIndex = jcas.getAnnotationIndex(SimpleSenseAnno.type);
			Iterator senseIterator = senseIndex.iterator();
			// gets all SenseAnnos from all jcases
			while (senseIterator.hasNext()) {
				SimpleSenseAnno sense = (SimpleSenseAnno) senseIterator.next();
				String rawValue=sense.getSimpleSense();
				//iterate over all keys
				for (String key : orthographyClusters.keySet()) {
					//if the correponding set contains the rawValue
			        if(orthographyClusters.get(key).contains(rawValue)){
			        	// the key is included in the frequency distribution
			        	fq.inc(key);
//			        	System.out.println("key: "+key+" is inc'ed");
			        }
			    }
			}
			}
		return fq;
	}
}
