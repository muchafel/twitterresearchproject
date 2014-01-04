package ude.SocialMediaExplorer.client;

import java.io.File;
import java.util.List;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.json.DataObjectFactory;
import ude.SocialMediaExplorer.analysis.Analysis;
import ude.SocialMediaExplorer.data.post.Post;
import ude.SocialMediaExplorer.data.post.PostList;
import ude.SocialMediaExplorer.data.post.providing.PostConverter;
import ude.SocialMediaExplorer.data.post.providing.stored.TwitterJSONFileReader;
import ude.SocialMediaExplorer.data.utils.io.TextFileReader;


public class TestAnalysis {
	
	public static void main(String[] args) {
		
		
		TwitterJSONFileReader bla =  new TwitterJSONFileReader();
		PostList pl=null;
		try {
			pl = bla.getPosts("halligalli");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//pl.print();
		
		
//		Post p1 = new Post();
//		p1.setMessage("RT @Michael spaziert zum  schönen Rhein :) <3 <3 <3.");
//		p1.setId("1");
//		Post p2 = new Post();
//		p2.setMessage(" Ich bin! gutes Fleisch wtf");
//		p2.setId("2");
//		Post p3 = new Post();
//		p3.setMessage("Ameisen sind keine schlechten Attribute #ftw ");
//		p3.setId("3");
//		Post p4 = new Post();
//		p3.setMessage("Kommisaro #ftw ");
//		p3.setId("4");
//		Post p5 = new Post();
//		p3.setMessage("Ameisen sind keine schlechten Attribute #ftw ");
//		p3.setId("5");
//		
//		Post p6 = new Post();
//		p3.setMessage("Armeisen sind keine schlechten Attributes #ftw ");
//		p3.setId("6");
//		Post p7 = new Post();
//		p3.setMessage("Ameiseni sind keine schlechten Attributes #ftw ");
//		p3.setId("7");
//		
//		
//		PostList pl = new PostList();
//		pl.add(p1);
//		pl.add(p2);
//		pl.add(p3);
		
		new Analysis(pl).run();
		
	}

	
	
}
