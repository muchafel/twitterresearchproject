package ude.SocialMediaExplorer.data;

/**
 * Represents the data base in a normalized way 
 * @author henrikdetjen
 *
 */
public class Status {
	
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
	
	/*
	 * Constructors
	 */
	public Status(){
	}
	public Status (String message){
		this.message = message;
	}
	public Status(String message, String user){
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
	
	
}
