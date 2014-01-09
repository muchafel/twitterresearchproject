package ude.SocialMediaExplorer.client.rmi;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IDataHelperServiceAsync {
	void getConfigHashtags(AsyncCallback<String[]> callback);
}
