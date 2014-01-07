package ude.SocialMediaExplorer.client.gui;

import java.util.ArrayList;

import ude.SocialMediaExplorer.client.DataHelper.DataHelper;
import ude.SocialMediaExplorer.client.DataHelper.DataHelperCallback;
import ude.SocialMediaExplorer.client.DataHelper.IDataHelperServiceAsync;
import ude.SocialMediaExplorer.shared.exchangeFormat.ClusterElement;

import com.github.gwtbootstrap.client.ui.ListBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MainPage extends Composite {

	private static MainPageUiBinder uiBinder = GWT.create(MainPageUiBinder.class);


	interface MainPageUiBinder extends UiBinder<Widget, MainPage> {
	}

	public MainPage() {
		initWidget(uiBinder.createAndBindUi(this));
		
		ArrayList<String> names = new ArrayList<String>();
		names.add("tatort");
		names.add("halligalli");
		names.add("tvog");
		names.add("sterntv");
		names.add("berlintagundnacht");
		


//		for (String name : names) {
//			listSeries.addItem(name);
//		}
	}
	
	public void addSeries(String[] series) {
		for (String s : series) {
			listSeries.addItem(s);
		}
	}
	
	@UiField ListBox listSeries;
	
}
