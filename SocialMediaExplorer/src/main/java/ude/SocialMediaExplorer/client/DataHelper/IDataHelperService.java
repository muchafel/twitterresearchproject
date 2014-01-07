package ude.SocialMediaExplorer.client.DataHelper;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("datahelper")
public interface IDataHelperService extends RemoteService{
	String[] getConfigHashtags();
}
