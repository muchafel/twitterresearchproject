package main.client;

import main.client.gui.components.InputPanel;
import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Attitude_Explorer implements EntryPoint {
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		new InputPanel();
		System.out.println("starting client...");
		
	}
}
