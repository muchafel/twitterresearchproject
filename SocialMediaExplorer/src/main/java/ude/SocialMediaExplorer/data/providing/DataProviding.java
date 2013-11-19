package ude.SocialMediaExplorer.data.providing;

import ude.SocialMediaExplorer.data.model.PostList;
import ude.SocialMediaExplorer.data.utils.time.TimeSpan;

/**
 * Interface for grabbing data
 * @author henrikdetjen
 *
 */
public interface DataProviding {
	/**
	 * get all post objects for a given hashtag
	 * @param hashtag {@link String} the hashtag you want to get the posts for
	 * @return {@link PostList} a InfoList with all states found
	 */
	public PostList getPosts(String hashtag) throws Exception;
	/**
	 * get all post objects for a given hashtag
	 * @param hashtag {@link String} the hashtag you want to get the posts for
	 * @param timespan -> optional
	 * @return {@link PostList} a InfoList with all states found
	 */
	public PostList getPosts(String hashtag, TimeSpan timespan) throws Exception;

	
}
