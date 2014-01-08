package ude.SocialMediaExplorer.client.gui.core;

import java.util.ArrayList;

import ude.SocialMediaExplorer.client.gui.errorhandling.SimpleErrorHandling;
import ude.SocialMediaExplorer.shared.exchangeFormat.ClusterElement;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ListBox;
import com.github.gwtbootstrap.client.ui.Row;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MainPage extends Composite {

	private static MainPageUiBinder uiBinder = GWT
			.create(MainPageUiBinder.class);

	interface MainPageUiBinder extends UiBinder<Widget, MainPage> {
	}
	
	//////////////////////////

	@UiField ListBox listSeries;
	@UiField Button btnGo;
	@UiField Row visualizationEl;
	ArrayList<String> names;
	public boolean loading = false;
	
	//////////////////////////
	
	public MainPage() {
		initWidget(uiBinder.createAndBindUi(this));
		
		names = getNames();
		for (String name : names) {
			listSeries.addItem(name);
		}
		
		initButton();
		
	}
	
	//////////////////////////
	
	private ArrayList<String> getNames(){
		//TODO: Replayce with RMI: something like PROXY.GET_POSSIBLE_HASHTAGS: ArrayList<String>
		ArrayList<String> names = new ArrayList<String>();
		names.add("tatort");
		names.add("halligalli");
		names.add("tvog");
		names.add("sterntv");
		names.add("berlintagundnacht");
		return names;
	}
	
	private void initButton(){
		
		btnGo.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				try{
					int selection = listSeries.getSelectedIndex();
					String name = names.get(selection);
					createVisualization(name);
				}catch(Exception e){
					new SimpleErrorHandling(e);
				}
			}
			
		});
	}
	
	private void createVisualization(String hashtag){
		//TODO: replace with RMI: PROXY.GET_CLUSTERS(String): String
		ClusterElement ce = ClusterElement.testCE();
		ClusterVisualization cv = new ClusterVisualization(hashtag, ce);
		visualizationEl.clear();
		visualizationEl.add(cv);
		cv.draw(); //called here because JSNI needs to access the DOM - isnt possible before creating the widget
	}
	
}