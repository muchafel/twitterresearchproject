package ude.SocialMediaExplorer;

import java.util.ArrayList;

import ude.SocialMediaExplorer.data.utils.io.XMLFileManager;

/**
 * A place for all configurable params
 * 
 * @author henrikdetjen
 * 
 */
public class Config {

	/**
	 * Path to project_config.xml (relative)
	 */
	private static final String	xmlPath					= "files/project_config.xml";

	/**
	 * Crawler
	 */

	/**
	 * run params
	 */
	// how often a stream is splitted in a new file
	private static long			crawler_newFileInterval	= -1;							// ms

	public static long get_crawler_newFileInterval() {
		if ( crawler_newFileInterval < 0 ) {
			crawler_newFileInterval = Long.parseLong( readValueFromXMLFile( "crawler_newFileInterval" ) );
		}
		return crawler_newFileInterval;
	}

	public static void set_crawler_newFileInterval( long interval ) {
		updateXMLValue( "crawler_newFileInterval", String.valueOf( interval ) );
		crawler_newFileInterval = interval;
	}

	// which tags are crawled
	private static ArrayList <String>	crawler_hashtags	= null;

	public static ArrayList <String> get_crawler_hashtags() {
		if ( crawler_hashtags == null ) {
			crawler_hashtags = readValuesFromXMLFile( "hashtag" );
		}
		return crawler_hashtags;
	}

	public static void set_crawler_hashtags( ArrayList <String> hashtags ) {
		crawler_hashtags = hashtags;
		deleteValueFromXMLFile( "hashtag", null );
		for ( String s : hashtags ) {
			add_crawler_hashtag( s );
		}
	}

	public static void add_crawler_hashtag( String hashtag ) {
		System.out.println("new mission: " + hashtag);
		crawler_hashtags.add( hashtag );
		writeValueToXMLFile( "search", "hashtag", hashtag );
	}
	
	public static void remove_crawler_hashtags (String[] hashtags) {
		
		for (int i = 0; i < hashtags.length; i++) {
			crawler_hashtags.remove( hashtags[i] );			
		}
	
		updateHashtags();
	}
	
	private static void updateHashtags() {
		if(crawler_hashtags!=null) {
			deleteValueFromXMLFile( "hashtag", null );
			for (String s : crawler_hashtags) {
				writeValueToXMLFile( "search", "hashtag", s );
			}
		}
	}

	/**
	 * API Access
	 */

	/**
	 * Twitter
	 */

	// credentials

	private static String	twitter_ConsumerKey	= null;

	public static String get_twitter_ConsumerKey() {
		if ( twitter_ConsumerKey == null ) {
			twitter_ConsumerKey = readValueFromXMLFile( "twitter_ConsumerKey" );
		}
		return twitter_ConsumerKey;
	}

	public static void set_twitter_ConsumerKey( String key ) {
		twitter_ConsumerKey = key;
		updateXMLValue( "twitter_ConsumerKey", key );
	}

	private static String	twitter_ConsumerSecret	= null;

	public static String get_twitter_ConsumerSecret() {
		if ( twitter_ConsumerSecret == null ) {
			twitter_ConsumerSecret = readValueFromXMLFile( "twitter_ConsumerSecret" );
		}
		return twitter_ConsumerSecret;
	}

	public static void set_twitter_ConsumerSecret( String secret ) {
		twitter_ConsumerSecret = secret;
		updateXMLValue( "twitter_ConsumerSecret", secret );
	}

	private static String	twitter_AccessToken	= null;

	public static String get_twitter_AccessToken() {
		if ( twitter_AccessToken == null ) {
			twitter_AccessToken = readValueFromXMLFile( "twitter_AccessToken" );
		}
		return twitter_AccessToken;
	}

	public static void set_twitter_AccessToken( String token ) {
		twitter_AccessToken = token;
		updateXMLValue( "twitter_AccessToken", token );
	}

	private static String	twitter_AccessTokenSecret	= "";

	public static String get_twitter_AccessTokenSecret() {
		if ( twitter_AccessTokenSecret == null ) {
			twitter_AccessTokenSecret = readValueFromXMLFile( "twitter_AccessTokenSecret" );
		}
		return twitter_AccessTokenSecret;
	}

	public static void set_twitter_AccessTokenSecret( String secret ) {
		twitter_AccessTokenSecret = secret;
		updateXMLValue( "twitter_AccessTokenSecret", secret );
	}

	// rest restriction

	private static int	twitter_result_restriction	= -1;

	public static int get_twitter_result_restriction() {
		if ( twitter_result_restriction < 0 ) {
			twitter_result_restriction = Integer.parseInt( readValueFromXMLFile( "twitter_result_restriction" ) );
		}
		return twitter_result_restriction;
	}

	public static void set_twitter_result_restriction( int max ) {
		twitter_result_restriction = max;
		updateXMLValue( "twitter_result_restriction", String.valueOf( max ) );
	}

	/**
	 * input / output
	 */
	private static String	location_tweets	= null;

	public static String get_location_tweets() {
		if ( location_tweets == null ) {
			location_tweets = readValueFromXMLFile( "location_tweets" );
		}
		return location_tweets;
	}

	public static void set_location_tweets( String location ) {
		location_tweets = location;
		updateXMLValue( "location_tweets", location );
	}

	private static String	location_results	= null;

	public static String get_location_results() {
		if ( location_results == null ) {
			location_results = readValueFromXMLFile( "location_results" );
		}
		return location_results;
	}

	public static void set_location_results( String location ) {
		location_results = location;
		updateXMLValue( "location_results", location );
	}
	
	private static String location_CAS = null;
	
	public static String get_location_CAS() {
		if ( location_CAS == null ) {
			location_CAS = readValueFromXMLFile( "location_CAS" );
		}
		return location_CAS;
	}

	public static void set_location_CAS( String location ) {
		location_CAS = location;
		updateXMLValue( "location_CAS", location );
	}
	
	/**
	 * get properties from project_config.xml
	 */

	public static String readValueFromXMLFile( String tagname ) {
		return XMLFileManager.getNodeValuesByTag( xmlPath, tagname ).get( 0 );
	}

	public static ArrayList <String> readValuesFromXMLFile( String tagname ) {
		return XMLFileManager.getNodeValuesByTag( xmlPath, tagname );
	}

	/**
	 * writes a node to certain tag
	 * 
	 * @param parentNodesTagname
	 *            (optional) null = firstElement
	 * @param tagname
	 * @param value
	 */
	public static void writeValueToXMLFile( String parentNodesTagname, String tagname, String value ) {
		try {
			XMLFileManager.appendValue( xmlPath, tagname, parentNodesTagname, value );
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
	}

	/**
	 * deletes a node from config file
	 * 
	 * @param tagname
	 *            the nodes tag
	 * @param value
	 *            (optional) null = delete all nodes of specified tag
	 */
	public static void deleteValueFromXMLFile( String tagname, String value ) {
		try {
			XMLFileManager.removeNode( xmlPath, tagname, value );
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
	}

	public static void updateXMLValue( String tagname, String value ) {
		try {
			XMLFileManager.updateNodeValue( xmlPath, tagname, null, value );
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
	}

	public static void updateXMLValue( String tagname, String value, String oldValue ) {
		try {
			XMLFileManager.updateNodeValue( xmlPath, tagname, oldValue, value );
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
	}

}
