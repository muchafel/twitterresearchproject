package ude.SocialMediaExplorer.server;


import ude.SocialMediaExplorer.client.rmi.AnalyticService;
import ude.SocialMediaExplorer.data.model.PostList;
import ude.SocialMediaExplorer.data.providing.DataProviding;
import ude.SocialMediaExplorer.data.providing.stored.TwitterJSONFileReader;
import ude.SocialMediaExplorer.shared.Response;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * An Implementation of {@link AnalyticServiceAsync} 
 * @author henrikdetjen
 *
 */
@SuppressWarnings("serial")
public class AnalyticServiceImpl extends RemoteServiceServlet implements AnalyticService{

	public Response analyseHashtag(String hashtag)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		try {
			Response resp = new Response();
			DataProviding  d = new TwitterJSONFileReader();
			PostList p = d.getPosts(hashtag);
			String s = p.makeString();
			
			System.out.println(hashtag);
			System.out.println(p.size());
			System.out.println(s);
			
			resp.addString(s);
			return resp;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Response();
		}
	}

}
