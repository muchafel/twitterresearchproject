package ude.SocialMediaExplorer.analysis;

import java.util.Date;

import ude.SocialMediaExplorer.data.utils.time.TimeSpan;

public class StartAnalysis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		new Analysis("tatort",new TimeSpan(new Date())).run();

	}

}
