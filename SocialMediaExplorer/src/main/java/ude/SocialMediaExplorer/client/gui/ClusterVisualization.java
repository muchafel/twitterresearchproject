package ude.SocialMediaExplorer.client.gui;

import java.util.ArrayList;

import ude.SocialMediaExplorer.shared.exchangeFormat.ClusterElement;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class ClusterVisualization extends Composite{

	private static ClusterVisualizationUiBinder uiBinder = GWT
			.create(ClusterVisualizationUiBinder.class);

	interface ClusterVisualizationUiBinder extends
			UiBinder<Widget, ClusterVisualization> {
	}

	public ClusterVisualization(ClusterElement ce) {
		initWidget(uiBinder.createAndBindUi(this));
		
		ArrayList<String> names = new ArrayList<String>();

		for (ClusterElement c : ce.getSubcluster()){
			names.add(c.getName());
		}
		for (String name : names) {
		      listBox.addItem(name);
		}
	}

	@UiField ListBox listBox;




}
