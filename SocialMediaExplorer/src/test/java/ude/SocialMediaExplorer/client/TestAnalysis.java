package ude.SocialMediaExplorer.client;

import ude.SocialMediaExplorer.analysis.Analysis;
import ude.SocialMediaExplorer.data.post.Post;
import ude.SocialMediaExplorer.data.post.PostList;

public class TestAnalysis {
	
	public static void main(String[] args) {
		
		Post p1 = new Post();
		p1.setMessage("Michael spaziert zum Rhein.");
		Post p2 = new Post();
		p2.setMessage("Ich bin gehen!");
		Post p3 = new Post();
		p3.setMessage("Ameisen sind keine Attribute?");
		
		PostList pl = new PostList();
		pl.add(p1);
		pl.add(p2);
		pl.add(p3);
		
		new Analysis(pl).run();
		
	}
	
}
