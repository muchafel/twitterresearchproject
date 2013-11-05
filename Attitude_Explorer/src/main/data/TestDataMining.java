package main.data;

import main.data.mining.DataMining;
import main.data.mining.twitter.TweetReader_REST;
import twitter4j.Status;

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
		for (Status s : states){
			System.out.println(s.getUser().getName() + " -> " + s.getText());
		}
	}
	
}
