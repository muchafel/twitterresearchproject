package ude.SocialMediaExplorer.client;

import java.io.File;
import java.io.FilenameFilter;
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
	static String hashtagToAnalyze = "fashionhero";
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
//		

		
		
//		Post p1 = new Post();
//		p1.setMessage("In einer Anti-Spam Klage wird behauptet, dass Utah Anti-Spam Regelungen verletzt. Das Vorgehen von Utah bezüglich der Anti-Spam Gesetze könnte die Bahn für neue Regelungen mit Telekommunikationsanbietern ebnen.");
//		p1.setId("1");
//		Post p2 = new Post();
//		p2.setMessage("In einer Anti-Späm Klage wird behaupptet, dass Utah Anti-Spam Regelungen verletzt. Das Vohrgehen von Utah bezüglich der Anti-Spam Gesetze könnte die Bahn für neue Rehgelungen mit Telekomunikationsanbietern ebnen.");
//		p2.setId("2");
//		Post p3 = new Post();
//		p3.setMessage("In einer AntiSpam Klage wird behäuptet, dass Utah Anti-Spam regelungen verletzt. Das Vorgehn von Utah bezüglich der Anti-Spam Gesetze könte die Bahn für neue Regellungen mit Telekommunikatinsanbietern ebnen.");
//		p3.setId("3");
//		Post p4 = new Post();
//		p4.setMessage("In einer Anti-Spam Klage wird behauptet, dass Utah Anti-Spam Regelungen verletzt. Das Vorgehen von Utah bezüglich der Anti-Spam Gesetze könnte die Bahn für neue Regelungen mit Telekommunikationsanbietern ebnen.");
//		p4.setId("4");
//		PostList pl = new PostList();
//		pl.add(p1);
//		pl.add(p2);
//		pl.add(p3);
//		pl.add(p4);
		
		new Analysis(pl).run(hashtagToAnalyze);
		File root = new File("files/serializedCases/" + hashtagToAnalyze);
		String[] directories = root.list(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return new File(dir, name).isDirectory();
			}
		});
		
		for (String s : directories) {
			System.out.println("Ordner: "+s);
			new Clusterer().cluster(hashtagToAnalyze + "/" + s);
		}
		
	}

	private void startAnalysisCycle(int everyXHour) {
		timer = new Timer();
		TimerTask hourlyTask = new TimerTask() {
			@Override
			public void run() {
				new Analysis(pl).run(hashtagToAnalyze);

				File root = new File("files/serializedCases/" + hashtagToAnalyze);
				String[] directories = root.list(new FilenameFilter() {
					public boolean accept(File dir, String name) {
						return new File(dir, name).isDirectory();
					}
				});
				
				for (String s : directories) {
					System.out.println("Ordner: "+s);
					new Clusterer().cluster(hashtagToAnalyze + "/" + s);
				}
				
				
				
				
			}
		};
		
		timer.schedule (hourlyTask, 0l, 1000*60*60*everyXHour);
	}
	
}
