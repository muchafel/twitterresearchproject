package ude.SocialMediaExplorer.client;

import ude.SocialMediaExplorer.client.gui.Footer;
import ude.SocialMediaExplorer.client.gui.MainPage;
import ude.SocialMediaExplorer.client.gui.Menu;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Start implements EntryPoint {

	  /**
	   * This is the entry point method.
	   */
	  public void onModuleLoad() {
		  
		RootPanel.get("menu").add(new Menu());
		  
		RootPanel.get("content").add(new MainPage());
		
		RootPanel.get("footer").add(new Footer());
		
		
	  }
}
