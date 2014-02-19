package ude.SocialMediaExplorer.client.gui.other;

import ude.SocialMediaExplorer.client.gui.core.AdminPage;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.PasswordTextBox;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;


public class Login extends Composite{

	private static LoginUiBinder uiBinder = GWT.create( LoginUiBinder.class );

	interface LoginUiBinder extends UiBinder<Widget, Login> {}

	public Login() {
		initWidget( uiBinder.createAndBindUi( this ) );
	}

	@UiField
	TextBox name;
	
	@UiField 
	PasswordTextBox pass;
	
	@UiField
	Button button_login;
	@UiHandler( "button_login" )
	void onClick( ClickEvent e ) {
		checkAuth();
	}
	
	public Login( String firstName ) {
		initWidget( uiBinder.createAndBindUi( this ) );
	}

	private void checkAuth() {
		if (name.getValue().equals( "test" ) && pass.getValue().equals( "test" ) ) {
			pass.setText( "" );
			name.setText( "" );
			RootPanel.get( "content" ).clear();
			RootPanel.get( "content" ).add( new AdminPage() );
		}else {
			pass.setText( "" );
			name.setText( "" );
		}
	}

}
