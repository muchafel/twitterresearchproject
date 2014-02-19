package ude.SocialMediaExplorer.server;

import java.io.File;
import java.util.ArrayList;

import ude.SocialMediaExplorer.Config;
import ude.SocialMediaExplorer.client.rmi.IDataHelperService;
import ude.SocialMediaExplorer.data.utils.io.ObjectReader;
import ude.SocialMediaExplorer.server.conversion.DataConverter;
import ude.SocialMediaExplorer.shared.exchangeFormat.ClusterElement;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * the Implementation of the client-server interface
 * 
 * @author henrikdetjen
 * 
 */
@SuppressWarnings( "serial" )
public class DataHelperServiceImpl extends RemoteServiceServlet implements IDataHelperService {


	/**
	 * enduser
	 */

	//STEP 1: select a hashtag...
	public String[] getPossibleHashtags() {
		File dirs = new File( Config.get_location_results() );
		return dirs.list();
	}

	//STEP 2: get the timeStamps for all result files belonging to the hashtag...
	public String[] getPossibleFiles( String hashtag ) {
		File dir = new File( Config.get_location_results() + hashtag );
		return dir.list();
	}

	// STEP 3: take the chosen hashtag and the chosen file and load clusterelements for visualization...
	public String getData( String hashtag, String timeStamp ) {

		File dir = new File( Config.get_location_results() + hashtag );
		String[] filenames = dir.list();
		for ( String f : filenames ) {
			if ( f.contains( timeStamp ) ) {
				String path = Config.get_location_results() + hashtag + "/" + f;
				path = path.replace( " ", "" );
				System.out.println( path );
				ClusterElement ce = (ClusterElement) ObjectReader.readObject( path );
				return DataConverter.toJSONFormat( ce );
			}
		}
		return null;

	}

	/**
	 * admin
	 */

	// Functions to display and edit the project_config.xml

	public boolean addHashtag( String hashtag ) {
		try {
			Config.add_crawler_hashtag( hashtag );
		}
		catch ( Exception e ) {
			return false;
		}
		return true;
	}

	public boolean removeHashtags( String[] hashtags ) {
		try {
			Config.remove_crawler_hashtags( hashtags );
		}
		catch ( Exception e ) {
			return false;
		}
		return true;
	}

	public long get_Interval() {
		return  Config.get_crawler_newFileInterval();
	}

	public boolean set_Interval( long d ) {
		Config.set_crawler_newFileInterval( d );
		return true;
	}

	public String[] getConfigHashtags_actual() {
		ArrayList<String> hashtags = Config.get_crawler_hashtags_actual();
		return hashtags.toArray( new String[hashtags.size()] );
	}

	public String[] getConfigHashtags_next() {
		ArrayList<String> hashtags = Config.get_crawler_hashtags();
		return hashtags.toArray( new String[hashtags.size()] );
	}


}
