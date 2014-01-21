package ude.SocialMediaExplorer.client.gui.other;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class Wait extends Composite {

	private static WaitUiBinder uiBinder = GWT.create( WaitUiBinder.class );

	interface WaitUiBinder extends UiBinder<Widget, Wait> {}

	public Wait() {
		initWidget( uiBinder.createAndBindUi( this ) );
	}
}
