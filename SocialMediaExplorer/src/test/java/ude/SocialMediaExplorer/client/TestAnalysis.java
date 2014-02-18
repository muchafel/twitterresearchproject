package ude.SocialMediaExplorer.client;

import java.io.File;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.json.DataObjectFactory;
import ude.SocialMediaExplorer.analysis.Analysis;
import ude.SocialMediaExplorer.analysis.Clusterer;
import ude.SocialMediaExplorer.data.post.Post;
import ude.SocialMediaExplorer.data.post.PostList;
import ude.SocialMediaExplorer.data.post.providing.PostConverter;
import ude.SocialMediaExplorer.data.post.providing.stored.TwitterJSONFileReader;
import ude.SocialMediaExplorer.data.utils.io.TextFileReader;


public class TestAnalysis {
	
	private boolean running = true;
	static String hashtagToAnalyze = "tatort";
	static private PostList pl;
	static Timer timer;
	
	public static void main(String[] args) {
		
		TwitterJSONFileReader bla =  new TwitterJSONFileReader();
		PostList pl=null;
		try {
			pl = bla.getPosts(hashtagToAnalyze);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
//		Post p1 = new Post();
//		p1.setMessage("RT @Michael walks to the nice river Rhein :) <3 <3 <3.");
//		p1.setId("1");
//		Post p2 = new Post();
//		p2.setMessage(" I am! legend Fleisch wtf");
//		p2.setId("2");
//		Post p3 = new Post();
//		p3.setMessage("RT Ameisen sind keine schlechten Attribute #ftw ");
//		p3.setId("3");
//		Post p4 = new Post();
//		p4.setMessage("RT @Kommisaro #ftw https://www.facebook.com/mi.woja");
//		p4.setId("4");
//		PostList pl = new PostList();
//		pl.add(p1);
//		pl.add(p2);
//		pl.add(p3);
//		pl.add(p4);
//		
		new Analysis(pl).run(hashtagToAnalyze);
		new Clusterer().cluster(hashtagToAnalyze);
		
	}

	private void startAnalysisCycle(int everyXHour) {
		timer = new Timer();
		TimerTask hourlyTask = new TimerTask() {
			@Override
			public void run() {
				new Analysis(pl).run(hashtagToAnalyze);
				new Clusterer().cluster(hashtagToAnalyze);
			}
		};
		
		timer.schedule (hourlyTask, 0l, 1000*60*60*everyXHour);
	}
	
}
