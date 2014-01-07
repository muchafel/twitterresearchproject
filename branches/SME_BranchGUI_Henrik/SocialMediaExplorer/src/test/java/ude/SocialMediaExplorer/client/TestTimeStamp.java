package ude.SocialMediaExplorer.client;

import ude.SocialMediaExplorer.data.utils.time.TimeStamp;

public class TestTimeStamp {
	public static void main(String[] args) {
		try {
			System.out.println(
					TimeStamp.getShort()
					+ "\n" +
					TimeStamp.reverseShort( TimeStamp.getShort() ).toString()
					+ "\n" +
					TimeStamp.getLong()
					+ "\n" +
					TimeStamp.reverseLong( TimeStamp.getLong() ).toString()
					);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
