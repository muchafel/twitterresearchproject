package datamining;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import datamining.GUI;


import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class ControllerStream implements ActionListener {

	private GUI view;
	private Tweet tweet;
	private SimpleFileWriter writer;
	private StatusListener listener;
	private List<String> list;
	
	// enables live-grabbing
	public ControllerStream( GUI view){
		this.view=view;
		view.addActionListener3(this);
		
		writer= new SimpleFileWriter();
		list= new ArrayList<String>();
		listener= new CustomTwitterStreamListener(list,writer);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String input= view.getInput();
//		writer.write(input);
		
		
		//new query
		FilterQuery fq = new FilterQuery();
	    
		//just one qury argument--> can easily be configured
        String keywords[] = {input};

        fq.track(keywords);
        
        // builds new configbuilder (therefore switch back is unavailable
		ConfigurationBuilder configBuilder = new ConfigurationBuilder();
		configBuilder.setDebugEnabled(true)
		  .setOAuthConsumerKey("CR8dliXroJBFtdfVsDdsuQ")
		  .setOAuthConsumerSecret("g3QYMQDxCbvtAFFNTXAfroeKO2cKGbWyHsa6bSCUws")
		  .setOAuthAccessToken("40204501-kyDmOOh9Yqk9BBzgzT8eDKHLwocKcNirI9eqALFzo")
		  .setOAuthAccessTokenSecret("Q9ZizMtfmECcr3X6SGWB8LLqbPtLvFtFqYFQrYuzq7U");
		
		//new stream
		TwitterStream twitterStream = new TwitterStreamFactory(configBuilder.build()).getInstance();
        
		//adds CustomTwitterStreamListener as StatusListener
        twitterStream.addListener(listener);
        twitterStream.filter(fq); 
       // twitterStream.sample();
        
		}
	//method for console use
	public void grabTweets(String s){
		String input= s;
//		writer.write(input);
		
		FilterQuery fq = new FilterQuery();
	    
        String keywords[] = {input};

        fq.track(keywords);
        
        
		ConfigurationBuilder configBuilder = new ConfigurationBuilder();
		configBuilder.setDebugEnabled(true)
		  .setOAuthConsumerKey("CR8dliXroJBFtdfVsDdsuQ")
		  .setOAuthConsumerSecret("g3QYMQDxCbvtAFFNTXAfroeKO2cKGbWyHsa6bSCUws")
		  .setOAuthAccessToken("40204501-kyDmOOh9Yqk9BBzgzT8eDKHLwocKcNirI9eqALFzo")
		  .setOAuthAccessTokenSecret("Q9ZizMtfmECcr3X6SGWB8LLqbPtLvFtFqYFQrYuzq7U");
		
		TwitterStream twitterStream = new TwitterStreamFactory(configBuilder.build()).getInstance();
        
        twitterStream.addListener(listener);
        twitterStream.filter(fq); 
	}
	
	}

