package ude.SocialMediaExplorer.server;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ude.SocialMediaExplorer.Config;
import ude.SocialMediaExplorer.analysis.Analysis;
import ude.SocialMediaExplorer.analysis.Clusterer;
import ude.SocialMediaExplorer.data.post.PostList;
import ude.SocialMediaExplorer.data.post.collecting.TwitterCrawler;
import ude.SocialMediaExplorer.data.post.providing.IPostProviding;
import ude.SocialMediaExplorer.data.post.providing.stored.TwitterJSONFileReader;

/**
 * Start the Server
 * 
 * - init crawler
 * - init analysis
 * 
 * - repeat procudure
 * 
 * @author henrikdetjen
 * 
 */
public class StartServer {

	private static Timer analysis_timer;
	private static Timer crawler_timer;
	private static Thread crawler;

	public static void main( String[] args ) {

		startAnalysisCycle();
		startCrawlerCycle();

	}

	private static void startAnalysisCycle() {

		analysis_timer = new Timer();
		TimerTask hourlyTask = new TimerTask() {

			@Override
			public void run() {

				//get actual hashtags
				ArrayList<String> actualHashtags = Config.get_crawler_hashtags_actual();
				//init reader
				IPostProviding dp = new TwitterJSONFileReader(); //TODO: init via Config and reflection

				for ( String hashtag : actualHashtags ) {

					PostList pl = null;
					try {
						pl = dp.getPosts( hashtag );
					}
					catch ( Exception e ) {}

					//start analysis and clustering
					new Analysis( pl ).run( hashtag ); // FIXME ?? doppelt gemoppelt
					new Clusterer().cluster( hashtag );

				}
				
			}
		};

		analysis_timer.schedule( hourlyTask, Config.get_crawler_newFileInterval() );
	}
	
	
	private static void startCrawlerCycle() {

		crawler_timer = new Timer();
		TimerTask hourlyTask = new TimerTask() {

			@Override
			public void run() {

				crawler.interrupt();
				crawler = new TwitterCrawler();
								
			}
		};

		crawler_timer.schedule( hourlyTask, Config.get_crawler_newFileInterval() );
	}

}
