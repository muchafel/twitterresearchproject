package crawler;

import java.util.ArrayList;
import java.util.List;
import twitter4j.HashtagEntity;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.json.DataObjectFactory;

public class CrawlerListener implements StatusListener {
	

		private List<String> list;
		private List<HashtagWriter> hashtagWriters;
//		private SimpleFileWriter writer;

		public CrawlerListener(List<String> list){
//			this.list=list;
			
			hashtagWriters= new ArrayList<HashtagWriter>();
			for (String a : list){

				hashtagWriters.add(new HashtagWriter(a));
			}
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
		         String statusJson = DataObjectFactory.getRawJSON(status);
		         
		         //System.out.println("JSON: "+statusJson);
		         for (HashtagWriter writer : hashtagWriters){
		        	 for (HashtagEntity a: status.getHashtagEntities()){
		        		 if(a.getText().toLowerCase().equals(writer.getName())){
//		        			 System.out.println(" IF: "+a.toString());
		        			 writer.write(statusJson);
		        		 }
		        	 }
		         }
		         
		         
		         //writes to file
//		         writer.write("@" + status.getUser().getScreenName() + " - " + status.getText() + " ID :" + status.getId() + " Geo: "+ 
//		        		 status.getGeoLocation() + "  Place: "+status.getPlace() + " Date:  "+status.getCreatedAt());
//		         list.add(username+"  "+profileLocation+" "+tweetId+" "+content);
			}

			@Override
			public void onTrackLimitationNotice(int i) {
				list.add(String.valueOf(i));
				
			}

		}

