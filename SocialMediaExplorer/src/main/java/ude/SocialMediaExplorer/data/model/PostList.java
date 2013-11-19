package ude.SocialMediaExplorer.data.model;

import java.util.ArrayList;

/**
 * An extension of {@link ArrayList} for handling lots of tweets in their twitter4j representation {@link Post}
 * @author henrikdetjen
 * 
 */
public class PostList extends ArrayList<Post>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2125186205141792778L;


	/**
	 * default constructor
	 */
	public PostList(){
		super();
	}
	
	//TODO: Add some useful functions :D
	public void print(){
		System.out.println("\n-------- size: "+this.size()+" ----------");
		for (int i = 0; i < this.size(); i++){
			System.out.println(i + ": " + this.get(i).getUser() + ": " + this.get(i).getMessage());
		}
		System.out.println("----------------------------\n");
	}
	public String makeString(){
		String result = "";
		result += "\n-------- size: "+this.size()+" ----------";
		for (int i = 0; i < this.size(); i++){
			result += "\n" + i + ": " + this.get(i).getUser() + ": " + this.get(i).getMessage();
		}
		result += "\n----------------------------\n";
		return result;
	}
}
