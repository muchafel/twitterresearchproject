package ude.SocialMediaExplorer.client.gui.other;

import ude.SocialMediaExplorer.client.gui.core.HashTagSelection;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Menu extends Composite {

	interface MenuUiBinder extends UiBinder<Widget, Menu> {}


	private static MenuUiBinder uiBinder = GWT.create( MenuUiBinder.class );

	@UiField
	FocusPanel home;
	
	@UiHandler("home")
	void openMainPage(ClickEvent e) {
		RootPanel.get( "content" ).clear();
		RootPanel.get( "content" ).add( new HashTagSelection() );
	}

	@UiField
	FocusPanel admin;
	@UiHandler("admin")
	void openAuth(ClickEvent e) {
		RootPanel.get( "content" ).clear();
		RootPanel.get( "content" ).add( new Login() );
	}
	
	@UiField
	FocusPanel help;
	@UiHandler("help")
	void openTutorial(ClickEvent e) {
		RootPanel.get( "content" ).clear();
		RootPanel.get( "content" ).add( new Help() );
	}

	public Menu() {
		
		initWidget( uiBinder.createAndBindUi( this ) );

	}

}
