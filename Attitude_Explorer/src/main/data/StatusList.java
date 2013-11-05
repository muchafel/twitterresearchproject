package main.data;

import java.util.List;
import java.util.ArrayList;

import twitter4j.Status;

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
	/**
	 * constructor for direct usage with twitter4j (and maybe others)
	 * @param  is twitter4j's response type of query -> result -> getTweets()
	 */
	public StatusList(List<Status> list){
		super(list);
	}
	
	
	//TODO: Add some useful functions :D
	public void print(){
		for (int i = 0; i < this.size(); i++){
			System.out.println(i + ": " + this.get(i).toString());
		}
	}
}
