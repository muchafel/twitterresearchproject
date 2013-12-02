package ude.SocialMediaExplorer.data.post;

/**
 * Represents the data base
 * @author henrikdetjen
 *
 */
public class Post {
	
	/**
	 * the user who posted the dataset
	 */
	private String user = "";
	/**
	 * the message which was posted 
	 */
	private String message = "";
	/**
	 * how many times the msg was shared/retweeted
	 */
	private int shared = 0;
	/**
	 * the id 
	 */
	private String id = "";	

	/*
	 * Constructors
	 */
	public Post(){
	}
	public Post (String message){
		this.message = message;
	}
	public Post(String message, String user){
		this.message = message;
		this.user = user;
	}
	
	/*
	 * Getters and setters
	 */
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getShared() {
		return shared;
	}
	public void setShared(int shared) {
		this.shared = shared;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
