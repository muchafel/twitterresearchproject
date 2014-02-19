package ude.SocialMediaExplorer.server.conversion;

import java.util.ArrayList;
import java.util.List;

import org.json.*;

import ude.SocialMediaExplorer.shared.exchangeFormat.ClusterElement;
import ude.SocialMediaExplorer.shared.exchangeFormat.Sentiment;

//import com.google.gwt.json.client.JSONArray;
//import com.google.gwt.json.client.JSONNull;
//import com.google.gwt.json.client.JSONNumber;
//import com.google.gwt.json.client.JSONString;


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

	/////////////////////////////

	/**
	 * 
	 * @param c
	 *            {@link ClusterElement} a Cluster to transform
	 * @return a JSON Array of Rows with single Clusters
	 *         firstrow = header
	 *         cols:
	 *         0 = id of the cluster
	 *         1 = name of the cluster
	 *         2 = parent cluster (id)
	 *         3 = size
	 *         4 = sentiments value
	 *         5 = sentiments range
	 *         6 = posts belonging to the cluster
	 * 
	 */
	public static String toJSONFormat( ClusterElement c ) {

		try {
			//make JSON
			ceList = new ArrayList<JSONArray>();
			addToCeList( c );

			JSONArray wrapper = new JSONArray();

			JSONArray header = new JSONArray();
			header.put( 0, "Id" );
			header.put( 1, "Name" );
			header.put( 2, "Parent" );
			header.put( 3, "Size" );
			header.put( 4, "SentimentValue" );
			header.put( 5, "SentimentRange" );
			header.put( 6, "Posts" );
			wrapper.put( 0, header );

			int rowCount = ceList.size();
			for ( int i = 0; i < rowCount; i++ ) {
				wrapper.put( ( i + 1 ), ceList.get( i ) );
			}

			ceList = null;
			return wrapper.toString();
		}
		catch ( Exception e ) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 
	 * @param c
	 */
	private static void addToCeList( ClusterElement c ) throws Exception {

		JSONArray row = new JSONArray();

		String id = String.valueOf( c.getId() );

		//0 = Id		/String
		row.put( 0, id );
		//1 = Name		/String
		row.put( 1, c.getName() );
		//2 = Parent	/String
		String parent = c.getParent();
		if ( parent == null ) {
			row.put( 2, JSONObject.NULL );
		}
		else {
			row.put( 2, parent );
		}
		//3 = Size		/Double
		row.put( 3, c.getPosts().size() );
		//4 = SentimentValue	/Double
		Sentiment s = c.getSentiment();
		Double sentiment = s.getNormalized();
			//map to 0-1
		row.put( 4, ( ( Math.round( sentiment * 100D ) / 100D ) ) );
		//5 = SentimentRange 	/Double
		row.put( 5, ( ( Math.round( s.getRange() * 100D ) / 100D ) ) );
		//6 = Posts		/ArrayList<String>
		ArrayList<String> posts = c.getPosts();
		JSONArray postsJSON = new JSONArray();
		int postSize = posts.size();
		for ( int i = 0; i < postSize; i++ ) {
			postsJSON.put( i, posts.get( i ) );
		}
		row.put( 6, postsJSON );

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


}
