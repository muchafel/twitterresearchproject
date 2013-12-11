package ude.SocialMediaExplorer.client.gui;

import java.util.ArrayList;

import ude.SocialMediaExplorer.shared.exchangeFormat.ClusterElement;

import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.ListBox;
import com.github.gwtbootstrap.client.ui.Paragraph;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ClusterVisualization extends Composite{

	private ClusterElement ce;
	private ClusterElement activeCE;
	private String hashtag;
	
	private static ClusterVisualizationUiBinder uiBinder = GWT
			.create(ClusterVisualizationUiBinder.class);

	interface ClusterVisualizationUiBinder extends
			UiBinder<Widget, ClusterVisualization> {
	}

	public ClusterVisualization(String hashtag, ClusterElement ce) {
		initWidget(uiBinder.createAndBindUi(this));

		this.hashtag = hashtag;
		this.ce = ce;
		
		title.setText(hashtag);
		
		ArrayList<String> names = new ArrayList<String>();
		for (ClusterElement c : ce.getSubcluster()){
			names.add(c.getName());
		}
		for (String name : names) {
		      listBox.addItem(name);
		}
		
		int pos = (int) ce.getSentiment().getPositive();
		int neg = (int) ce.getSentiment().getNegative();
		
		ce_sentiment.setText("Positiv: " + pos + " / " + "Negativ: " + neg );
		
		
		ArrayList<String> names2 = new ArrayList<String>();
		for (ClusterElement c : ce.getSubcluster()){
			names2.add(c.getName());
		}
		for (String name : names2) {
			ce_subcluster.addItem(name);
		}
	}

	@UiField ListBox listBox;
	@UiField Heading title;
	
	@UiField Heading ce_title;
	@UiField Paragraph ce_sentiment;
	@UiField ListBox ce_subcluster;


}
