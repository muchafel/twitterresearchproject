package ude.SocialMediaExplorer.client.gui.other;

import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Footer extends Composite {

	private static FooterUiBinder	uiBinder	= GWT.create( FooterUiBinder.class );

	interface FooterUiBinder extends UiBinder<Widget, Footer> {}

	@UiField
	Button link_impressum;

	public Footer() {
		
		initWidget( uiBinder.createAndBindUi( this ) );
		link_impressum.addClickHandler( new ClickHandler() {
			
			public void onClick( ClickEvent event ) {
				RootPanel.get("content").clear();
				RootPanel.get("content").add( new Impressum() );
			}
		} );

	}

}
