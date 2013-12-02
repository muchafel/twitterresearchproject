package ude.SocialMediaExplorer.shared.exchangeFormat;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class ClusterElement implements Serializable{
	
	/////////////////////////////////
	
	private String name;
	private Sentiment sentiment;
	private List<ClusterElement> subcluster;
	private boolean isRoot;
	private boolean isLeaf;
	
	/////////////////////////////////
	
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
	
	/////////////////////////////////
	
	public ClusterElement() {
		
	}
	
	/////////////////////////////////

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sentiment getSentiment() {
		return sentiment;
	}

	public void setSentiment(Sentiment sentiment) {
		this.sentiment = sentiment;
	}

	public List<ClusterElement> getSubcluster() {
		return subcluster;
	}

	public void setSubcluster(List<ClusterElement> subcluster) {
		this.subcluster = subcluster;
	}

	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	
}
