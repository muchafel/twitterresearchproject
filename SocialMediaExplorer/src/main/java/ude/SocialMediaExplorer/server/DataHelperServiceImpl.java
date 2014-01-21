package ude.SocialMediaExplorer.server;

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
		ClusterElement results = null;
		try {
			results = new ResultPoolingImpl().getClusters( hashtag );
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
		return results;
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
