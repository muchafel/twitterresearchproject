package ude.SocialMediaExplorer.analysis;

import ude.SocialMediaExplorer.data.model.Post;
import ude.SocialMediaExplorer.data.model.PostList;
import ude.SocialMediaExplorer.data.providing.DataProviding;
import ude.SocialMediaExplorer.data.providing.stored.TwitterJSONFileReader;


public class Analysis extends Thread{

	String hashtag;
	DataProviding dataProvider;

	public Analysis(String hashtag) {
		dataProvider = new TwitterJSONFileReader();
		
	}
	
	public void run() {
		
		try {
			PostList posts = dataProvider.getPosts(hashtag);
			
			for (Post p : posts){
//				analyze(p);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}