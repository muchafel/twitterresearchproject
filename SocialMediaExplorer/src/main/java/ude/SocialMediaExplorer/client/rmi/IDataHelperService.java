package ude.SocialMediaExplorer.client.rmi;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("datahelper")
public interface IDataHelperService extends RemoteService{
	ArrayList<String> getConfigHashtags();
}
