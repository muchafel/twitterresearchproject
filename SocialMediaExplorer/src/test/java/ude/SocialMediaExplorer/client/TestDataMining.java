package ude.SocialMediaExplorer.client;

import ude.SocialMediaExplorer.data.Status;
import ude.SocialMediaExplorer.data.StatusList;
import ude.SocialMediaExplorer.data.mining.twitter.TweetReader_REST;

public class TestDataMining {

	public static void main(String[] args) {
		TweetReader_REST d = new TweetReader_REST();
		StatusList states = null;
		try {
			states = d.getStates("tatort");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (ude.SocialMediaExplorer.data.Status s : states){
			System.out.println(s.getUser() + " -> " + s.getMessage());
		}
	}
	
}
