package main.data.mining;

import main.data.StatusList;

/**
 * Interface for grabbing data
 * @author henrikdetjen
 *
 */
public interface DataMining {

	/**
	 * get all status object for a given hashtag
	 * @param hashtag {@link String} the hashtag you want to get the states for
	 * @return {@link StatusList} a Statuslist with all states found
	 */
	public StatusList getStates(String hashtag) throws Exception;

	
}
