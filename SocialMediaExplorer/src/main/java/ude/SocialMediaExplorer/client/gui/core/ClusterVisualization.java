package ude.SocialMediaExplorer.client.gui.core;

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

	private String hashtag;
	private String jsonData;

	private static ClusterVisualizationUiBinder uiBinder = GWT.create( ClusterVisualizationUiBinder.class );

	interface ClusterVisualizationUiBinder extends UiBinder<Widget, ClusterVisualization> {}

	public ClusterVisualization( String hashtag, String data ) {
		initWidget( uiBinder.createAndBindUi( this ) );

		this.hashtag = hashtag;
		jsonData = data;
		
		//		title.setText( hashtag );
		//		infos.setText( "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua." );

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
				for (var i = 1; i < dataSize; i++){
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
			
			/////////////////////////////////////////////////////
			
			// Create and draw the postList
			function drawPosts(id){
				
				var row = getRowById(id);
				console.log(row);
				var posts = row[6];
				
				console.log("building postList data...");
				var postListData = new $wnd.google.visualization.DataTable();
				postListData.addColumn('string', 'Post');
				postListData.addColumn('number', 'Sentiment');
				postListData.addRows(posts.length);
				for (var i = 0; i < posts.length; i++){
					var post = posts[i];
					postListData.setCell(i, 0, post.post);
					postListData.setCell(i, 1, post.sentiment);
				}
				
				console.log("...done");
				
				console.log("drawing List...");
				var table = new $wnd.google.visualization.Table($doc.getElementById('posts'));
				
				var formatter = new $wnd.google.visualization.ColorFormat();
				formatter.addGradientRange(0,0.5, 'black', '#f00', '#ddd');
				formatter.addGradientRange(0.5,1, 'black', '#ddd', '#0d0');
  				formatter.format(postListData, 1); // Apply formatter to second column
				
	        	table.draw( postListData, 
	        	{
	        		allowHtml: true, 
	        		showRowNumber: true,
	        		height: (($wnd.innerHeight / 2) * 1) + "px"
//	        		page: 'enable',
//	        		pageSize: 10,
//	        		pagingSymbols: {
//				        prev: ' <- ',
//				        next: ' -> '
//				    },
//				    pagingButtonsConfiguration: 'auto'
	        	});
	        	
	        	$doc.getElementById('posts').style.overflowX = "hidden";
	        	$doc.getElementsByClassName('google-visualization-table-table')[0].style.overflowX = "hidden";
	        	
	        	
				console.log("...done");
				
			}
			drawPosts(data[1][0]);//build list for topCluster
	
			/////////////////////////////////////////////////////
	
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
			
			/////////////////////////////////////////////////////

			// Create and draw the treemap
			console.log("drawing treemap...");
			var treemapEl = $doc.getElementById('treemap');
			treemapEl.style.height = (($wnd.innerHeight / 3) * 2) + "px";

			var tree = new $wnd.google.visualization.TreeMap( treemapEl );
			tree.draw( 
				treemapData, 
				{
					minColor : '#f00',
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
			//var last = [];//TODO:navigation history
			function selectHandler() {
				var selection = tree.getSelection();
				if(selection[0].row != null){
					var id = treemapData.getValue( selection[0].row, 0 );
					drawPosts( id );
					addBreadcrumb( id );
				}
			}
			function rollupHandler() {
				var selection = tree.getSelection();
				console.log("rollup - "+selection[0].row);
				if(selection[0].row != null){
					//getParent, because rollup returns "from" not "to"
					var idTarget = treemapData.getValue( selection[0].row, 0 );
					drawPosts( idTarget );
					removeBreadcrumb( idTarget );					
				}
			}
			
			$wnd.google.visualization.events.addListener(tree, 'select', selectHandler);
			$wnd.google.visualization.events.addListener(tree, 'rollup', rollupHandler);
			
			var upButton = $doc.getElementById("btn_up");
			upButton.onclick = function(){
				tree.goUpAndDraw();
			}
		

		} catch (e) {
			$wnd.alert("Something went wrong:\n " + e);
		}

		

	}-*/;

}
