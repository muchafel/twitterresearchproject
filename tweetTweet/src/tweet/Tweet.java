package tweet;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

//handles REST
public class Tweet {
	private Twitter twitter;

	public Tweet(Twitter twitter){
		this.twitter=twitter;
	}
	// gets max 15 tweets
	public List<String> getTweets(String hashtag){
		List<String> list= new ArrayList<String>();
		Query query = new Query(hashtag);
	    QueryResult result;
		try {
			result = twitter.search(query);
			int n=0;
			 for (Status status : result.getTweets()) {
				 n++;
			        list.add(n+". @" + status.getUser().getScreenName() + " : " + status.getText() );
			    }
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// gets all available tweets
	public List<String> getTweetsLong(String hashtag){
		List<String> list= new ArrayList<String>();
		try{
		Query query = new Query(hashtag);
	    QueryResult result;
	    do {
            result = twitter.search(query);
            List<Status> tweets = result.getTweets();
            for (Status tweet : tweets) {
            	list.add("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
            }
        } while ((query = result.nextQuery()) != null);
//        System.exit(0);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return list;
	}
}
