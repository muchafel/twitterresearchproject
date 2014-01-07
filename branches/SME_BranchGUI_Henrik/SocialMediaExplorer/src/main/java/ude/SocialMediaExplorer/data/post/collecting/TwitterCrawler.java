package ude.SocialMediaExplorer.data.post.collecting;

import java.util.ArrayList;
import java.util.List;

import twitter4j.FilterQuery;
import twitter4j.HashtagEntity;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.json.DataObjectFactory;
import ude.SocialMediaExplorer.Config;
import ude.SocialMediaExplorer.data.utils.connections.TwitterConnector;
import ude.SocialMediaExplorer.data.utils.io.HashtagStreamWriter;

/**
 * Connect to Twitter and listen for defined hashtags, save posts to disk via {@link HashtagStreamWriter}  
 * @author michealwojatzki, henrikdetjen
 *
 */
public class TwitterCrawler extends Thread implements StatusListener{
	
	private int exceptionCounter = 20;
	private TwitterStream twitterStream;
	private ArrayList<String> hashtags;
	private List<HashtagStreamWriter> hashtagWriters;
	
	public TwitterCrawler() {
		
		hashtags = Config.crawler_hashtags;
		twitterStream = TwitterConnector.getStream(true);
		hashtagWriters= new ArrayList<HashtagStreamWriter>();
		for (String a : hashtags){
			hashtagWriters.add(new HashtagStreamWriter(a));
		}
		
	}
	
	public void run() {
		//new query
		FilterQuery fq = new FilterQuery();

		StatusListener listener = this;
		
		fq.track(hashtags.toArray(new String[hashtags.size()]));
		
		//adds CustomTwitterStreamListener as StatusListener
        twitterStream.addListener(listener);
        
       // twitterStream.filter(fq); 
        retrieve(fq,twitterStream);	
	}

	private void retrieve(FilterQuery fq, TwitterStream twitterStream ){
		try{ 
			twitterStream.filter(fq);
		}
		catch (Exception e){
			exceptionCounter--;
			if(exceptionCounter>0){
			retrieve(fq,twitterStream);}
		}
	}
	
	public void onStatus(Status status) {
        
        String username = status.getUser().getScreenName(); 

        String content = status.getText();

        System.out.println("@" +username+"   "+content);
        
        String statusJson = DataObjectFactory.getRawJSON(status);
        
	    for (HashtagStreamWriter writer : hashtagWriters){
	    	for (HashtagEntity a: status.getHashtagEntities()){
	       		 if(a.getText().toLowerCase().equals(writer.getName())){
	       			 writer.write(statusJson);
	       		 }
	       	}
	    }
	    
	}

	public void onTrackLimitationNotice(int i) {
		System.out.println("Track Limitation: " + i);
	}
	
	public void onException(Exception arg0) {
		System.out.println("Error: "+arg0.getMessage());
		try {
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		this.interrupt();
	}
	
	public void onDeletionNotice(StatusDeletionNotice arg0) {
		System.out.println("StatusDeletionNotice: "+arg0.toString());
	}
	
	public void onScrubGeo(long id, long statusId) {
		System.out.println("Got scrub_geo event userId:" + id + " upToStatusId:" + statusId);
	}
	
	public void onStallWarning(StallWarning arg0) {
		System.out.println("Got stall warning:" + arg0);
	}
	
}
