package ude.SocialMediaExplorer.client;

import ude.SocialMediaExplorer.analysis.Clusterer;

public class TestClusterer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String hashtagToAnalyze="tatort";
		new Clusterer().cluster(hashtagToAnalyze);

	}

}
