package ude.SocialMediaExplorer.analysis;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;


import org.apache.commons.collections15.Transformer;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.scoring.BetweennessCentrality;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

import ude.SocialMediaExplorer.analysis.type.SenseAnno;

public class OrthographyCluster {

	public Map<String, Set<String>> cluster(List<JCas> jCases) {
		List<String> rawSenses= new ArrayList<String>();
		for (JCas jcas : jCases) {
			// System.out.println("Original text: "+jcas.getDocumentText()+" ----------------------");
			FSIndex senseIndex = jcas.getAnnotationIndex(SenseAnno.type);
			Iterator senseIterator = senseIndex.iterator();
			// gets all SenseAnnos from all jcases
			while (senseIterator.hasNext()) {
				SenseAnno sense = (SenseAnno) senseIterator.next();
//				if(rawSenses.contains(sense.getSenseValue())){
//					System.out.println("doppelt: " +sense.getSenseValue());
//				}
				rawSenses.add(sense.getSenseValue());
				}
			}
		//do clustering over all jcases
		Map<String, Set<String>> result=this.distanceClustering(rawSenses);
		
		return result;
	}

	private Map<String, Set<String>> distanceClustering(List<String> rawSenses) {
		UndirectedSparseGraph<String, MyLink> g = new UndirectedSparseGraph<String, MyLink>();
		for(String n : rawSenses){
			g.addVertex(n);
		}
		int linkId=0;
		for(int i=0; i<rawSenses.size();i++){
			for(int j=(i+1); j<rawSenses.size();j++){
//				System.out.println("i: " + i + " : " + rawSenses.get(i) + " j: "
//						+ j + " : " + rawSenses.get(j));
				try {

					if(calcWeight(rawSenses.get(i),rawSenses.get(j))>0.75){
						g.addEdge(
								new MyLink(calcWeight(rawSenses.get(i),rawSenses.get(j)), 1.0, linkId),
								rawSenses.get(i), rawSenses.get(j));
					//}
//					System.out.println("Edge with: i: " + i + " : " + rawSenses.get(i)
//							+ " j: " + j + " : " + rawSenses.get(j)
//							+ " Similarity:"
//							+  String.valueOf(calcWeight(rawSenses.get(i), rawSenses.get(j))));
					linkId++;}
				} catch (Exception e) {
					e.printStackTrace();
//					System.out.println("i: " + i + " : " + rawSenses.get(i)
//							+ " j: " + j + " : " + rawSenses.get(j));
				}
			}
		}
		visualize(g);
		Map<String, Set<String>> result= doBetweennessClustering(g);
		return result;
	}
	private Map<String, Set<String>> doBetweennessClustering(
			UndirectedSparseGraph<String, MyLink> g) {
		//transformer is used to do a adjusted BetweennessCentrality
		Transformer<MyLink, Double> wtTransformer = new Transformer<MyLink,Double>() {
			 public Double transform(MyLink link) {
			 return link.weight;
			 	}
			 };
		
			 // total unnötig ???
		CustomEdgeBetweennessClusterer<String, MyLink> clusterer= new CustomEdgeBetweennessClusterer( 0);
		
		BetweennessCentrality<String, MyLink> bc = new BetweennessCentrality<String, MyLink>(g);
		
		//retrieve the clusters
		Set<Set<String>>clusters= clusterer.transform(g);
		for(MyLink edge : (List<MyLink>)clusterer.getEdgesRemoved()){
			System.out.println("remove edge:"+edge.id+" score "+bc.getEdgeScore(edge)+" edge weight"+edge.weight);
			g.removeEdge(edge);
		}
		
		//iterate over all clusters and set the name of the most connected node as cluster name
		Iterator<Set<String>> iter = clusters.iterator();
		Map<String,Set<String>> clusterNames=new HashMap<String,Set<String>>();
		while (iter.hasNext()) {
			Set<String> cluster=iter.next();
			Iterator<String> iter2 = cluster.iterator();
				double temp0=0.0;
				String name=null;
				while (iter2.hasNext()) {
					String tempName=iter2.next();
					//System.out.println("Kandidat : "+tempName+" mit degree "+g.inDegree(tempName));
					double inDegree =getInDegreeg(g.getInEdges(tempName));
					if(inDegree>=temp0){
						name=tempName;
						temp0=inDegree;
						//System.out.println("Genommen: "+tempName+" mit degree "+temp0);
					}
				}
				clusterNames.put(name,cluster);
		}
		System.out.println(clusterNames);
		//visualizeSpecial(g);
		
		return clusterNames;
	}

private void visualize(UndirectedSparseGraph<String, MyLink> g) {
	Layout<String, String> layout = new CircleLayout(g);
	 layout.setSize(new Dimension(300,300));
	 VisualizationViewer<String,String> vv = new VisualizationViewer<String,String>(layout);
	 vv.setPreferredSize(new Dimension(350,350));
	 // Show vertex and edge labels
	 vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
	 vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
	 // Create a graph mouse and add it to the visualization component
	 DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
//	 gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
	 vv.setGraphMouse(gm); 
	 JFrame frame = new JFrame("Interactive Graph View 1");
	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 frame.getContentPane().add(vv);
	 frame.pack();
	 frame.setVisible(true);
		
	}

private double getInDegreeg(Collection<MyLink> inEdges) {
	double result=0.0;
	Iterator<MyLink> iter = inEdges.iterator();
	while(iter.hasNext()){
		result+= iter.next().weight;
	}
		return result;
	}

	//calculates the weight from a normalized levensthein distance minus 1. Everything that's under 0 will be assumed as totally different -->0
	private double calcWeight(String node1, String node2) {
		double levensthein= LevenshteinDistance(node1,node2);
		double length=node1.length();
		double result=1-(levensthein/length);
		if(result<0){
			result=0;
		}
		return result;
	}
	// --> http://de.wikipedia.org/wiki/Levenshtein-Distanz
	private static int LevenshteinDistance(String node1, String node2) {
		if (node1 == null || node2 == null) {
			throw new IllegalArgumentException("Strings must not be null");
		}
		// lengths of the nodes 
		int length1 = node1.length(); 
		int length2 = node2.length(); 

		//two costArrays for comparison
		int costArray1[] = new int[length1 + 1]; 
		int costArray2[] = new int[length1 + 1]; 
		int temp[]; // placeholder for swapping

		// indexes of strings 'node1' and 'node2'
		int i; 
		int j; 
		char jcount; // jth character of node1
		int cost; //temporary costs
		
		//if one lenght is zero the  distance is equal the length of the second
		if (length1 == 0) {
			return length2;
		} else if (length2 == 0) {
			return length1;
		}

		for (i = 0; i <= length1; i++) {
			costArray1[i] = i;
		}

		for (j = 1; j <= length2; j++) {
			jcount = node2.charAt(j - 1);
			costArray2[0] = j;

			for (i = 1; i <= length1; i++) {
				cost = node1.charAt(i - 1) == jcount ? 0 : 1;
				// minimum of cell to the left+1, to the top+1, diagonally left
				// and up +cost
				costArray2[i] = Math.min(Math.min(costArray2[i-1] + 1, costArray1[i]+1), costArray1[i-1]
						+ cost);
			}
			// copy current distance counts to 'previous row' distance counts
			temp = costArray1;
			costArray1 = costArray2;
			costArray2 = temp;
		}

		return costArray1[length1];
	}

}
