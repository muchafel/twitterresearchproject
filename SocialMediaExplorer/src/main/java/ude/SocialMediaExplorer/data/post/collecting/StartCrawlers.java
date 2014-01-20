package ude.SocialMediaExplorer.data.post.collecting;

public class StartCrawlers {

	public static void main( String[] args ) {
		
		new TwitterCrawler().run();
		
		// possible future crawlers for facebook xing etc
	}
}
