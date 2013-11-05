package main.data.mining.twitter;

import main.Config;
import main.data.StatusList;
import main.data.mining.DataMining;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;

/**
 * gets states via twitter rest api {@link TwitterConnection}
 * @author henrikdetjen
 *
 */
public class TweetReader_REST implements DataMining {

	@Override
	public StatusList getStates(String hashtag) throws Exception {
		
		StatusList sl = new StatusList();
		
		Query query = new Query(hashtag);
		QueryResult result; 
		Twitter t = TwitterConnection.get();
		
		do {
			result = t.search(query);
			sl.addAll(result.getTweets());
		} while ((query = result.nextQuery()) != null && sl.size() < Config.twitter_result_restriction);			
	 
		 return sl;
	}

}
