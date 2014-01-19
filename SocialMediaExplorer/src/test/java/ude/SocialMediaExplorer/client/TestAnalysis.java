package ude.SocialMediaExplorer.client;

import java.io.File;
import java.util.List;

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
	
	public static void main(String[] args) {
		
		String hashtagToAnalyze="tatort";
		
		TwitterJSONFileReader bla =  new TwitterJSONFileReader();
		PostList pl=null;
		try {
			pl = bla.getPosts(hashtagToAnalyze);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//pl.print();
		
		
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

	
	
}
