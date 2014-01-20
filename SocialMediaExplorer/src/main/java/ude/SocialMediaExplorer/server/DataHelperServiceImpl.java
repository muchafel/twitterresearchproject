package ude.SocialMediaExplorer.server;

import java.util.ArrayList;

import ude.SocialMediaExplorer.Config;
import ude.SocialMediaExplorer.client.rmi.IDataHelperService;
import ude.SocialMediaExplorer.data.result.IResultPooling;
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

	public ClusterElement getClusters(String hashtag) {
		ClusterElement results = null;
		try {
			results = new ResultPoolingImpl().getClusters( hashtag );
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
		return results;
	}

}
