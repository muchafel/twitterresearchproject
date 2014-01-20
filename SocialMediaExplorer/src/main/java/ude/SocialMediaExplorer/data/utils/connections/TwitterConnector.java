package ude.SocialMediaExplorer.data.utils.connections;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;
import ude.SocialMediaExplorer.Config;

/**
 * Establishes a connection and necessary authentification to twitters api via
 * {@link #get()} and {@link #getStream()} for a new stream
 * for multiple access cast into an {@link Twitter} instance
 * 
 * @author henrikdetjen
 * 
 */
public class TwitterConnector {

	private static Twitter			con		= null;
	private static TwitterStream	stream	= null;

	/**
	 * get the connection (Singelton)
	 * 
	 * @param json
	 *            if the stream should be the raw json objects else they are
	 *            {@twitter4j.Status} objects
	 * @return {@link Twitter} object
	 */
	public static Twitter get( boolean json ) {
		if ( con == null ) {
			ConfigurationBuilder configBuilder = new ConfigurationBuilder();
			configBuilder.setDebugEnabled( true ).setOAuthConsumerKey( Config.get_twitter_ConsumerKey() )
					.setOAuthConsumerSecret( Config.get_twitter_ConsumerSecret() )
					.setOAuthAccessToken( Config.get_twitter_AccessToken() )
					.setOAuthAccessTokenSecret( Config.get_twitter_AccessTokenSecret() );

			if ( json )
				configBuilder.setJSONStoreEnabled( true );

			TwitterFactory twitterFactory = new TwitterFactory( configBuilder.build() );
			con = twitterFactory.getInstance();
		}
		return con;
	}

	/**
	 * open a stream (Singelton)
	 * 
	 * @param json
	 *            if the stream should be the raw json objects else they are
	 *            {@twitter4j.Status} objects
	 * @return {@link TwitterStream} object
	 */
	public static TwitterStream getStream( boolean json ) {
		if ( stream == null ) {
			ConfigurationBuilder configBuilder = new ConfigurationBuilder();
			configBuilder.setDebugEnabled( true ).setOAuthConsumerKey( Config.get_twitter_ConsumerKey() )
					.setOAuthConsumerSecret( Config.get_twitter_ConsumerSecret() )
					.setOAuthAccessToken( Config.get_twitter_AccessToken() )
					.setOAuthAccessTokenSecret( Config.get_twitter_AccessTokenSecret() );

			if ( json )
				configBuilder.setJSONStoreEnabled( true );

			TwitterStreamFactory twitterFactory = new TwitterStreamFactory( configBuilder.build() );
			stream = twitterFactory.getInstance();
		}
		return stream;
	}

}
