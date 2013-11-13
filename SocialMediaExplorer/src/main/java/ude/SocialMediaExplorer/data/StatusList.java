package ude.SocialMediaExplorer.data;

import java.util.ArrayList;

/**
 * An extension of {@link ArrayList} for handling lots of tweets in their twitter4j representation {@link Status}
 * @author henrikdetjen
 * 
 */
public class StatusList extends ArrayList<Status>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2125186205141792778L;


	/**
	 * default constructor
	 */
	public StatusList(){
		super();
	}
	
	//TODO: Add some useful functions :D
	public void print(){
		for (int i = 0; i < this.size(); i++){
			System.out.println(i + ": " + this.get(i).toString());
		}
	}
}
