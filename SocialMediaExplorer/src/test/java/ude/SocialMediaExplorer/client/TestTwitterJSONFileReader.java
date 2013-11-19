package ude.SocialMediaExplorer.client;

import ude.SocialMediaExplorer.data.providing.stored.TwitterJSONFileReader;

public class TestTwitterJSONFileReader {
	public static void main(String[] args) {
		TwitterJSONFileReader bla =  new TwitterJSONFileReader();
		
		try {
			bla.getPosts("tatort").print();
			bla.getPosts("TATORT").print();
			bla.getPosts("gibts nicht...").print();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
