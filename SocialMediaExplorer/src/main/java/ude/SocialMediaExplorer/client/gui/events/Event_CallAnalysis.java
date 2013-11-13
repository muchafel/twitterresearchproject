package ude.SocialMediaExplorer.client.gui.events;

import ude.SocialMediaExplorer.client.gui.components.PopUpDialog;
import ude.SocialMediaExplorer.client.interfaces.AnalyticService;
import ude.SocialMediaExplorer.client.interfaces.AnalyticServiceAsync;
import ude.SocialMediaExplorer.shared.Response;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * calls the remote Analysis via proxy (from clients UI)
 * and creates visualization
 * @author henrikdetjen
 *
 */
public class Event_CallAnalysis implements ClickHandler, KeyUpHandler{

	private boolean executing = false;
	
	/**
	 * Fired when the user clicks on the sendButton.
	 */
	public void onClick(ClickEvent event) {
		if (!executing)
		analyze();
	}

	/**
	 * Fired when the user types in the nameField.
	 */
	public void onKeyUp(KeyUpEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			if (!executing)
			analyze();
		}
	}
	
	private void analyze(){
		
		String hashtag = RootPanel.get("nameFieldContainer").getWidget(0).getElement().getNodeValue();
		
		this.executing = true;
		
		// create remote service proxy
		AnalyticServiceAsync anlyticService = GWT.create(AnalyticService.class); 
		
		anlyticService.analyseHashtag(hashtag, new AsyncCallback<Response>(){

			public void onFailure(Throwable caught) {
				executing = false;
				new PopUpDialog("failed", caught.getMessage());	
			}

			public void onSuccess(Response result) {
				executing = false;
				new PopUpDialog("sucess", result.toString());
				
			}
			
		});
	}
	
}
