package ude.SocialMediaExplorer.analysis;

import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.TypeSystemDescriptionFactory.*;
import static org.apache.uima.fit.util.JCasUtil.select;

import org.annolab.tt4j.TreeTaggerModel;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.api.frequency.util.FrequencyDistribution;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.constituent.NP;
import de.tudarmstadt.ukp.dkpro.core.arktools.ArktweetTagger;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpChunker;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordParser;
import de.tudarmstadt.ukp.dkpro.core.treetagger.TreeTaggerChunkerTT4J;
import de.tudarmstadt.ukp.dkpro.core.treetagger.TreeTaggerPosLemmaTT4J;
import de.tudarmstadt.ukp.dkpro.core.treetagger.TreeTaggerTT4JBase;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

import ude.SocialMediaExplorer.analysis.type.CleanedSenseAnno;
import ude.SocialMediaExplorer.analysis.type.SenseAnno;
import ude.SocialMediaExplorer.analysis.type.SentimentAnno;
import ude.SocialMediaExplorer.data.utils.io.CASReader;
import ude.SocialMediaExplorer.shared.exchangeFormat.ClusterElement;
import ude.SocialMediaExplorer.shared.exchangeFormat.Sentiment;

public class Clusterer {

	public static FrequencyDistribution<String> fq;
	public static Map<String,Set<String>> orthographyClusters;
	public int nodeCounter;
	private String hashtagToCluster="halligalli";
	//TODO: replace main with run() + parametrisieren
	/**
	 * @param args
	 */
	public void cluster(String hashtagToCluser) {
		
		this.hashtagToCluster=hashtagToCluser;
		String directory= "files/serializedCases/"+"halligalli";
		CASReader reader= new CASReader();
		List<JCas> jCases= new ArrayList<JCas>();
		try {
			jCases=reader.read("files/serializedCases/"+hashtagToCluster);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		OrthographyCluster oCluster= new OrthographyCluster();
		//find gazeteer for orthographic lexicon on senses
		orthographyClusters= oCluster.cluster(jCases);
		System.out.println("gefundene Orthographiecluster: "+orthographyClusters);
		//find frequency distribution of clusters (uses orthography lexicon)
		fq= new FrequencyFinder(jCases).getFrequency(orthographyClusters);
		System.out.println("Most 40 frequent senses (orthographically cleaned): "+fq.getMostFrequentSamples(40));
		//annotate orthography and frequency to cases
		jCases= annotateSenseFrequency(jCases);
		
		ClusterElement clusterElement=createClusterElementsNaive(jCases, null, null);
		
		
		UndirectedSparseGraph<String, String> graph=calcGraph(clusterElement);
		visualize(graph);
		
		serialize(clusterElement);
		System.out.println("Serialization finished");
//	    printCluster(clusterElement);
	}

	// binary serialization Java6-style
	private void serialize(ClusterElement clusterElement) {
		FileOutputStream fs = null;
		ObjectOutputStream os = null;
		try {
			fs = new FileOutputStream("files/serializedClusterElements/"+hashtagToCluster+"/"+hashtagToCluster+".ser");
			os = new ObjectOutputStream(fs);
			os.writeObject(clusterElement);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fs != null) {
				try {
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
//in this step the co-occurence graph is performed and these "cleaned senses" are merged with the orthography-cleaning
	private static List<JCas> annotateSenseFrequency(List<JCas> jCases) {
		
		for(JCas jcas : jCases){
			
			try {
				AggregateBuilder builder = new AggregateBuilder();
				builder.add(createEngineDescription(CleanedSenseAnnotator.class));
				builder.add(createEngineDescription(SenseAnnotator.class));
//				builder.add(createEngineDescription(StanfordParser.class,StanfordParser.PARAM_MODEL_LOCATION,
//                        "classpath:/de/tudarmstadt/ukp/dkpro/core/stanfordnlp/lib/parser-de-factored.ser.gz"));
				AnalysisEngine engine = builder.createAggregate();
				engine.process(jcas);
				

				//for testing
//				System.out.println("Tweet: "+jcas.getDocumentText());
//				for (Sentence sentence : JCasUtil.select(jcas, Sentence.class)) {
//				for (NP nounphrase : JCasUtil.selectCovered(jcas, NP.class,sentence)) {
//					System.out.println("found namephrase: "+nounphrase.getCoveredText());
//					
//					}
//				}
//				System.out.println("Original text: "+jcas.getDocumentText());
//		        FSIterator<AnnotationFS> annotationIterator = jcas.getCas().getAnnotationIndex().iterator();
//		        while (annotationIterator.hasNext()) {
//		                AnnotationFS annotation = annotationIterator.next();
//		                System.out.println("[" + annotation.getCoveredText() + "]");
//		                System.out.println(annotation.toString());
//		        }
				
				
			} catch (ResourceInitializationException e) {
				e.printStackTrace();
			} catch (AnalysisEngineProcessException e) {
				e.printStackTrace();
			}
		}
		
		return jCases;
	}

	//  prepare a jung graph for visualization
	private UndirectedSparseGraph<String, String> calcGraph(ClusterElement clusterElement) {
		UndirectedSparseGraph<String, String> g = new UndirectedSparseGraph<String, String>();
		
		g=addNodes(clusterElement,g);
		g=addEdges(clusterElement,g);
		return g;
	}
	// add all edges
	private static UndirectedSparseGraph<String, String> addEdges(ClusterElement clusterElement,UndirectedSparseGraph<String, String> g) {
		if (!clusterElement.getSubcluster().isEmpty()) {
			for (ClusterElement c : clusterElement.getSubcluster()) {
				g.addEdge(clusterElement.getName()+c.getName(), clusterElement.getName(),c.getName());
				g=addEdges(c,g);
			}
		}
		return g;
	}

	//adds all nodes to the graph
	private static UndirectedSparseGraph<String, String> addNodes(ClusterElement clusterElement,UndirectedSparseGraph<String, String> g) {
		g.addVertex(clusterElement.getName());
		if (!clusterElement.getSubcluster().isEmpty()) {
			for (ClusterElement c : clusterElement.getSubcluster()) {
				g=addNodes(c,g);
			}
		}
		return g;
	}

	// show the graph with gui
	private static void visualize(UndirectedSparseGraph<String, String> g) {
		Layout<String, String> layout = new FRLayout(g);
		 layout.setSize(new Dimension(300,300));
		 VisualizationViewer<String,String> vv = new VisualizationViewer<String,String>(layout);
		 vv.setPreferredSize(new Dimension(350,350));
		 // Show vertex and edge labels
		 vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		 vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
		 // Create a graph mouse and add it to the visualization component
		 DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
//		 gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
		 vv.setGraphMouse(gm); 
		 JFrame frame = new JFrame("Interactive Graph View 1");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.getContentPane().add(vv);
		 frame.pack();
		 frame.setVisible(true);
	}
// print all found clusters
	private void printCluster(ClusterElement clusterElement) {
		System.out.println("Cluster:"+clusterElement.getName());
		if (!clusterElement.getSubcluster().isEmpty()) {
			for (ClusterElement c : clusterElement.getSubcluster()) {
				printCluster(c);
			}
		}
	}

	//TODO Sentiment richtig einbauen!
	private ClusterElement createClusterElementsNaive(List<JCas> jCases,String clusterName,List<String> headers) {
		
		ClusterElement c;
		FrequencyDistribution<String> frequencydistribution;
		
		List<ClusterElement> subClusters = new ArrayList<ClusterElement>();
		
		if (clusterName == null) {
			c = new ClusterElement("TopCluster", new Sentiment(0,0), null);
			frequencydistribution=fq;
			headers = new ArrayList<String>();
		} else {
			c = new ClusterElement(clusterName, calcSentiment(jCases), null);
			frequencydistribution=creatfd(jCases,clusterName,headers);
			//System.out.println(clusterName+" Max Frq:"+frequencydistribution.getSampleWithMaxFreq());
		}
		//set the posts of the clusterelement
		c.setPosts(getRawText(jCases));
		for (String subClusterName: frequencydistribution.getMostFrequentSamples(18)){
			headers.add(subClusterName);
			ClusterElement subCluster=createClusterElementsNaive(getSubset(jCases,subClusterName),subClusterName,headers);
			subClusters.add(subCluster);
		}
		c.setSubcluster(subClusters);
		
		return c;
	}

	private Sentiment calcSentiment(List<JCas> jCases) {
		
		double totalPositive = 0;
		double totalNegative = 0;
		
		//iterate over all cases and find for each a sentiment (positiv/negative) 
		for(JCas jcas: jCases){
			List<SentimentAnno> annos = new ArrayList<SentimentAnno>(select(jcas,SentimentAnno.class));
			double positive = 0;
			double negative = 0;
			//iterate over all Sentiment annos and sum positive and negative
			for(SentimentAnno anno: annos){
				if(anno.getSentimentValue().equals("true")){
					positive++;
				}
				if(anno.getSentimentValue().equals("false")){
					negative++;
				}
			}
			int lenght=select(jcas,Token.class).size();
			//normalize through length of all tokens
			totalPositive+=positive/lenght;
			totalNegative+=negative/lenght;
		}
		//normalize through size of all cases
		totalPositive=totalPositive/jCases.size();
		totalNegative=totalNegative/jCases.size();
		
		return new Sentiment(totalPositive,totalNegative);
	}

	private ArrayList<String> getRawText(List<JCas> jCases) {
		ArrayList<String> posts= new ArrayList<String>();
		for(JCas jcas: jCases){
			posts.add(jcas.getDocumentText());
		}
		return posts;
	}

	private FrequencyDistribution<String> creatfd(List<JCas> jCases,
			String clusterName, List<String> headers) {
		FrequencyDistribution<String> frequencydistribution= new FrequencyDistribution<String>();
		for(JCas jcas : jCases){
			FSIndex senseIndex = jcas.getAnnotationIndex(SenseAnno.type);
			Iterator senseIterator = senseIndex.iterator();
			// gets all CleanedSenseAnnos from jcas
			while (senseIterator.hasNext()) {
				SenseAnno sense = (SenseAnno) senseIterator.next();
				String senseValue=sense.getSenseValue();
				if(!headers.contains(senseValue)){
					frequencydistribution.inc(senseValue);
				}
			}
		}
		return frequencydistribution;
	}

	private List<JCas> getSubset(List<JCas> jCases,String name) {
		List<JCas> subset= new ArrayList<JCas>();
//		System.out.println("SSSize: "+jCases.size());
		for(JCas jcas : jCases){
			FSIndex senseIndex = jcas.getAnnotationIndex(SenseAnno.type);
			//System.out.println("Index:"+senseIndex.toString()+"  - "+senseIndex.size());
			Iterator senseIterator = senseIndex.iterator();
			// gets all CleanedSenseAnnos from jcas
			while (senseIterator.hasNext()) {
				SenseAnno sense = (SenseAnno) senseIterator.next();
				String senseValue=sense.getSenseValue();
				//System.out.println("SenseValue: "+senseValue);
				if(senseValue.equals(name)){
//					System.out.println(jcas.getDocumentText()+" added to subset: jacas mit: "+name);
					subset.add(jcas);
				}
			}
		}
		return subset;
	}
}
