package ude.SocialMediaExplorer.client.gui.errorhandling;

import com.github.gwtbootstrap.client.ui.Paragraph;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;


public class Error extends Composite {

	private static ErrorUiBinder uiBinder = GWT.create( ErrorUiBinder.class );

	interface ErrorUiBinder extends UiBinder<Widget, Error> {}

	public Error( String msg ) {
		initWidget( uiBinder.createAndBindUi( this ) );
		
		error_details.setText( msg );
		
		RootPanel.get("content").clear();
		RootPanel.get("content").add( this );
	}

	@UiField
	Paragraph error_details;


}
