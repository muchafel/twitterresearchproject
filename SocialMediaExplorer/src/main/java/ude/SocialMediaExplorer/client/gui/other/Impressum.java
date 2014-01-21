package ude.SocialMediaExplorer.client.gui.other;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class Impressum extends Composite {

	private static ImpressumUiBinder	uiBinder	= GWT.create( ImpressumUiBinder.class );

	interface ImpressumUiBinder extends UiBinder<Widget, Impressum> {}

	public Impressum() {
		initWidget( uiBinder.createAndBindUi( this ) );
	}


}
