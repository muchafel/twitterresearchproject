package ude.SocialMediaExplorer.client.conversion;

import java.util.ArrayList;
import java.util.List;

import ude.SocialMediaExplorer.shared.exchangeFormat.ClusterElement;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNull;
import com.google.gwt.json.client.JSONNumber;
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

	private static ArrayList <ArrayList <String>>	rows;

	/////////////////////////////

	/**
	 * Conversion to a JSON table, as needed in the clients visualization
	 * 
	 * @param ce
	 *            {@link ClusterElement}
	 * @return a JSON encoded {@link String}
	 */
	public static String toJSON_GoogleTable( ClusterElement ce ) {

		rows = new ArrayList <ArrayList <String>>();
		ArrayList <String> header = new ArrayList <String>();
		header.add( "Name" );
		header.add( "Parent" );
		header.add( "Sentiment" );
		header.add( "Size" );
		rows.add( 0, header );

		// go through all els add parents name and make list with all nodes
		addCE( ce );

		JSONArray wrapper = new JSONArray();

		for ( int i = 0; i < rows.size(); i++ ) {

			JSONArray rowJSON = new JSONArray();
			ArrayList <String> row = rows.get( i );

			for ( int j = 0; j < row.size(); j++ ) {

				String date = row.get( j );

				System.out.println( date );
				try {
					if ( j < 2 ) {
						rowJSON.set( j, new JSONString( date ) );
					}
					else {
						try {
							rowJSON.set( j, new JSONNumber( Double.parseDouble( date ) ) );
						}
						catch ( Exception e ) {
							rowJSON.set( j, new JSONString( date ) );
						}
					}
				}
				catch ( Exception e ) {
					rowJSON.set( j, JSONNull.getInstance() );
				}

			}

			wrapper.set( i, rowJSON );

		}

		return wrapper.toString();
	}

	/////////////////////////////

	/**
	 * reorganize data structure
	 * 
	 * @param parent
	 *            = ROOTNODE
	 */
	private static void addCE( ClusterElement c ) {

		System.out.println( c.getName() );

		ArrayList <String> row = new ArrayList <String>();
		row.add( c.getName() );
		if ( c.getParent() != null ) {
			row.add( c.getParent() );
		}
		else {
			row.add( null );
		}
		row.add( String.valueOf( c.getSentiment().getNormalized() ) );
		row.add( String.valueOf( c.getSize() ) );
		rows.add( row );

		// recursion for all els
		List <ClusterElement> childs = c.getSubcluster();
		if ( childs != null ) {
			for ( ClusterElement child : childs ) {
				child.setParent( c.getName() );
				addCE( child );
			}
		}

	}

}
