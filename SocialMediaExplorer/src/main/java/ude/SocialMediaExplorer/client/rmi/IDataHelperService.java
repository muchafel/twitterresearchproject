package ude.SocialMediaExplorer.client.rmi;

import ude.SocialMediaExplorer.shared.exchangeFormat.ClusterElement;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath( "datahelper" )
public interface IDataHelperService extends RemoteService {

	String[] getConfigHashtags();
	
	ClusterElement getClusters(String hashtag);
	
}
