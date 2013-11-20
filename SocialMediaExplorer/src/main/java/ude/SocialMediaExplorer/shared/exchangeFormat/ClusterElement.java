package ude.SocialMediaExplorer.shared.exchangeFormat;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Vector;

public class ClusterElement {
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
