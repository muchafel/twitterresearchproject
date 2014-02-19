package ude.SocialMediaExplorer.analysis;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import ude.SocialMediaExplorer.Config;
import ude.SocialMediaExplorer.data.post.PostList;
import ude.SocialMediaExplorer.data.post.providing.IPostProviding;
import ude.SocialMediaExplorer.data.post.providing.stored.TwitterJSONFileReader;
import ude.SocialMediaExplorer.data.utils.time.TimeSpan;

public class StartAnalysis {

	/**
	 * @param args
	 */
	private boolean running = true;
	static String hashtagToAnalyze = "tatort";
	static private PostList pl;
	static Timer timer;
	
	
	public static void main(String[] args) {
		
		TimeSpan ts = new TimeSpan(new Date());
		IPostProviding dp = new TwitterJSONFileReader(); //TODO: init via Config and reflection
		
		for (String hashtag : Config.get_crawler_hashtags()){
			try {
				new Analysis(dp.getPosts( hashtag )).run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	private static void startAnalysisCycle(int everyXHour) {
	
		timer = new Timer();
		TimerTask hourlyTask = new TimerTask() {
			@Override
			public void run() {
				new Analysis(pl).run(hashtagToAnalyze);
				new Clusterer().cluster(hashtagToAnalyze);
			}
		};
		
		timer.schedule (hourlyTask, 0l, 1000*everyXHour);
	}

}
