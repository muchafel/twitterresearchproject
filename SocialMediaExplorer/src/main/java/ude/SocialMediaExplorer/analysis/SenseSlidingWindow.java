package ude.SocialMediaExplorer.analysis;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JFrame;

import de.tudarmstadt.ukp.dkpro.core.api.frequency.util.FrequencyDistribution;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

public class SenseSlidingWindow {
	
	private List<String> pos;
	private int phraseCutOff=2;
	public List<String> getKeyPhrases (List<String> words,List<String> pos){
		
		int w = 4; // width of sliding window
		this.pos=pos;
        List<List<String>> slides = new ArrayList<List<String>>();
        //checks for the lenght that is specified with w
        for (int i = 0; i < words.size()-(w-1); i++) {
        	List<String> wordSet = new ArrayList<String> ();
        	for (int j = 0; j < w; j++){
        		//just put nouns and adjectives in the sliding window
        		if(isNounOrAdjective(j+i)){
        		wordSet.add(words.get(j+i));
        		}
        	}
        	slides.add(wordSet);
        }
       // System.out.println(slides);
        //calculate the graph
        UndirectedSparseGraph<String, Integer> coOccurenceGraph= doCoOccurenceGraph(slides);
//        Hashtable<String, Integer> ht = new Hashtable<String, Integer>();
        FrequencyDistribution<String> fq = new FrequencyDistribution<String>();
        for(String node: coOccurenceGraph.getVertices()){
//        	ht.put(node, coOccurenceGraph.inDegree(node));
        	for( int i=0; i<coOccurenceGraph.inDegree(node);i++){
        		fq.inc(node);
        	}
        }
//        System.out.println(ht);
        //System.out.println(fq.getMostFrequentSamples(5));
        List<String> keywords=fq.getMostFrequentSamples(5);
        List<String> keyPhrases=new ArrayList<String>();
        
        //to find the adjacent candidates we iterate over all found keywords
        for (String word : keywords){
        	String tempWord=word;
        	//the we iterate over all words
        	for (int i=0; i<words.size();i++){
        		//if the keyphrase is found in the sentence
        		if(word.equals(words.get(i))){
        			//we check if it isn't the last word
        			if(i<words.size()-1){
	        			String post=words.get(i+1);
	        			// then we check if the following word has a ranking >2 and is a noun or a adjective
	        			if(fq.getCount(post)>phraseCutOff && isNounOrAdjective(i+1)){
	        				//then we concate the words
	        				tempWord=word+" "+post;
	        			}
	        			if(i<words.size()-2){
		        			String postpost=words.get(i+2);
		        			// then we check if the following-following word has a ranking >2 and is a noun or a adjective
		        			if(fq.getCount(postpost)>phraseCutOff && isNounOrAdjective(i+1)){
		        				//then we concate the words
		        				tempWord=tempWord+" "+postpost;
		        			}
	        			}
        			}
        			//we check if it isn't the first word
        			if(i>1){
        				String pre=words.get(i-1);
        				// then we check if the previous word has a ranking >2 and is a noun or a adjective
        				if(fq.getCount(pre)>phraseCutOff && isNounOrAdjective(i-1)){
        					//then we concate the words
	        				tempWord=pre+" "+tempWord;
	        			}
        				if(i>2){
            				String prepre=words.get(i-2);
            				// then we check if the previous-previous word has a ranking >2 and is a noun or a adjective
            				if(fq.getCount(prepre)>phraseCutOff && isNounOrAdjective(i-2)){
            					//then we concate the words
    	        				tempWord=prepre+" "+tempWord;
    	        			}
            			}
        			}
        		}
        	}
        	if(!keyPhrases.contains(tempWord)){
        		keyPhrases.add(tempWord);
        	}
        	
        }
//        System.out.println(keyPhrases);
        //uncomment to visualize the graph
       // visualize(coOccurenceGraph);
		return keyPhrases;
	}
	private int getOrder(FrequencyDistribution<String> fq, String post) {
		for(int i=0; i<6;i++){
			if(fq.getMostFrequentSamples(i).contains(post)){
				return i;
			}
		}
		return 6;
	}
	private boolean isNounOrAdjective(int i) {
		if(pos.get(i).equals("NN")||pos.get(i).equals("NE")||pos.get(i).equals("ADJA"))return true;
		return false;
	}

	private void visualize(
			UndirectedSparseGraph<String, Integer> coOccurenceGraph) {
		Layout<String, String> layout = new FRLayout(coOccurenceGraph);
		 layout.setSize(new Dimension(300,300));
		 VisualizationViewer<String,String> vv = 
		 new VisualizationViewer<String,String>(layout);
		 vv.setPreferredSize(new Dimension(350,350));
		 // Show vertex and edge labels
		 vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		 vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
		 // Create a graph mouse and add it to the visualization component
		 DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
//		 gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
		 vv.setGraphMouse(gm); 
		 JFrame frame = new JFrame("Co-Occurrence");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.getContentPane().add(vv);
		 frame.pack();
		 frame.setVisible(true);
		
	}
	private UndirectedSparseGraph<String, Integer> doCoOccurenceGraph(List<List<String>> slides) {
		int i=0;
		UndirectedSparseGraph<String, Integer> g = new UndirectedSparseGraph<String, Integer>();
		for(List<String> slide : slides){
			//add all words to graph
			for(String word : slide){
				g.addVertex(word);
			}
			//connect all nodes that co-occur in a sliding window
			for(String wordA : slide){
				//without crossing sentence boundaries
				if(wordA.equals(".")||wordA.equals("!")||wordA.equals("?")){
					break;
				}
				for(String wordB : slide){
					//don't create a edege to the word itself
					if(!wordB.equals(wordA)){
						//create the ede
					g.addEdge(i, wordA,wordB);
					i++;
					}
				}
			}
		}
		return g;
	}
}
