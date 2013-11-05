package main.client.interfaces;

import com.google.gwt.user.client.rpc.AsyncCallback;
import main.shared.Response;

/**
 * GWT Interface for Remote Procedure Call (asynchronous)
 * @author henrikdetjen
 *
 * The async counterpart of <code>AnalyticService</code>.
 *
 */
public interface AnalyticServiceAsync {
	/**
	 * calls the hashtag analysis 
	 * @param hashtag a {String} with or without "#"
	 * @return {@link Response}
	 * @throws IllegalArgumentException
	 */
	public void analyseHashtag(String hashtag, AsyncCallback<Response> callback) throws IllegalArgumentException;
}
