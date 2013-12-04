package ude.SocialMediaExplorer.client;

import java.awt.List;
import java.util.ArrayList;

import ude.SocialMediaExplorer.client.gui.ClusterVisualization;
import ude.SocialMediaExplorer.client.gui.Footer;
import ude.SocialMediaExplorer.client.gui.MainPage;
import ude.SocialMediaExplorer.client.gui.Menu;
import ude.SocialMediaExplorer.data.result.ResultPoolingImpl;
import ude.SocialMediaExplorer.shared.exchangeFormat.ClusterElement;
import ude.SocialMediaExplorer.shared.exchangeFormat.Sentiment;

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
		
		Sentiment s = new Sentiment();
		s.setNegative(0.5);
		s.setPositive(0);
		
		ClusterElement ce2 = new ClusterElement();
		ce2.setRoot(false);
		ce2.setLeaf(true);
		ce2.setName("cluster1");
		ce2.setSentiment(s);
		
		ClusterElement ce3 = new ClusterElement();
		ce3.setRoot(false);
		ce3.setLeaf(true);
		ce3.setName("cluster2");
		ce3.setSentiment(s);
		
		ClusterElement ce4 = new ClusterElement();
		ce4.setRoot(false);
		ce4.setLeaf(true);
		ce4.setName("cluster3");
		ce4.setSentiment(s);
		
		ArrayList<ClusterElement> l = new ArrayList<ClusterElement>();
		l.add(ce2);
		l.add(ce3);
		l.add(ce4);
		
		ClusterElement ce = new ClusterElement();
		ce.setRoot(true);
		ce.setLeaf(false);
		ce.setName("asd");
		ce.setSentiment(s);
		
		ce.setSubcluster(l); 		
	 
		RootPanel.get("content").add(new ClusterVisualization(ce));
		
	  }
}
