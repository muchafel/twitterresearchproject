package ude.SocialMediaExplorer.client;

import ude.SocialMediaExplorer.data.providing.stored.TwitterJSONFileReader;
import ude.SocialMediaExplorer.data.utils.time.TimeSpan;
import ude.SocialMediaExplorer.data.utils.time.TimeStamp;

public class TestTwitterJSONFileReader {
	public static void main(String[] args) {
		TwitterJSONFileReader bla =  new TwitterJSONFileReader();
		
		try {
//			bla.getPosts("tatort").print();
			bla.getPosts("TATORT",new TimeSpan(TimeStamp.reverseLong("20131119102440"))).print();
			bla.getPosts("gibts nicht...").print();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
