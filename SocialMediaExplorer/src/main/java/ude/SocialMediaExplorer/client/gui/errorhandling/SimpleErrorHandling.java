package ude.SocialMediaExplorer.client.gui.errorhandling;

public class SimpleErrorHandling {


	public SimpleErrorHandling( Exception e ) {

		//		Window.alert( e.getMessage() );

		new Error( e.getLocalizedMessage() );

		System.out.println( e.getClass() + ":\n " + e.getLocalizedMessage() );
	}

	public SimpleErrorHandling( String e ) {

		//		Window.alert( e );

		new Error( e );

		System.out.println( e );
	}

}
