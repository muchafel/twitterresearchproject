package ude.SocialMediaExplorer.client.gui.other;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class Help extends Composite {

	private static HelpUiBinder uiBinder = GWT.create( HelpUiBinder.class );

	interface HelpUiBinder extends UiBinder<Widget, Help> {}

	public Help() {
		initWidget( uiBinder.createAndBindUi( this ) );
	}

}
