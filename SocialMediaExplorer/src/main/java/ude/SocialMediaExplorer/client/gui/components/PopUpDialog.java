package ude.SocialMediaExplorer.client.gui.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PopUpDialog {
	public PopUpDialog(String title, String msg) {
		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText(title);
		dialogBox.setAnimationEnabled(true);
		
		// We can set the id of a widget by accessing its Element
		final Button closeButton = new Button("Close");
		closeButton.getElement().setId("closeButton");
		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.getElement().removeFromParent();
			}
		});
		
		//for text = new Widget
		final Label label = new Label();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(label);
		dialogVPanel.add(new HTML(msg));
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		
		dialogBox.setWidget(dialogVPanel);
		
		//add to UI
		RootPanel.get().add(dialogBox);
	}
}
