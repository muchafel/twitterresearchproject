package ude.SocialMediaExplorer.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.apache.commons.collections15.Transformer;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;

import edu.uci.ics.jung.algorithms.scoring.BetweennessCentrality;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;

import ude.SocialMediaExplorer.analysis.type.SenseAnno;

public class OrthographyCluster {

	public Map<String, Set<String>> cluster(List<JCas> jCases) {
		List<String> rawSenses= new ArrayList<String>();
		for (JCas jcas : jCases) {
			// System.out.println("Original text: "+jcas.getDocumentText()+" ----------------------");
			FSIndex senseIndex = jcas.getAnnotationIndex(SenseAnno.type);
			Iterator senseIterator = senseIndex.iterator();
			while (senseIterator.hasNext()) {
				SenseAnno sense = (SenseAnno) senseIterator.next();
				rawSenses.add(sense.getSenseValue());
				}
			}
		
		Map<String, Set<String>> result=this.distanceClustering(rawSenses);
		
		return null;
	}

	private Map<String, Set<String>> distanceClustering(List<String> rawSenses) {
		UndirectedSparseGraph<String, MyLink> g = new UndirectedSparseGraph<String, MyLink>();
		for(String n : rawSenses){
			g.addVertex(n);
		}
		int linkId=0;
		for(int i=0; i<rawSenses.size();i++){
			for(int j=(i+1); j<rawSenses.size();j++){
				System.out.println("i: " + i + " : " + rawSenses.get(i) + " j: "
						+ j + " : " + rawSenses.get(j));
				try {
					// /TODO: normalize somehow on wordlength
					/// TODO: <5 überdenken!
					//if (calcWeight(nodeList.get(i), nodeList.get(j)) < 5) {
					if(calcWeight(rawSenses.get(i),rawSenses.get(j))>0.75){
						g.addEdge(
								new MyLink(calcWeight(rawSenses.get(i),rawSenses.get(j)), 1.0, linkId),
								rawSenses.get(i), rawSenses.get(j));
					//}
					System.out.println("Edge with: i: " + i + " : " + rawSenses.get(i)
							+ " j: " + j + " : " + rawSenses.get(j)
							+ " Similarity:"
							+  String.valueOf(calcWeight(rawSenses.get(i), rawSenses.get(j))));
					linkId++;}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("i: " + i + " : " + rawSenses.get(i)
							+ " j: " + j + " : " + rawSenses.get(j));
				}
			}
		}
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
				int temp0=0;
				String name=null;
				while (iter2.hasNext()) {
					String tempName=iter2.next();
					System.out.println("Kandidat : "+tempName+" mit degree "+g.inDegree(tempName));
					if(g.inDegree(tempName)>=temp0){
						name=tempName;
						temp0=g.inDegree(tempName);
						System.out.println("Genommen: "+tempName+" mit degree "+temp0);
					}
					
				}
				clusterNames.put(name,cluster);
				
		}
		System.out.println(clusterNames);
		//visualizeSpecial(g);
		
		return clusterNames;
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
		    int n = node1.length(); // length of s
		    int m = node2.length(); // length of t
		
		    if (n == 0) {
		      return m;
		    } 
		    else if (m == 0) {
		      return n;
		    }
		 
		    int p[] = new int[n+1]; //'previous' cost array, horizontally
		    int d[] = new int[n+1]; // cost array, horizontally
		    int _d[]; //placeholder to assist in swapping p and d
		 
		    // indexes into strings s and t
		    int i; // iterates through s
		    int j; // iterates through t
		 
		    char t_j; // jth character of t
		 
		    int cost; // cost
		 
		    for (i = 0; i<=n; i++) {
		       p[i] = i;
		    }
		     
		    for (j = 1; j<=m; j++) {
		       t_j = node2.charAt(j-1);
		       d[0] = j;
		     
		       for (i=1; i<=n; i++) {
		          cost = node1.charAt(i-1)==t_j ? 0 : 1;
		          // minimum of cell to the left+1, to the top+1, diagonally left and up +cost                         
		          d[i] = Math.min(Math.min(d[i-1]+1, p[i]+1),  p[i-1]+cost);  
		       }
		 
		       // copy current distance counts to 'previous row' distance counts
		       _d = p;
		       p = d;
		       d = _d;
		    }
		     
		    // our last action in the above loop was to switch d and p, so p now
		    // actually has the most recent cost counts
		    return p[n];
	}

}
