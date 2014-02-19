package ude.SocialMediaExplorer.client.gui.core;

import java.util.ArrayList;

import ude.SocialMediaExplorer.client.gui.errorhandling.SimpleErrorHandling;
import ude.SocialMediaExplorer.client.rmi.IDataHelperService;
import ude.SocialMediaExplorer.client.rmi.IDataHelperServiceAsync;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ListBox;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.Paragraph;
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

	@UiField
	ListBox list_hashtags_actual;

	@UiField
	Paragraph input_interval;

	@UiField
	Button button_change_interval;

	@UiField
	Modal dialog_set_interval;

	@UiField
	TextBox input_set_interval;
	@UiField
	Button btn_set_interval;

	public AdminPage() {

		initWidget( uiBinder.createAndBindUi( this ) );

		try {
			dh = GWT.create( IDataHelperService.class );
		}
		catch ( Exception e ) {
			new SimpleErrorHandling( e );
		}

		updateHashtagsLists();
		initButtons();
		updateInterval();


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

				if ( dh != null ) {

					String input = input_add_hashtag.getValue();

					if ( input != null && input.length() > 0 ) {
						dh.addHashtag( input, new AsyncCallback<Boolean>() {

							public void onSuccess( Boolean result ) {
								if ( result == true ) {
									updateHashtagsLists();
									successfulCall();
								}
							}

							public void onFailure( Throwable caught ) {
								new SimpleErrorHandling( caught.getMessage() );
							}

						} );
					}

					input_add_hashtag.setValue( "" );
					dialog_add_hashtag.hide();

				}

			}
		} );

		//remove a hashtag
		btn_remove_hashtags.addClickHandler( new ClickHandler() {

			public void onClick( ClickEvent event ) {

				if ( dh != null ) {
					ArrayList<String> choices = new ArrayList<String>();

					for ( int i = 0; i < list_hashtags.getItemCount(); i++ ) {
						if ( list_hashtags.isItemSelected( i ) ) {
							choices.add( list_hashtags.getItemText( i ) );
						}
					}

					if ( choices.size() > 0 ) {
						dh.removeHashtags( choices.toArray( new String[choices.size()] ), new AsyncCallback<Boolean>() {

							public void onSuccess( Boolean result ) {
								if ( result == true ) {
									updateHashtagsLists();
									successfulCall();
								}
							}

							public void onFailure( Throwable caught ) {
								new SimpleErrorHandling( caught.getMessage() );
							}
						} );

					}
				}

			}
		} );

		btn_set_interval.addClickHandler( new ClickHandler() {

			public void onClick( ClickEvent event ) {

				set_interval();

			}

		} );

		button_change_interval.addClickHandler( new ClickHandler() {

			public void onClick( ClickEvent event ) {
				dialog_set_interval.show();
			}

		} );

	}


	private void updateHashtagsLists() {

		if ( dh != null ) {

			dh.getConfigHashtags_actual( new AsyncCallback<String[]>() {

				public void onSuccess( String[] result ) {
					successfulCall();
					
					if ( result.length > 0 ) {
						list_hashtags_actual.clear();
						list_hashtags_actual.setVisibleItemCount( result.length );

						for ( int i = 0; i < result.length; i++ ) {
							list_hashtags_actual.addItem( result[i] );
						}
					}

				}

				public void onFailure( Throwable caught ) {
					new SimpleErrorHandling( caught.getMessage() );
				}

			} );

			dh.getConfigHashtags_next( new AsyncCallback<String[]>() {

				public void onSuccess( String[] result ) {
					successfulCall();
					
					if ( result.length > 0 ) {
						list_hashtags.clear();
						list_hashtags.setVisibleItemCount( result.length );

						for ( int i = 0; i < result.length; i++ ) {
							list_hashtags.addItem( result[i] );
						}
					}

				}

				public void onFailure( Throwable caught ) {
					new SimpleErrorHandling( caught.getMessage() );
				}

			} );

		}
	}

	private void updateInterval() {

		if ( dh != null ) {
			dh.get_Interval( new AsyncCallback<Long>() {

				public void onSuccess( Long result ) {
					successfulCall();
					
					if ( result >= 0 ) {
						input_interval.setText( String.valueOf( ( result / 1000 / 60 ) ) );
					}
				}

				public void onFailure( Throwable caught ) {
					new SimpleErrorHandling( caught.getMessage() );
				}

			} );
		}
	}

	private void set_interval() {
		dialog_set_interval.hide();
		if(dh != null) {
			long value = Long.valueOf( input_set_interval.getText() );
			value = value * 60 * 1000;//convert min to ms
			if ( value > 0 ) {
				dh.set_Interval( value, new AsyncCallback<Boolean>() {
					
					public void onSuccess( Boolean result ) {
						successfulCall();
						if ( result == true ) {
							updateInterval();
						}
					}
					
					public void onFailure( Throwable caught ) {
						new SimpleErrorHandling( caught.getMessage() );
					}
					
				} );
			}	
		}
	}

	private void successfulCall() {
		//do something on success
	}
}
