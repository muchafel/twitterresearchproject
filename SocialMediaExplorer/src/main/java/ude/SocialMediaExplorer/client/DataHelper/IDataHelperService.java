package ude.SocialMediaExplorer.client.DataHelper;

import com.google.gwt.user.client.rpc.RemoteService;

public interface IDataHelperService extends RemoteService{
	String[] getConfigHashtags();
}
