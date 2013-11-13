package ude.SocialMediaExplorer.data.mining;

import ude.SocialMediaExplorer.data.Status;

/**
 * contains methods for creation of {@link Status} objects from different data formats
 * @author henrikdetjen
 *
 */
public class StatusMapper {
	/**
	 * converts twitter data into project data
	 * @param s4j the {@link twitter4j.Status}
	 * @return {@link Status}
	 */
	public static Status fromTwitter(twitter4j.Status s4j){
		Status s = new Status();
		s.setMessage(s4j.getText());
		s.setUser(s4j.getUser().getName());
		s.setShared(s4j.getRetweetCount());
		return s;
	}
}
