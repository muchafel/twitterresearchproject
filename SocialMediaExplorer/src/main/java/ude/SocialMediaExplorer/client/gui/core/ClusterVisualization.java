package ude.SocialMediaExplorer.client.gui.core;

import ude.SocialMediaExplorer.client.conversion.DataConverter;
import ude.SocialMediaExplorer.shared.exchangeFormat.ClusterElement;

import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.Paragraph;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * @author henrikdetjen
 * 
 */
public class ClusterVisualization extends Composite {

	private ClusterElement ce;
	private ClusterElement activeCE;
	private String hashtag;
	private String json;

	private static ClusterVisualizationUiBinder uiBinder = GWT.create( ClusterVisualizationUiBinder.class );

	interface ClusterVisualizationUiBinder extends UiBinder<Widget, ClusterVisualization> {}

	public ClusterVisualization( String hashtag, ClusterElement ce ) {
		initWidget( uiBinder.createAndBindUi( this ) );

		this.hashtag = hashtag;
		this.ce = ce;

		title.setText( hashtag );
		infos.setText( "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua." );

		json = DataConverter.toJSON_GoogleTable( ce );

	}

	@UiField
	Heading title;
	@UiField
	Paragraph infos;

	/**
	 * Uses the google apijs lib to draw the results in the clients browser
	 */
	public native void draw() /*-{

		if (!$wnd.google && !wnd.google.visualisation) {
			$wnd.alert("Something went wrong: no google js lib found... ");
			return;
		}

		try {

			//try to get and transform the data:
			var j = this.@ude.SocialMediaExplorer.client.gui.core.ClusterVisualization::json;
			var data = $wnd.google.visualization.arrayToDataTable($wnd.JSON
					.parse(j));

			var el = $doc.getElementById('googleChart');
			el.style.height = (($wnd.innerHeight / 3) * 2) + "px";

			// Create and draw the visualization.
			var tree = new $wnd.google.visualization.TreeMap($doc
					.getElementById('googleChart'));
			tree.draw(data, {
				minColor : '#2f00',
				midColor : '#ddd',
				maxColor : '#0d0',
				headerHeight : 50,
				textStyle: {  color: 'black',
							  fontSize: '16',
							  bold: false,
							  italic: false 
				},
				titleTextStyle: {  color: 'white',
								  fontSize: '20',
								  bold: false,
								  italic: false 
				},
				showScale : true,
				hintOpacity: 1,
				generateTooltip : showFullTooltip
			});

			function showFullTooltip(row, size, value) {
				return '<div style="background:white; padding:10px; border-style:2px solid black">'
						+ '<span style="font-family:Courier"><b>'
						+ data.getValue(row, 0)
						+ '</b>, '
						+ data.getValue(row, 1)
						+ ', '
						+ data.getValue(row, 2)
						+ ', '
						+ data.getValue(row, 3)
						+ '</span>'
						+ ' </div>';
			}

		} catch (e) {
			$wnd.alert("Something went wrong:\n " + e);
		}

	}-*/;

}
