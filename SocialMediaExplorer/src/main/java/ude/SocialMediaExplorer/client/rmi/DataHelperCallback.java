package ude.SocialMediaExplorer.client.rmi;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class DataHelperCallback implements AsyncCallback<String[]> {
	
	public void onFailure(Throwable caught) {
		Window.alert(caught.getMessage());
	}

	public void onSuccess(String[] result) {
	}

}
