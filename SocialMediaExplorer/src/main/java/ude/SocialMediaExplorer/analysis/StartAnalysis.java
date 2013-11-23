package ude.SocialMediaExplorer.analysis;

import java.util.Date;

import ude.SocialMediaExplorer.Config;
import ude.SocialMediaExplorer.data.post.providing.IPostProviding;
import ude.SocialMediaExplorer.data.post.providing.stored.TwitterJSONFileReader;
import ude.SocialMediaExplorer.data.utils.time.TimeSpan;

public class StartAnalysis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TimeSpan ts = new TimeSpan(new Date());
		IPostProviding dp = new TwitterJSONFileReader(); //TODO: init via Config and reflection
		
		for (String hashtag : Config.crawler_hashtags){
			try {
				new Analysis(dp.getPosts( hashtag )).run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
