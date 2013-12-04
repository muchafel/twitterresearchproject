package ude.SocialMediaExplorer.analysis;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.apache.uima.fit.factory.TypeSystemDescriptionFactory.*;
import static org.apache.uima.fit.util.JCasUtil.select;

import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

import ude.SocialMediaExplorer.analysis.type.SenseAnno;
import ude.SocialMediaExplorer.data.utils.io.CASReader;
import ude.SocialMediaExplorer.shared.exchangeFormat.ClusterElement;
import ude.SocialMediaExplorer.shared.exchangeFormat.Sentiment;

public class Clusterer {

	//TODO: replace main with run()
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String directory= "files/serializedCases/"+"Tatort";
		CASReader reader= new CASReader();
		List<JCas> jCases= new ArrayList<JCas>();
		try {
			jCases=reader.read(directory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ClusterElement clusterElement=createClusterElements(jCases, null,null);
		printCluster(clusterElement);
	}

	private static void printCluster(ClusterElement clusterElement) {
		System.out.println("Cluster:"+clusterElement.getName());
		if (!clusterElement.getSubcluster().isEmpty()) {
			for (ClusterElement c : clusterElement.getSubcluster()) {
				printCluster(c);
			}
		}
	}
///TODO add Sentiment
	private static ClusterElement createClusterElements(List<JCas> jCases,String cluster,List<String> headers) {
		ClusterElement c;
		List<ClusterElement> subClusters = new ArrayList<ClusterElement>();
		if (cluster == null) {
			c = new ClusterElement("TopCluster", new Sentiment(), null);
			headers = new ArrayList<String>();
		} else {
			c = new ClusterElement(cluster, new Sentiment(), null);
		}
		for (JCas jcas : jCases) {
			// System.out.println("Original text: "+jcas.getDocumentText()+" ----------------------");
			FSIndex senseIndex = jcas.getAnnotationIndex(SenseAnno.type);
			Iterator senseIterator = senseIndex.iterator();
			while (senseIterator.hasNext()) {
				SenseAnno sense = (SenseAnno) senseIterator.next();
				if (!headers.contains(sense.getSenseValue())) {
					// System.out.println("Sense:"+sense.getSenseValue());
					headers.add(sense.getSenseValue());
					subClusters.add(createClusterElements(jCases,
							sense.getSenseValue(), headers));
				}
			}
		}
		c.setSubcluster(subClusters);
		return c;
	}
}
