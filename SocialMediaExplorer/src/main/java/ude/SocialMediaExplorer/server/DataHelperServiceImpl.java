package ude.SocialMediaExplorer.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import ude.SocialMediaExplorer.Config;
import ude.SocialMediaExplorer.client.rmi.IDataHelperService;
import ude.SocialMediaExplorer.data.result.ResultPoolingImpl;
import ude.SocialMediaExplorer.shared.exchangeFormat.ClusterElement;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings( "serial" )
public class DataHelperServiceImpl extends RemoteServiceServlet implements IDataHelperService {

	// TODO Benny Clusterelemente aus files deserialisieren

	public String[] getConfigHashtags() {
		ArrayList<String> hashtags = Config.get_crawler_hashtags();
		return hashtags.toArray( new String[hashtags.size()] );
	}

	public ClusterElement getClusters( String hashtag ) {
		ClusterElement readObject = null;
		FileInputStream fi = null;
		ObjectInputStream is = null;
		// Versuchen die Streams zu initialisieren
		try {
			fi = new FileInputStream("files/serializedClusterElements/tatort/tatort.ser");
			is = new ObjectInputStream(fi);
			readObject = (ClusterElement) is.readObject(); // Read Object
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// im finally block alle Streams schließen
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fi != null) {
				try {
					fi.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return readObject;
	}

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


}
