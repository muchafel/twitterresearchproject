package ude.SocialMediaExplorer.client.DataHelper;

import ude.SocialMediaExplorer.client.gui.MainPage;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class DataHelperCallback implements AsyncCallback<String[]> {

	private MainPage mainpage;
	
	public DataHelperCallback(MainPage mainpage) {
		this.mainpage = mainpage;
	}
	
	public void onFailure(Throwable caught) {
		Window.alert(caught.getMessage());
	}

	public void onSuccess(String[] result) {
		mainpage.addSeries(result);
	}

}
