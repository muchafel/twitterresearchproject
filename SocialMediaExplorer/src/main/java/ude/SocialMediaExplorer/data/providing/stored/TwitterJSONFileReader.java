package ude.SocialMediaExplorer.data.providing.stored;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import twitter4j.Status;
import twitter4j.json.DataObjectFactory;
import ude.SocialMediaExplorer.Config;
import ude.SocialMediaExplorer.data.model.PostList;
import ude.SocialMediaExplorer.data.providing.DataProviding;
import ude.SocialMediaExplorer.data.providing.PostConverter;
import ude.SocialMediaExplorer.data.utils.io.JSONReader;
import ude.SocialMediaExplorer.data.utils.io.TextFileReader;
import ude.SocialMediaExplorer.data.utils.time.TimeSpan;
import ude.SocialMediaExplorer.data.utils.time.TimeStamp;

/**
 * reads posts from filesystem
 * @author henrikdetjen
 *
 */
public class TwitterJSONFileReader implements DataProviding{

	public PostList getPosts(String hashtag) throws Exception {
		
		hashtag = hashtag.toLowerCase();
		PostList result = new PostList();
		
		File dir = new File(Config.location_tweets + hashtag);
		if ( dir.exists()  ){
			File[] files = dir.listFiles();
			for (File file : files){
				JSONReader reader= new JSONReader(file.getAbsolutePath());
				List<String> lines = reader.readJSON();
				for(String line : lines){
					Status s = DataObjectFactory.createStatus(line);
					result.add(PostConverter.fromTwitter(s) );
				}
			}
		}
	
		return result;
	}

	public PostList getPosts(String hashtag, TimeSpan timespan) throws Exception {
		hashtag = hashtag.toLowerCase();
		
		PostList result = new PostList();
		//Folder
		File dir = new File(Config.location_tweets + hashtag);
		if ( dir.exists() && dir.isDirectory() ){
			File[] files = dir.listFiles();
			//files
			for (File f : files){
				//check time
				String timestamp = f.getName().replaceAll(".json", "").split("_")[0];
				try{
					Date d = TimeStamp.reverseLong(timestamp);
					//if in timespan
					if ( timespan.includes(d) ){
						ArrayList<String> lines = TextFileReader.read(f.getAbsolutePath());
						for(String line : lines){
							Status s = DataObjectFactory.createStatus(line);
							result.add( PostConverter.fromTwitter(s) );
						}
					}
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
			}
		}
	
		return result;
	}

}
