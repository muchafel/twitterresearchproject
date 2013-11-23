package ude.SocialMediaExplorer.shared.exchangeFormat;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class ClusterElement implements Serializable{
	private String name;
	private Sentiment sentiment;
	private List<ClusterElement> subcluster;
	private boolean isRoot;
	private boolean isLeaf;
	
	public ClusterElement(String name, Sentiment sentiment, List<ClusterElement> subCluster ){
		this.sentiment=sentiment;
		if(subCluster == null){
			isLeaf=true;
		}
		else this.subcluster=subCluster;
		if(name == null){
			isRoot=true;
		}
		else this.name=name;
	}
	
	public ClusterElement() {
		
	}
}
