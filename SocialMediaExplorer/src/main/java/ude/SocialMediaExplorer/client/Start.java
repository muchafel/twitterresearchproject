package ude.SocialMediaExplorer.client;

import ude.SocialMediaExplorer.client.gui.components.InputPanel;
import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Start implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		new InputPanel();
		System.out.println("starting client...");
		
	}
}
