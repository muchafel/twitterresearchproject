package ude.SocialMediaExplorer.client.rmi;

import ude.SocialMediaExplorer.shared.exchangeFormat.ClusterElement;

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

	void getConfigHashtags( AsyncCallback<String[]> callback );

	void getClusters( String hashtag, String timeStamp, AsyncCallback<ClusterElement> callback );

	void addHashtag( String hashtag, AsyncCallback<Boolean> callback );

	void removeHashtags( String[] hashtags, AsyncCallback<Boolean> callback );

}
