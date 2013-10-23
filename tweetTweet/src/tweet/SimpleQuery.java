package tweet;
 import javax.swing.JFrame;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class SimpleQuery {

	
	
	/**
	 * args[0]=r --> use args[1] to grab tweets to file (for console use)
	 */
	
	//Main class --> switch from stream to REST is not enabled
	public static void main(String[] args) {
		
		GUI gui = new GUI("TotalTweetTweet");
		ConfigurationBuilder configBuilder = new ConfigurationBuilder();
		configBuilder.setDebugEnabled(true)
		  .setOAuthConsumerKey("CR8dliXroJBFtdfVsDdsuQ")
		  .setOAuthConsumerSecret("g3QYMQDxCbvtAFFNTXAfroeKO2cKGbWyHsa6bSCUws")
		  .setOAuthAccessToken("40204501-kyDmOOh9Yqk9BBzgzT8eDKHLwocKcNirI9eqALFzo")
		  .setOAuthAccessTokenSecret("Q9ZizMtfmECcr3X6SGWB8LLqbPtLvFtFqYFQrYuzq7U");

		TwitterFactory twitterFactory = new TwitterFactory(configBuilder.build());
		Twitter twitter = twitterFactory.getInstance();
		
		Tweet tweet= new Tweet(twitter);
		Controller controller= new Controller(gui, tweet);
		Controller2 controller2= new Controller2(gui, tweet);
		 
		ControllerStream controller3= new ControllerStream(gui);
		
		if(args.length>0){
		if(args[0].equals("r")){
			controller3.grabTweets(args[1]);
		}}
		
		
	    
		
	}

}
