package ude.SocialMediaExplorer.client.rmi;

import ude.SocialMediaExplorer.shared.exchangeFormat.ClusterElement;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IDataHelperServiceAsync {

	void getConfigHashtags( AsyncCallback<String[]> callback );
	
	void getClusters( String hashtag, AsyncCallback<ClusterElement> callback );
	
	void addHashtag( String hashtag, AsyncCallback<Boolean> callback );
	
	void removeHashtags (String[] hashtags, AsyncCallback<Boolean> callback );
	
}
