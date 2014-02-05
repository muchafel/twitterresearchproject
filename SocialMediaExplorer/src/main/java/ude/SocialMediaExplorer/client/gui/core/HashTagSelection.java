package ude.SocialMediaExplorer.client.gui.core;

import java.util.ArrayList;

import ude.SocialMediaExplorer.client.gui.errorhandling.SimpleErrorHandling;
import ude.SocialMediaExplorer.client.gui.other.Wait;
import ude.SocialMediaExplorer.client.rmi.IDataHelperService;
import ude.SocialMediaExplorer.client.rmi.IDataHelperServiceAsync;
import ude.SocialMediaExplorer.shared.exchangeFormat.ClusterElement;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ButtonGroup;
import com.github.gwtbootstrap.client.ui.ListBox;
import com.github.gwtbootstrap.client.ui.Row;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;


public class HashTagSelection extends Composite {

	private static HashTagSelectionUiBinder uiBinder = GWT.create( HashTagSelectionUiBinder.class );

	interface HashTagSelectionUiBinder extends UiBinder<Widget, HashTagSelection> {}

	//////////////////////////

	@UiField
	ListBox listSeries;

	@UiHandler( "listSeries" )
	void onClick( ClickEvent e ) {
		timeSelection.setVisible( false );
		gogogo.setVisible( false );
	}

	@UiField
	Button btnOk;

	@UiHandler( "btnOk" )
	void time( ClickEvent event ) {
		wait.setVisible( true );
		
		// dh.getDataSets(hashtag)
		ArrayList<String> dates = new ArrayList<String>();
		dates.add( "14.12.2012" );
		dates.add( "34.12.2011" );
		dates.add( "64.26.2012" );
		dates.add( "74.04.2014" );

		times.clear();
		for ( final String date : dates ) {
			
			Button b = new Button();
			b.setText( date );
			b.addClickHandler( new ClickHandler() {
				public void onClick( ClickEvent event ) {
					gogogo.setVisible( true );
				}
			} );
			
			times.add( b );
		}

		wait.setVisible( false );
		timeSelection.setVisible( true );//onSuccess

	}
	
	@UiField
	ButtonGroup times;

	@UiField
	Button btnGo;

	@UiHandler( "btnGo" )
	void draw( ClickEvent event ) {
		try {
			int selection = listSeries.getSelectedIndex();
			String name = names.get( selection );
			createVisualization( name );
		}
		catch ( Exception e ) {
			new SimpleErrorHandling( e );
		}
	}

	@UiField
	Row gogogo;
	@UiField
	Row timeSelection;
	@UiField
	Row hashtagSelection;

	ArrayList<String> names;
	public boolean loading = false;

	private IDataHelperServiceAsync dh = null;

	Wait wait;

	//////////////////////////

	public HashTagSelection() {

		initWidget( uiBinder.createAndBindUi( this ) );

		hashtagSelection.setVisible( false );
		timeSelection.setVisible( false );
		gogogo.setVisible( false );

		wait = new Wait( "preparing..." );

		try {
			dh = GWT.create( IDataHelperService.class );

			RootPanel.get( "content" ).add( wait );

			dh.getConfigHashtags( new AsyncCallback<String[]>() {

				public void onSuccess( String[] result ) {

					names = new ArrayList<String>();

					for ( int i = 0; i < result.length; i++ ) {
						names.add( result[i] );
						listSeries.addItem( result[i] );
					}

					wait.setVisible( false );
					hashtagSelection.setVisible( true );

				}

				public void onFailure( Throwable caught ) {
					new SimpleErrorHandling( caught.getMessage() );
				}

			} );

		}
		catch ( Exception e ) {
			new SimpleErrorHandling( e );
		}


	}

	//////////////////////////

	private void createVisualization( final String hashtag ) {

		RootPanel.get( "content" ).clear();
		RootPanel.get( "content" ).add( new Wait( "loading..." ) );
		//TODO dh.getClusters(hashtag,date)...
		dh.getClusters( hashtag, new AsyncCallback<ClusterElement>() {

			public void onSuccess( ClusterElement result ) {
				ClusterElement ce = result; //ClusterElement.testCE();
				ClusterVisualization cv = new ClusterVisualization( hashtag, ce );
				RootPanel.get( "content" ).clear();
				RootPanel.get( "content" ).add( cv );
				cv.draw(); //called here because JSNI needs to access the DOM - isnt possible before creating the widget
			}

			public void onFailure( Throwable caught ) {
				new SimpleErrorHandling( caught.getMessage() );
			}
		} );

	}

}
