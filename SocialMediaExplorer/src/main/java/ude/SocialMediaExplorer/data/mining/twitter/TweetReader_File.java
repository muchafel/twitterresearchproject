package ude.SocialMediaExplorer.data.mining.twitter;

import org.mortbay.util.ajax.JSON;

import ude.SocialMediaExplorer.Config;
import ude.SocialMediaExplorer.data.StatusList;
import ude.SocialMediaExplorer.data.mining.AbstractTextFileHandler;
import ude.SocialMediaExplorer.data.mining.DataMining;

/**
 * reads states from a file
 * @author henrikdetjen
 *
 */
public class TweetReader_File extends AbstractTextFileHandler implements DataMining{

	public StatusList getStates(String hashtag) throws Exception {
		StatusList result = new StatusList();
		result = (StatusList) JSON.parse(this.readTextFile(Config.location_tweets + hashtag + ".json"));
		return result;
	}

}
