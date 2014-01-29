package ude.SocialMediaExplorer.client;

import ude.SocialMediaExplorer.analysis.SentimentLexicon;

public class TestLexicon {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SentimentLexicon lexEnglish = new SentimentLexicon("en");
		SentimentLexicon lexGerman= new SentimentLexicon("de");
		
		System.out.println(lexGerman.getSentiment("toll", "de"));
		System.out.println(lexGerman.getSentiment("nett", "de"));
		System.out.println(lexGerman.getSentiment("schön", "de"));
		System.out.println(lexGerman.getSentiment("normal", "de"));

	}

}
