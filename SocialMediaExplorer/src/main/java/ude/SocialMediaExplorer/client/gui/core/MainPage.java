package ude.SocialMediaExplorer.client.gui.core;

import java.util.ArrayList;

import ude.SocialMediaExplorer.client.gui.errorhandling.SimpleErrorHandling;
import ude.SocialMediaExplorer.client.rmi.IDataHelperService;
import ude.SocialMediaExplorer.client.rmi.IDataHelperServiceAsync;
import ude.SocialMediaExplorer.shared.exchangeFormat.ClusterElement;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ListBox;
import com.github.gwtbootstrap.client.ui.Row;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MainPage extends Composite {

	private static MainPageUiBinder uiBinder = GWT.create( MainPageUiBinder.class );

	interface MainPageUiBinder extends UiBinder<Widget, MainPage> {}

	//////////////////////////

	@UiField
	ListBox listSeries;
	@UiField
	Button btnGo;
	@UiField
	Row visualizationEl;
	ArrayList<String> names;
	public boolean loading = false;

	private IDataHelperServiceAsync dh = null;

	//////////////////////////

	public MainPage() {

		initWidget( uiBinder.createAndBindUi( this ) );

		try {
			dh = GWT.create( IDataHelperService.class );
			dh.getConfigHashtags( new AsyncCallback<String[]>() {

				public void onSuccess( String[] result ) {

					names = new ArrayList<String>();

					for ( int i = 0; i < result.length; i++ ) {
						names.add( result[i] );
						listSeries.addItem( result[i] );
					}

					initButton();

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


	private void initButton() {

		btnGo.addClickHandler( new ClickHandler() {

			public void onClick( ClickEvent event ) {
				try {
					int selection = listSeries.getSelectedIndex();
					String name = names.get( selection );
					createVisualization( name );
				}
				catch ( Exception e ) {
					new SimpleErrorHandling( e );
				}
			}

		} );

	}

	private void createVisualization( final String hashtag ) {

		dh.getClusters( hashtag, new AsyncCallback<ClusterElement>() {

			public void onSuccess( ClusterElement result ) {
				ClusterElement ce = result; //ClusterElement.testCE();
				ClusterVisualization cv = new ClusterVisualization( hashtag, ce );
				visualizationEl.clear();
				visualizationEl.add( cv );
				cv.draw(); //called here because JSNI needs to access the DOM - isnt possible before creating the widget

			}

			public void onFailure( Throwable caught ) {
				new SimpleErrorHandling( caught.getMessage() );
			}

		} );

	}

}
