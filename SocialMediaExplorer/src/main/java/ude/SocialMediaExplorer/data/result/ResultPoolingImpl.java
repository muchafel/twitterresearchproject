package ude.SocialMediaExplorer.data.result;

import static org.apache.uima.fit.factory.TypeSystemDescriptionFactory.createTypeSystemDescription;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;

import ude.SocialMediaExplorer.analysis.type.SenseAnno;
import ude.SocialMediaExplorer.data.utils.io.CASReader;
import ude.SocialMediaExplorer.data.utils.time.TimeSpan;
import ude.SocialMediaExplorer.shared.exchangeFormat.ClusterElement;
import ude.SocialMediaExplorer.shared.exchangeFormat.Sentiment;

public class ResultPoolingImpl implements IResultPooling {


	public ClusterElement getClusters(String hashtag) throws Exception {
		CASReader reader= new CASReader();
		String directory= "files/serializedCases/"+hashtag;
		List<JCas> jCases= new ArrayList<JCas>();
		try {
			jCases=reader.read(directory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ClusterElement clusterElement=createClusterElements(jCases, null,null);
		printCluster(clusterElement);
		return clusterElement;
	}

	public ClusterElement getClusters(String hashtag, TimeSpan timespan)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	private void printCluster(ClusterElement clusterElement) {
		System.out.println("Cluster:"+clusterElement.getName());
		if (!clusterElement.getSubcluster().isEmpty()) {
			for (ClusterElement c : clusterElement.getSubcluster()) {
				printCluster(c);
			}
		}
	}
///TODO add Sentiment
	private ClusterElement createClusterElements(List<JCas> jCases,String cluster,List<String> headers) {
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
