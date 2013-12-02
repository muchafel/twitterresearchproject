package ude.SocialMediaExplorer.client;

import ude.SocialMediaExplorer.data.post.PostList;
import ude.SocialMediaExplorer.data.post.providing.live.TwitterRESTReader;

public class TestRESTReader {

	public static void main(String[] args) {
		
		TwitterRESTReader d = new TwitterRESTReader();
		PostList states = null;
		try {
			states = d.getPosts("tatort");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (ude.SocialMediaExplorer.data.post.Post s : states){
			System.out.println(s.getUser() + " -> " + s.getMessage());
		}
		
	}
	
}
