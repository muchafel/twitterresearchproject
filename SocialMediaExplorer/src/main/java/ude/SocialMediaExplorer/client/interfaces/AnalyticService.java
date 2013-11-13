package ude.SocialMediaExplorer.client.interfaces;

import ude.SocialMediaExplorer.shared.Response;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * GWT Interface for Remote Procedure Call (synchronous)
 * @author henrikdetjen
 *
 */
@RemoteServiceRelativePath("analysis")
public interface AnalyticService extends RemoteService{
	/**
	 * calls the hashtag analysis 
	 * @param hashtag a {String} with or without "#"
	 * @return {@link Response}
	 * @throws IllegalArgumentException
	 */
	public Response analyseHashtag(String hashtag) throws IllegalArgumentException;
}
