package ude.SocialMediaExplorer.client.gui.errorhandling;

import com.google.gwt.user.client.Window;

public class SimpleErrorHandling {

	
	public SimpleErrorHandling(Exception e){
		
		Window.alert(e.getMessage());
		
		System.out.println(e.getMessage() + ":\n ");
	}
	
	public SimpleErrorHandling(String e){
		
		Window.alert(e);
		
		System.out.println(e);
	}
	
}
