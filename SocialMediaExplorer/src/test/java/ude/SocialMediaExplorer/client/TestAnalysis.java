package ude.SocialMediaExplorer.client;

import ude.SocialMediaExplorer.analysis.Analysis;
import ude.SocialMediaExplorer.data.model.Post;
import ude.SocialMediaExplorer.data.model.PostList;

public class TestAnalysis {
	
	public static void main(String[] args) {
		
		Post p1 = new Post();
		p1.setMessage("EINSTIEGSDROGE:Der 1. Tony Braun #Thriller entführt dich in den Kopf des Killers. @BCSchiller http://t.co/qZbH9r265W #tatort #bestseller");
		Post p2 = new Post();
		p2.setMessage("Sergio Pettis #tatort Gracias http://t.co/h4C4JoG7pw Sabella #forumbeasiswa");
		Post p3 = new Post();
		p3.setMessage("Dienstag, 22:00 Uhr auf NDR: \u201eRuhe sanft\u201c, http://t.co/fQ1ywZUaad #Tatort");
		
		PostList pl = new PostList();
		pl.add(p1);
		pl.add(p2);
		pl.add(p3);
		
		new Analysis(pl).run();
		
	}
	
}
