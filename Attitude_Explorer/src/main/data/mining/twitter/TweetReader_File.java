package main.data.mining.twitter;

import org.mortbay.util.ajax.JSON;

import main.Config;
import main.data.StatusList;
import main.data.mining.AbstractTextFileHandler;
import main.data.mining.DataMining;

/**
 * reads states from a file
 * @author henrikdetjen
 *
 */
public class TweetReader_File extends AbstractTextFileHandler implements DataMining{

	@Override
	public StatusList getStates(String hashtag) throws Exception {
		StatusList result = new StatusList();
		result = (StatusList) JSON.parse(this.readTextFile(Config.location_tweets + hashtag + ".json"));
		return result;
	}

}
