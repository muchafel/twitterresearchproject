package ude.SocialMediaExplorer.client.rmi;

import ude.SocialMediaExplorer.shared.exchangeFormat.ClusterElement;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Defines the server interfaces for the client
 * 
 * @author henrikdetjen
 * 
 */
@RemoteServiceRelativePath( "datahelper" )
public interface IDataHelperService extends RemoteService {

	//ADMIN
	
	/**
	 * @return {@link String[]} - an array of hashtags being crawled (project_config.xml)
	 */
	String[] getConfigHashtags_actual();
	
	/**
	 * @return {@link String[]} - an array of hashtags being crawled next interval (project_config.xml)
	 */
	String[] getConfigHashtags_next();
	
	/**
	 * adds a hashtag to the project_config.xml (next hashtags)
	 * 
	 * @param hashtag
	 *            {@link String}
	 * @return if successful
	 */
	boolean addHashtag( String hashtag );

	/**
	 * removes all given hashtags from project_config.xml (next hashtags)
	 * 
	 * @param hashtags
	 *            {@link String[]}
	 * @return if successful
	 */
	boolean removeHashtags( String[] hashtags );

	/**
	 * 
	 * @return the crawler interval
	 */
	long get_Interval();
	
	/**
	 * 
	 */
	boolean set_Interval(long d);
	
	
	//ENDUSER
	
	/**
	 * @param hashtag
	 *            {@link String} a hashtag
	 * @return {@link String[]} the Files (represented by their timestamp), which where found
	 */
	String[] getPossibleFiles( String hashtag );

	/**
	 * 
	 * @param hashtag
	 *            {@link String} a hashtag
	 * @param timeStamp
	 *            {@link TimeStamp} a String in formatted in long Timestamp
	 * @return {@link ClusterElement} - analysis result of given hashtag
	 */
	String getData( String hashtag, String timeStamp );

	/**
	 * @return {@link String[]} - an array of hashtags, which have actually any analysis results (location_results/?)
	 */
	String[] getPossibleHashtags();
	

}
