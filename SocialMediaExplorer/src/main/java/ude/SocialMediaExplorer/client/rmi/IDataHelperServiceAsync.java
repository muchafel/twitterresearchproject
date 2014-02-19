package ude.SocialMediaExplorer.client.rmi;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * the Asynchronous match of IDataHelperService
 * 
 * @author henrikdetjen
 * 
 */
public interface IDataHelperServiceAsync {

	void getPossibleHashtags( AsyncCallback<String[]> callback );

	void getPossibleFiles( String hashtag, AsyncCallback<String[]> callback );

	void getConfigHashtags_next( AsyncCallback<String[]> callback );

	void getConfigHashtags_actual( AsyncCallback<String[]> callback );
	
	void getData( String hashtag, String timeStamp, AsyncCallback<String> callback );

	void addHashtag( String hashtag, AsyncCallback<Boolean> callback );

	void removeHashtags( String[] hashtags, AsyncCallback<Boolean> callback );

	void get_Interval( AsyncCallback<Long> callack );
	
	void set_Interval( long d, AsyncCallback<Boolean> callack );
	
}
