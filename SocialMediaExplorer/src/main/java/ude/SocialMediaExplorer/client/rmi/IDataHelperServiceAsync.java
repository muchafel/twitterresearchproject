package ude.SocialMediaExplorer.client.rmi;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IDataHelperServiceAsync {
	void getConfigHashtags(AsyncCallback<ArrayList<String>> callback);
}
