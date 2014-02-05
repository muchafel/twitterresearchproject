package ude.SocialMediaExplorer.client.conversion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ude.SocialMediaExplorer.client.gui.errorhandling.SimpleErrorHandling;
import ude.SocialMediaExplorer.shared.exchangeFormat.ClusterElement;
import ude.SocialMediaExplorer.shared.exchangeFormat.Sentiment;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNull;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

/**
 * 
 * @author henrikdetjen
 * 
 *         converts the ClusterElements into something else
 * 
 */
public class DataConverter {

	/////////////////////////////

	private static ArrayList<JSONArray> ceList;

	@Deprecated
	private static ArrayList<ArrayList<String>> dataRowsTable;
	@Deprecated
	private static Map<String, ArrayList<String>> dataPosts;

	/////////////////////////////

	/**
	 * 
	 * @param c {@link ClusterElement} a Cluster to transform
	 * @return a JSON Array of Rows with single Clusters
	 * 			firstrow = header
	 * 				cols:	
	 * 					0 = id of the cluster
	 * 					1 = name of the cluster
	 * 					2 = parent cluster (id)
	 * 					3 = size 
	 * 					4 = sentiments value
	 * 					5 = sentiments range
	 * 					6 = posts belonging to the cluster
	 * 					
	 */
	public static String toJSONFormat( ClusterElement c ) {
		
		//make JSON
		ceList = new ArrayList<JSONArray>();
		addToCeList( c );
		
		JSONArray wrapper = new JSONArray();

		JSONArray header = new JSONArray();
		header.set( 0, new JSONString( "Id" ) );
		header.set( 1, new JSONString( "Name" ) );
		header.set( 2, new JSONString( "Parent" ) );
		header.set( 3, new JSONString( "Size" ) );
		header.set( 4, new JSONString( "SentimentValue" ) );
		header.set( 5, new JSONString( "SentimentRange" ) );
		header.set( 6, new JSONString( "Posts" ) );
		wrapper.set( 0, header );
		
		int rowCount = ceList.size();
		for ( int i = 0; i < rowCount; i++ ) {
			wrapper.set( ( i+1 ), ceList.get( i ) );
		}

		ceList = null;
		return wrapper.toString();
	}

	/**
	 * 
	 * @param c
	 */
	private static void addToCeList( ClusterElement c ) {

		JSONArray row = new JSONArray();

		String id = String.valueOf( c.getId() );

		//0 = Id		/String
		row.set( 0, new JSONString( id ) );
		//1 = Name		/String
		row.set( 1, new JSONString( c.getName() ) );
		//2 = Parent	/String
		String parent = c.getParent();
		if (parent == null) {
			row.set( 2, JSONNull.getInstance() );
		}else {
			row.set( 2, new JSONString( parent ) );			
		}
		//3 = Size		/Double
		row.set( 3, new JSONNumber( Math.random() ) );
		//4 = SentimentValue	/Double
		Sentiment s = c.getSentiment();
		row.set( 4, new JSONNumber( Math.random() ) );
		//5 = SentimentRange 	/Double
		row.set( 5, new JSONNumber( Math.random() ) );
		//6 = Posts		/ArrayList<String>
		ArrayList<String> posts = c.getPosts();
		JSONArray postsJSON = new JSONArray();
		int postSize = posts.size();
		for ( int i = 0; i < postSize; i++ ) {
			postsJSON.set( i, new JSONString( posts.get( i ) ) );
		}
		row.set( 6, postsJSON );
		
		System.out.println(row);
		ceList.add( row );

		// recursion for all els
		List<ClusterElement> childs = c.getSubcluster();
		if ( childs != null ) {
			for ( ClusterElement child : childs ) {
				child.setParent( id );
				addToCeList( child );
			}
		}

	}

	/**
	 * 
	 * Done directly in JSNI now (this is too slow..)
	 * 
	 * Conversion to a JSON table, as needed in the clients visualization
	 * 
	 * @param ce
	 *            {@link ClusterElement}
	 * @return a JSON encoded {@link String}
	 */
	@Deprecated
	public static String toJSON_GoogleTable( ClusterElement ce ) {

		init();
		System.out.println( "DataConverter -> Creating Google data table and converting it to JSON..." );

		// go through all els add parents name and make list with all nodes		
		addCE( ce );

		JSONArray wrapper = new JSONArray();

		// add header
		JSONArray header = new JSONArray();
		header.set( 0, new JSONString( "Name" ) );
		header.set( 1, new JSONString( "Parent" ) );
		header.set( 2, new JSONString( "Sentiment" ) );
		header.set( 3, new JSONString( "Size" ) );
		wrapper.set( 0, header );

		// for all rows
		for ( int i = 0; i < dataRowsTable.size(); i++ ) {

			//make new json set
			JSONArray rowJSON = new JSONArray();
			ArrayList<String> row = dataRowsTable.get( i );

			//			System.out.println( row.toString() );

			// handle all column values
			try {
				// col 1 = value
				// v = value / f = formatted value
				JSONObject idAndValue = new JSONObject();
				idAndValue.put( "v", new JSONString( row.get( 0 ) ) );//html-"id"
				idAndValue.put( "f", new JSONString( row.get( 1 ) ) );//html-"value"
				rowJSON.set( 0, idAndValue );
				//col 2 = parent
				if ( row.get( 2 ) != null ) {
					rowJSON.set( 1, new JSONString( row.get( 2 ) ) );
				}
				else {
					rowJSON.set( 1, JSONNull.getInstance() );
				}
				//col 3 = sentiment
				rowJSON.set( 2, new JSONNumber( Double.parseDouble( row.get( 3 ) ) ) );
				//col 4 = size
				rowJSON.set( 3, new JSONNumber( Double.parseDouble( row.get( 4 ) ) ) );
			}
			catch ( Exception e ) {
				new SimpleErrorHandling( e );
			}

			wrapper.set( i + 1, rowJSON );

		}

		dataRowsTable = null;
		System.out.println( "DataConverter -> ...done" );

		return wrapper.toString();
	}


	/**
	 * done in JSNI (vis)
	 * 
	 * @param ce
	 * @return
	 */
	@Deprecated
	public static String toJSON_Posts( ClusterElement ce ) {

		init();

		System.out.println( "DataConverter -> Converting Posts to JSON..." );
		JSONArray wrapper = new JSONArray();
		String[] keys = dataPosts.keySet().toArray( new String[dataPosts.keySet().size()] );

		for ( int i = 0; i < keys.length; i++ ) {
			String key = keys[i];
			ArrayList<String> msgs = dataPosts.get( key );
			JSONArray row = new JSONArray();
			row.set( 0, new JSONString( key ) );
			JSONArray posts = new JSONArray();
			for ( int j = 0; j < msgs.size(); j++ ) {
				posts.set( j, new JSONString( msgs.get( j ) ) );
			}
			row.set( 1, posts );
			wrapper.set( i, row );
		}

		dataPosts = null;
		System.out.println( "DataConverter -> ...done" );

		return wrapper.toString();
	}


	/////////////////////////////

	/**
	 * reorganize data structure and make two big lists with needed values
	 * 
	 * @param parent
	 *            {@link ClusterElement} = ROOTNODE
	 */
	@Deprecated
	private static void addCE( ClusterElement c ) {
		if ( c != null ) {

			//TREEMAP
			ArrayList<String> row = new ArrayList<String>();
			row.add( String.valueOf( c.getId() ) );
			row.add( c.getName() );
			row.add( c.getParent() );
			//row.add( String.valueOf( c.getSentiment().getNormalized() ) );
			//row.add( String.valueOf( c.getSize() ) );
			Double d = Math.random();
			row.add( d.toString() );
			Double d2 = Math.random();
			row.add( d2.toString() );
			dataRowsTable.add( row );

			//POSTS
			dataPosts.put( String.valueOf( c.getId() ), c.getPosts() );

			// recursion for all els
			List<ClusterElement> childs = c.getSubcluster();
			if ( childs != null ) {
				for ( ClusterElement child : childs ) {
					//				System.out.println("child:" + child.getName() + " / parent:" + child.getParent());
					child.setParent( String.valueOf( c.getId() ) );
					addCE( child );
				}
			}
		}

	}


	private static void init() {
		if ( dataPosts == null )
			dataPosts = new HashMap<String, ArrayList<String>>();
		if ( dataRowsTable == null )
			dataRowsTable = new ArrayList<ArrayList<String>>();
	}

}
