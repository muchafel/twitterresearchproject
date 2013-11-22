package ude.SocialMediaExplorer.data.post.providing.live;

import ude.SocialMediaExplorer.Config;
import ude.SocialMediaExplorer.data.post.PostList;
import ude.SocialMediaExplorer.data.post.providing.IPostProviding;
import ude.SocialMediaExplorer.data.post.providing.PostConverter;
import ude.SocialMediaExplorer.data.utils.connections.TwitterConnector;
import ude.SocialMediaExplorer.data.utils.time.TimeSpan;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;

/**
 * gets posts via twitter rest api {@link TwitterConnector}
 * @author henrikdetjen
 *
 */
public class TwitterRESTReader implements IPostProviding {

	public PostList getPosts(String hashtag) throws Exception {
		
		PostList sl = new PostList();
		
		Query query = new Query(hashtag);
		QueryResult result; 
		Twitter t = TwitterConnector.get(false);
		
		do {
			result = t.search(query);
			for (Status s : result.getTweets()){
				sl.add(PostConverter.fromTwitter(s));
			}
		} while ((query = result.nextQuery()) != null && sl.size() < Config.twitter_result_restriction);			
	 
		 return sl;
	}

	public PostList getPosts(String hashtag, TimeSpan timespan) throws Exception {
		return getPosts(hashtag);
	}

}
