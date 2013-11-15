package crawler;

import twitter4j.FilterQuery;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Crawler {

	static int exceptionCounter=20;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ConfigurationBuilder configBuilder = new ConfigurationBuilder();
		
		
		configBuilder.setDebugEnabled(true)
		  .setOAuthConsumerKey("CR8dliXroJBFtdfVsDdsuQ")
		  .setOAuthConsumerSecret("g3QYMQDxCbvtAFFNTXAfroeKO2cKGbWyHsa6bSCUws")
		  .setOAuthAccessToken("40204501-kyDmOOh9Yqk9BBzgzT8eDKHLwocKcNirI9eqALFzo")
		  .setOAuthAccessTokenSecret("Q9ZizMtfmECcr3X6SGWB8LLqbPtLvFtFqYFQrYuzq7U");
		
		configBuilder.setJSONStoreEnabled(true);
		TwitterStream twitterStream = new TwitterStreamFactory(configBuilder.build()).getInstance();
    
		Config config= new Config();
		//new query
		FilterQuery fq = new FilterQuery();
		    

		StatusListener listener = new CrawlerListener(config.getHashtagList());
		String[] keywordsArray = config.getHashtags();
		
		fq.track(keywordsArray);
		
		//adds CustomTwitterStreamListener as StatusListener
        twitterStream.addListener(listener);
        
       // twitterStream.filter(fq); 
        retrieve(fq,twitterStream);
        

	}
	
	public static void retrieve(FilterQuery fq, TwitterStream twitterStream ){
		try{ 
		twitterStream.filter(fq);
		}
		catch (Exception e){
			exceptionCounter--;
			if(exceptionCounter>0){
			retrieve(fq,twitterStream);}
		}
	}

}
