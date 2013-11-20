package ude.SocialMediaExplorer.analysis;

import ude.SocialMediaExplorer.data.model.Post;
import ude.SocialMediaExplorer.data.model.PostList;
import ude.SocialMediaExplorer.data.providing.DataProviding;
import ude.SocialMediaExplorer.data.providing.stored.TwitterJSONFileReader;
import ude.SocialMediaExplorer.data.utils.time.TimeSpan;


public class Analysis extends Thread{

	String hashtag;
	DataProviding dataProvider;
	TimeSpan timeSpan;

	public Analysis(String hashtag, TimeSpan timeSpan) {
		dataProvider = new TwitterJSONFileReader();
		this.timeSpan= timeSpan;
	}
	
	public void run() {
		
		try {
			PostList posts = dataProvider.getPosts(hashtag,timeSpan);
			
			for (Post p : posts){
//				analyze(p);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}