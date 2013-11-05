package main.data.mining.twitter;

import main.Config;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Establishes a connection and necessary authentification to twitters api via {@link  #get()} 
 * for multiple access cast into an {@link Twitter} instance
 * @author henrikdetjen
 *
 */
public class TwitterConnection {

	private static Twitter con = null;
	/**
	 * get the connection (Singelton)
	 * @return {@link Twitter} object
	 */
	public static Twitter get(){
		if (con == null){
			ConfigurationBuilder configBuilder = new ConfigurationBuilder();
		    configBuilder.setDebugEnabled(true)
		      .setOAuthConsumerKey(Config.twitter_ConsumerKey)
		      .setOAuthConsumerSecret(Config.twitter_ConsumerSecret)
		      .setOAuthAccessToken(Config.twitter_AccessToken)
		      .setOAuthAccessTokenSecret(Config.twitter_AccessTokenSecret);

		    TwitterFactory twitterFactory = new TwitterFactory(configBuilder.build());
		    con = twitterFactory.getInstance();	
		}
		return con;
	}
	

	
    
}
