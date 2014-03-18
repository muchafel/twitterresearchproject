package ude.SocialMediaExplorer.client.gui.core;

import java.util.ArrayList;

import ude.SocialMediaExplorer.client.gui.errorhandling.SimpleErrorHandling;
import ude.SocialMediaExplorer.client.gui.other.Wait;
import ude.SocialMediaExplorer.client.rmi.IDataHelperService;
import ude.SocialMediaExplorer.client.rmi.IDataHelperServiceAsync;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ListBox;
import com.github.gwtbootstrap.client.ui.Row;
import com.github.gwtbootstrap.client.ui.WellForm;
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

/**
 * the gui for selection of a result to load
 * 
 * @author henrikdetjen
 * 
 */
public class HashTagSelection extends Composite {

	private static HashTagSelectionUiBinder uiBinder = GWT.create( HashTagSelectionUiBinder.class );

	interface HashTagSelectionUiBinder extends UiBinder<Widget, HashTagSelection> {}

	//////////////////////////

	/**
	 * the list for hashtag selection
	 */
	//initialized in constructor
	@UiField
	ListBox listSeries;

	ArrayList<String> names; //holds actual list of hashtags

	//if one clicks in hashtaglist -> hide other menus 
	@UiHandler( "listSeries" )
	void onClick( ClickEvent e ) {
		timeSelection.setVisible( false );
		gogogo.setVisible( false );
	}

	@UiField
	Button btnOk;

	/**
	 * the buttons for a selection of a file
	 */
	@UiField
	WellForm times; //files timestamps

	ArrayList<Button> possibleFiles; //holds actual selection buttons in 

	@UiField
	Button btnGo;

	/**
	 * the 3 main panels (representing the 3 selection steps)
	 */

	@UiField
	Row gogogo;
	@UiField
	Row timeSelection;
	@UiField
	Row hashtagSelection;

	/**
	 * rest
	 */
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

		// STEP 1 load all possible hashtags with results (by reultsfolders names)
		try {
			dh = GWT.create( IDataHelperService.class );

			RootPanel.get( "content" ).add( wait );

			dh.getPossibleHashtags( new AsyncCallback<String[]>() {

				public void onSuccess( String[] result ) {

					names = new ArrayList<String>();

					for ( int i = 0; i < result.length; i++ ) {
						if ( !result[i].contains( ".svn" ) && !result[i].contains( ".mvn" ) ) {
							names.add( result[i] );
							listSeries.addItem( result[i] );
						}
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

	private String getSelectedHashtag() {
		if ( names != null ) { return names.get( listSeries.getSelectedIndex() ); }
		return "";
	}

	//////////////////////////

	// STEP 2 (takes selected hashtag and loads the file/date selection)
	@UiHandler( "btnOk" )
	void time( ClickEvent event ) {

		wait = new Wait( "loading..." );
		RootPanel.get( "content" ).add( wait );
		wait.setVisible( true );

		try {
			dh.getPossibleFiles( getSelectedHashtag(), new AsyncCallback<String[]>() {

				public void onSuccess( String[] result ) {
					times.clear();
					possibleFiles = new ArrayList<Button>();
					for ( String date : result ) {

						if ( !date.contains( ".svn" ) && !date.contains( ".mvn" ) ) {

							final Button b = new Button();
							possibleFiles.add( b );
							String btn_text = date;
							btn_text = makeDateString( btn_text );
							b.setText( btn_text );
							b.setName( date );
							b.addClickHandler( new ClickHandler() {

								public void onClick( ClickEvent event ) {
									for ( Button btn : possibleFiles ) {
										btn.setActive( false );
									}
									b.setActive( true );
									gogogo.setVisible( true );
								}
							} );

							times.add( b );

						}
					}

					wait.setVisible( false );
					timeSelection.setVisible( true );
				}

				public void onFailure( Throwable caught ) {

					wait.setVisible( false );
					hashtagSelection.setVisible( true );
					new SimpleErrorHandling( caught.getLocalizedMessage() );

				}
			} );

		}
		catch ( Exception e ) {
			new SimpleErrorHandling( e );
		}


	}

	//////////////////////////


	//STEP 3 load the selected file and create visualization 
	@UiHandler( "btnGo" )
	void draw( ClickEvent event ) {
		try {
			createVisualization( getSelectedHashtag(), getSelectedDate() );
		}
		catch ( Exception e ) {
			new SimpleErrorHandling( e );
		}
	}

	private String getSelectedDate() {
		for ( Button b : possibleFiles ) {
			if ( b.isActive() ) { return b.getName().replace( " ", "" ); }
		}
		return "";
	}

	private void createVisualization( final String hashtag, final String date ) {

		RootPanel.get( "content" ).clear();
		RootPanel.get( "content" ).add( new Wait( "loading..." ) );
		System.out.println( date );
		dh.getData( hashtag, date, new AsyncCallback<String>() {

			public void onSuccess( String result ) {
				if ( result != null ) {
					ClusterVisualization cv = new ClusterVisualization( hashtag, result );
					RootPanel.get( "content" ).clear();
					RootPanel.get( "content" ).add( cv );
					cv.draw(); //called here because JSNI needs to access the DOM - isnt possible before creating the widget
				}
				else {
					RootPanel.get( "content" ).clear();
					new SimpleErrorHandling( "Could not create visualization: Clusters are null." );
				}
			}

			public void onFailure( Throwable caught ) {
				RootPanel.get( "content" ).clear();
				new SimpleErrorHandling( caught.getMessage() );
			}
		} );

	}

	private String makeDateString( String in ) {
		String date = in.split( "_" )[0];
		String year = date.substring( 0, 4 );
		String month = date.substring( 4, 6 );
		String day = date.substring( 6, 8 );
		return day + "." + month + "." + year;
	}

}
