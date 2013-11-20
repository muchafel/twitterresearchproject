package ude.SocialMediaExplorer.data.providing;

import ude.SocialMediaExplorer.data.model.Post;

/**
 * contains methods for creation of {@link Post} objects from different data formats
 * @author henrikdetjen
 *
 */
public class PostConverter {
	/**
	 * converts twitter data into project data
	 * @param s4j the {@link twitter4j.Status}
	 * @return {@link Post}
	 */
	public static Post fromTwitter(twitter4j.Status s4j){
		Post s = new Post();
		s.setMessage(s4j.getText());
		s.setUser(s4j.getUser().getName());
		s.setShared((int) s4j.getRetweetCount());
		return s;
	}
}
