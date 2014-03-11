package ude.SocialMediaExplorer.analysis;

public class MyLink {

	double capacity; 
	 double weight; 
	 int id;
	 
	 public MyLink(double weight, double capacity, int edgeCount) {
	 this.id = edgeCount; 
	 this.weight = weight;
	 this.capacity = capacity;
	 } 
	 public String toString() { 
	 return "E"+id;
	 }


}
