package ude.SocialMediaExplorer.client.gui.other;

import com.github.gwtbootstrap.client.ui.Paragraph;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class Wait extends Composite {

	private static WaitUiBinder uiBinder = GWT.create( WaitUiBinder.class );

	interface WaitUiBinder extends UiBinder<Widget, Wait> {}

	@UiField
	Paragraph wait_text;
	
	public Wait(String msg) {
		initWidget( uiBinder.createAndBindUi( this ) );
		wait_text.setText( msg ); 
	}
	public Wait() {
		initWidget( uiBinder.createAndBindUi( this ) );
	}
}
