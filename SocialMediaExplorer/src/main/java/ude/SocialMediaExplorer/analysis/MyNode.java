package ude.SocialMediaExplorer.analysis;

/**
 * a custom node class that is used in the graph-operations
 *
 */
public class MyNode {

	String id; 
	 public MyNode(String name) {
	 this.id = name;
	 }
	 public String toString() { 
	 return "V "+id; 
	 } 

}
