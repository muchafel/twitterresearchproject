package ude.SocialMediaExplorer.client;

import ude.SocialMediaExplorer.analysis.SentimentLexicon;

public class TestLexicon {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SentimentLexicon lexEnglish = new SentimentLexicon("en");
		SentimentLexicon lexGerman= new SentimentLexicon("de");
		
		System.out.println(lexGerman.getSentiment("Zerstörung", "de"));
		System.out.println(lexGerman.getSentiment("Teufel", "de"));
		System.out.println(lexGerman.getSentiment("Opfer", "de"));
		System.out.println(lexGerman.getSentiment("Hure", "de"));
		System.out.println(lexGerman.getSentiment("Elend", "de"));
		System.out.println(lexGerman.getSentiment("Armut", "de"));
		System.out.println(lexGerman.getSentiment("Hass", "de"));
		System.out.println(lexGerman.getSentiment("Dreck", "de"));
		System.out.println(lexGerman.getSentiment("Leben", "de"));
		System.out.println(lexGerman.getSentiment("Katze", "de"));
		System.out.println(lexGerman.getSentiment("Schönheit", "de"));

	}

}
