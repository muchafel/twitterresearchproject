package main.client.gui.events;

import main.client.gui.components.PopUpDialog;
import main.client.interfaces.AnalyticService;
import main.client.interfaces.AnalyticServiceAsync;
import main.shared.Response;

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

			@Override
			public void onFailure(Throwable caught) {
				executing = false;
				new PopUpDialog("failed", caught.getMessage());	
			}

			@Override
			public void onSuccess(Response result) {
				executing = false;
				new PopUpDialog("sucess", result.toString());
				
			}
			
		});
	}
	
}
