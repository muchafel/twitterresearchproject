package ude.SocialMediaExplorer.data.mining.twitter;

import ude.SocialMediaExplorer.Config;
import ude.SocialMediaExplorer.data.StatusList;
import ude.SocialMediaExplorer.data.mining.DataMining;
import ude.SocialMediaExplorer.data.mining.StatusMapper;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;

/**
 * gets states via twitter rest api {@link TwitterConnection}
 * @author henrikdetjen
 *
 */
public class TweetReader_REST implements DataMining {

	public StatusList getStates(String hashtag) throws Exception {
		
		StatusList sl = new StatusList();
		
		Query query = new Query(hashtag);
		QueryResult result; 
		Twitter t = TwitterConnection.get();
		
		do {
			result = t.search(query);
			for (Status s : result.getTweets()){
				sl.add(StatusMapper.fromTwitter(s));
			}
		} while ((query = result.nextQuery()) != null && sl.size() < Config.twitter_result_restriction);			
	 
		 return sl;
	}

}
