package datamining;


import java.util.List;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.User;

// implementation of StatusListener
public class CustomTwitterStreamListener implements StatusListener {

private List<String> list;
private SimpleFileWriter writer;

public CustomTwitterStreamListener(List<String> list, SimpleFileWriter writer){
	this.list=list;
	this.writer= writer;
}


	
	@Override
	public void onException(Exception arg0) {
		list.add("Error: "+arg0.getMessage());
		
	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice arg0) {
		System.out.println("StatusDeletionNotice: "+arg0.toString());
		
	}

	@Override
	public void onScrubGeo(long id, long statusId) {
		System.out.println("Got scrub_geo event userId:" + id + " upToStatusId:" + statusId);
		
	}

	@Override
	public void onStallWarning(StallWarning arg0) {
		System.out.println("Got stall warning:" + arg0);
		
	}

	@Override
	public void onStatus(Status status) {
         
         String username = status.getUser().getScreenName(); 


         String content = status.getText();

         System.out.println("@" +username+"   "+content);
         //writes to file
         writer.write("@" + status.getUser().getScreenName() + " - " + status.getText() + " ID :" + status.getId() + " Geo: "+ 
        		 status.getGeoLocation() + "  Place: "+status.getPlace() + " Date:  "+status.getCreatedAt());
//         list.add(username+"  "+profileLocation+" "+tweetId+" "+content);
	}

	@Override
	public void onTrackLimitationNotice(int i) {
		list.add(String.valueOf(i));
		
	}

}
