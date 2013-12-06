package ude.SocialMediaExplorer.analysis;

public class MyNode {

	String id; // good coding practice would have this as private
	 public MyNode(String name) {
	 this.id = name;
	 }
	 public String toString() { // Always a good idea for debuging
	 return "V"+id; // JUNG2 makes good use of these.
	 } 

}
