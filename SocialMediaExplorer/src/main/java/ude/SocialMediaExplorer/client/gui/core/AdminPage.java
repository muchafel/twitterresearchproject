package ude.SocialMediaExplorer.client.gui.core;

import java.util.ArrayList;

import ude.SocialMediaExplorer.client.gui.errorhandling.SimpleErrorHandling;
import ude.SocialMediaExplorer.client.rmi.IDataHelperService;
import ude.SocialMediaExplorer.client.rmi.IDataHelperServiceAsync;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ListBox;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class AdminPage extends Composite {


	interface AdminPageUiBinder extends UiBinder<Widget, AdminPage> {}

	private static AdminPageUiBinder uiBinder = GWT.create( AdminPageUiBinder.class );

	private IDataHelperServiceAsync dh = null;

	@UiField
	ListBox list_hashtags;
	
	@UiField
	Button btn_open_dialog_add_hashtag;
	
	@UiField
	Button btn_add_hashtag;
	
	@UiField
	TextBox input_add_hashtag;
	
	@UiField
	Button btn_remove_hashtags;
	
	@UiField
	Modal dialog_add_hashtag;


	public AdminPage() {
		
		initWidget( uiBinder.createAndBindUi( this ) );

		try {
			dh = GWT.create( IDataHelperService.class );
		}
		catch ( Exception e ) {
			new SimpleErrorHandling( e );
		}

		if ( dh != null ) {
			updateHashtagsList();
			initButtons();
		}


	}
	
	private void initButtons() {
		
		//open dialog 
		btn_open_dialog_add_hashtag.addClickHandler( new ClickHandler() {
			public void onClick( ClickEvent event ) {
				dialog_add_hashtag.show();
			}
		} );
		
		//add a new hashtag
		btn_add_hashtag.addClickHandler( new ClickHandler() {
			public void onClick( ClickEvent event ) {
				String input = input_add_hashtag.getValue();
				dh.addHashtag(input, new AsyncCallback<Boolean>() {
					
					public void onSuccess( Boolean result ) {
						updateHashtagsList();
						successfulCall();
					}
					
					public void onFailure( Throwable caught ) {
						new SimpleErrorHandling( caught.getMessage() );
					}
					
				});
				input_add_hashtag.setValue( "" );
				dialog_add_hashtag.hide();
			}
		} );
		
		//remove a hashtag
		btn_remove_hashtags.addClickHandler( new ClickHandler() {
			public void onClick( ClickEvent event ) {
				
				ArrayList<String> choices = new ArrayList<String>();
				
				for (int i = 0; i < list_hashtags.getItemCount(); i++) {
					if (list_hashtags.isItemSelected( i )) {
						choices.add( list_hashtags.getItemText( i ) );
					}
				}
				
				dh.removeHashtags( choices.toArray( new String[choices.size()] ), new AsyncCallback<Boolean>() {
					
					public void onSuccess( Boolean result ) {
						updateHashtagsList();
						successfulCall();
					}
					
					public void onFailure( Throwable caught ) {
						new SimpleErrorHandling( caught.getMessage() );
					}
				} );
				
			}
		} );
		
	}
	

	private void updateHashtagsList() {
		
		dh.getConfigHashtags( new AsyncCallback<String[]>() {

			public void onSuccess( String[] result ) {

				list_hashtags.clear();
				list_hashtags.setVisibleItemCount( result.length );
				
				for ( int i = 0; i < result.length; i++ ) {
					list_hashtags.addItem( result[i] );
				}

			}

			public void onFailure( Throwable caught ) {
				new SimpleErrorHandling( caught.getMessage() );
			}

		} );
		
	}
	
	private void successfulCall() {
		//do something on success
	}


}
