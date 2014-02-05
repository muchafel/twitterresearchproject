package ude.SocialMediaExplorer.client.gui.core;

import ude.SocialMediaExplorer.client.conversion.DataConverter;
import ude.SocialMediaExplorer.shared.exchangeFormat.ClusterElement;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
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
	private String jsonData;

	private static ClusterVisualizationUiBinder uiBinder = GWT.create( ClusterVisualizationUiBinder.class );

	interface ClusterVisualizationUiBinder extends UiBinder<Widget, ClusterVisualization> {}

	public ClusterVisualization( String hashtag, ClusterElement ce ) {
		initWidget( uiBinder.createAndBindUi( this ) );

		this.hashtag = hashtag;
		this.ce = ce;
		
		//		title.setText( hashtag );
		//		infos.setText( "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua." );

		jsonData = DataConverter.toJSONFormat( ce );
	}

	/**
	 * Uses the google apijs lib to draw the results in the clients browser
	 */
	/**
	 * 
	 */
	public native void draw() /*-{

		if (!$wnd.google && !wnd.google.visualisation) {
			$wnd.alert("Something went wrong: no google js lib found... ");
			return;
		}

		try {


			/////////////////////////////////////////////////////

			//try to get and transform the data:
			var j = this.@ude.SocialMediaExplorer.client.gui.core.ClusterVisualization::jsonData;
			console.log("received data:");
			var data = $wnd.JSON.parse(j);
			console.log(data);
			var dataSize = data.length;
			//	first row  = header
			//	cols:	
			//		0 = id of the cluster
			//		1 = name of the cluster
			//		2 = parent cluster (id)
			//		3 = size 
			//		4 = sentiments value
			//		5 = sentiments range
			//		6 = posts belonging to the cluster
			function getRowById(id){
				for (var i = 0; i < dataSize; i++){
					if (id == data[i][0]){
						return data[i];
					}
				}
			}

			//create different data structures for google js api
			console.log("building treemap data...");
			var treemapData = [];
			for (var i = 0; i < dataSize; i++){
				var row = new Array();
				row.push({v:data[i][0],f:data[i][1]});//value/formatted
				row.push(data[i][2]);//parent
				row.push(data[i][3]);//size
				row.push(data[i][4]);//sentiment
				treemapData.push(row);
			}
			treemapData = new $wnd.google.visualization.arrayToDataTable( treemapData );
			console.log("...done");
			
//			console.log("building orgChart data...");
//			var orgChartData = [];
//			for (var i = 0; i < dataSize; i++){
//				var row = new Array();
//				var row = new Array();
//				row.push({v:data[i][0],f:data[i][1]});//value/formatted
//				row.push(data[i][2]);//parent
//				orgChartData.push(row);
//			}
//			orgChartData = new $wnd.google.visualization.arrayToDataTable( orgChartData );
//			console.log("...done");
			
			// Create and draw the postList
			function drawPosts(id){
				
				var row = getRowById(id);
				console.log(row);
				var posts = row[6];
				
				function makeHTMLfromPosts(postArray){
					var ul = $doc.createElement('ul');
					for (var i = 0; i < postArray.length; i++){
						var li = $doc.createElement('li');
						li.innerHTML = postArray[i];
						ul.appendChild(li);
					} 
					var postsDiv = $doc.getElementById("posts");
					while (postsDiv.firstChild) {
	  					postsDiv.removeChild(postsDiv.firstChild);
					}
					postsDiv.appendChild(ul);
				}
				
				makeHTMLfromPosts(posts);
				
				
//				posts.unshift("Posts");
//				
//				console.log("building postList data...");
//				
//				var postListData = new $wnd.google.visualization.arrayToDataTable( posts );
//				console.log("...done");
//				
//				console.log("drawing List...");
//				var table = new $wnd.google.visualization.Table($doc.getElementById('posts'));
//	        	table.draw(postListData, {showRowNumber: true});
//				console.log("...done");
				
			}
			drawPosts(data[1][0]);//build list for topCluster
			
			// Update Breadcrumb
			function addBreadcrumb(id){
				var row = getRowById(id);
				var id = row[0];
				var name = row[1];
				var parentId = row[2]; 
				var bc = $doc.getElementById("breadcrumbs");
				
				
				//remove last if on same level
				if( bc.lastChild && bc.lastChild.id != parentId ){
					removeLastBreadcrumb();
					add();
				}else{
					add();	
				}
				
				function add(){
					if( ( bc.lastChild &&  bc.lastChild.id != id ) || !bc.lastChild ){
						var newLi = $doc.createElement("li");
						newLi.innerHTML = "&nbsp;>&nbsp;" + name; 
						newLi.id = id;
						bc.appendChild(newLi);
					}
				}
			}
			addBreadcrumb(data[1][0]); //TopCluster 
			function removeLastBreadcrumb(){
				var parent = $doc.getElementById("breadcrumbs");
				parent.removeChild(parent.lastChild);
			}
			function removeBreadcrumb(id){
				var parent = $doc.getElementById("breadcrumbs");
				while(parent.lastChild.id!=id){
					parent.removeChild(parent.lastChild);
				}
			}
			

			// Create and draw the treemap
			console.log("drawing treemap...");
			var treemapEl = $doc.getElementById('treemap');
			treemapEl.style.height = (($wnd.innerHeight / 2) * 1) + "px";

			var tree = new $wnd.google.visualization.TreeMap( treemapEl );
			tree.draw( 
				treemapData, 
				{
					minColor : '#2f00',
					midColor : '#ddd',
					maxColor : '#0d0',
					headerHeight : 50,
					textStyle : {
						color : 'black',
						fontSize : '16',
						bold : false,
						italic : false
					},
					titleTextStyle : {
						color : 'white',
						fontSize : '20',
						bold : false,
						italic : false
					},
					showScale : true,
					hintOpacity : 1,
					generateTooltip : showFullTooltip
				}
			);
			console.log("...done");

			function showFullTooltip(row, size, value) {
				return '<div style="background:white; padding:10px; border-style:2px solid black">'
						+ '<b>'
						+ treemapData.getFormattedValue(row, 0)
						+ '</b>'
						+ ' </div>';
			}

			//react on click..
			//show corresponding posts
			var last = [];//navigation history
			function selectHandler() {
				var selection = tree.getSelection();
				last.push(selection);
				if(selection[0].row){
					var id = treemapData.getValue( selection[0].row, 0 );
					drawPosts( id );
					addBreadcrumb( id );
				}
			}
			function rollupHandler() {
				var selection = tree.getSelection();
				last.pop();
				if(selection[0].row){
					//getParent, because rollup returns "from" not "to"
					var idFrom = treemapData.getValue( selection[0].row, 0 );
					var row = getRowById( idFrom );
					var idTo = row[2];
					drawPosts( idTo );
					removeBreadcrumb( idFrom );
				}
			}
			
			$wnd.google.visualization.events.addListener(tree, 'select', selectHandler);
			$wnd.google.visualization.events.addListener(tree, 'rollup', rollupHandler);
			
			var upButton = $doc.getElementById("btn_up");
			upButton.onclick = function(){
				tree.goUpAndDraw();
			}
			
			var downButton = $doc.getElementById("btn_down");
				downButton.onclick = function(){
				tree.setSelection(last[(last.length-1)]);	
			}
		

		} catch (e) {
			$wnd.alert("Something went wrong:\n " + e);
		}

		

	}-*/;

}
