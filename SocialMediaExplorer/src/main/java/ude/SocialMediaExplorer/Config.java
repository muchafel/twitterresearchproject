package ude.SocialMediaExplorer;

import java.util.ArrayList;

/**
 * A place for all configurable params
 * @author henrikdetjen
 *
 */
public class Config {
	
	// auth
	public static String twitter_ConsumerKey = "Y9I5pvM8aUhJiUFKj4XxgQ";
	public static String twitter_ConsumerSecret = "doQ84Y3mtge6kIxus3zFeKK2t8AdC3FRwuNVXPs9iB4";
	public static String twitter_AccessToken = "2159056350-Jb6q09GoUeJJvGHj4qmM4mDNwA4hFURgU1gOg76";
	public static String twitter_AccessTokenSecret = "hlghHUIQLL9RMWvcKU36obLwqKMesRL2gIzfVBFxZEDWY";
	public static int twitter_result_restriction = 20;
		
	// io
	private static String location_tweets = "files/rawdata/tweets/";
	public static String get_location_tweets(){
		return location_tweets;
	}
	private static String location_results = "files/results/";
	public static String get_location_results(){
		return location_results;
	}
	
	//crawler
	public static long crawler_newFileInterval = 1000*60*60*24*7; //ms
	@SuppressWarnings("serial")
	public static ArrayList<String> crawler_hashtags = new ArrayList<String>() {{
		add("#tatort");
		add("#spiegeltv");
		add("#sterntv");
		add("#terrax");
		add("#guentherjauch");
		add("#annewill");
		add("#fashionhero");
		add("#tvog");
		add("#halligalli");
	}};
	

}
