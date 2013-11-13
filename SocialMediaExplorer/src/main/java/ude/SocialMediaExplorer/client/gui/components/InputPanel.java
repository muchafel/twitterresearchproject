package ude.SocialMediaExplorer.client.gui.components;

import ude.SocialMediaExplorer.client.gui.events.Event_CallAnalysis;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class InputPanel{

	Button sendButton;
	TextBox nameField;
	Label errorLabel;
	
	public InputPanel() {
		
		sendButton = new Button("Send");
		nameField = new TextBox();
		errorLabel = new Label();
		
		nameField.setText("#");
		// We can add style names to widgets
		sendButton.addStyleName("sendButton");
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);
		
		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create a handler for the sendButton and nameField
		sendButton.addClickHandler(new Event_CallAnalysis());
		nameField.addKeyUpHandler(new Event_CallAnalysis());
	
	}
	
}
