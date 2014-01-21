package ude.SocialMediaExplorer.client.gui.other;

import ude.SocialMediaExplorer.client.gui.core.AdminPage;
import ude.SocialMediaExplorer.client.gui.core.MainPage;

import com.github.gwtbootstrap.client.ui.NavLink;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Menu extends Composite {

	interface MenuUiBinder extends UiBinder<Widget, Menu> {}


	private static MenuUiBinder uiBinder = GWT.create( MenuUiBinder.class );

	@UiField
	NavLink home;
	@UiField
	NavLink admin;
	@UiField
	NavLink wait;


	public Menu() {
		initWidget( uiBinder.createAndBindUi( this ) );

		home.addClickHandler( new ClickHandler() {

			public void onClick( ClickEvent event ) {
				RootPanel.get( "content" ).clear();
				RootPanel.get( "content" ).add( new MainPage() );
			}

		} );

		admin.addClickHandler( new ClickHandler() {

			public void onClick( ClickEvent event ) {
				RootPanel.get( "content" ).clear();
				RootPanel.get( "content" ).add( new AdminPage() );
			}

		} );

		wait.addClickHandler( new ClickHandler() {

			public void onClick( ClickEvent event ) {
				RootPanel.get( "content" ).clear();
				RootPanel.get( "content" ).add( new Wait() );
			}

		} );

	}

}
