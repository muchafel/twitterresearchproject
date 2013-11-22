package ude.SocialMediaExplorer.data.providing.stored;

import java.io.File;
import java.util.List;

import twitter4j.Status;
import twitter4j.json.DataObjectFactory;
import ude.SocialMediaExplorer.Config;
import ude.SocialMediaExplorer.data.model.PostList;
import ude.SocialMediaExplorer.data.providing.DataProviding;
import ude.SocialMediaExplorer.data.providing.PostConverter;
import ude.SocialMediaExplorer.data.utils.io.FileFilter;
import ude.SocialMediaExplorer.data.utils.io.JSONReader;
import ude.SocialMediaExplorer.data.utils.time.TimeSpan;

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
		if ( dir.exists()  && dir.isDirectory() ){
			File[] files = dir.listFiles();
			for (File file : files){
				if ( file.getName().contains(".json") ){
					JSONReader reader = new JSONReader(file.getAbsolutePath());
					List<String> lines = reader.readJSON();
					for(String line : lines){
						Status s = DataObjectFactory.createStatus(line);
						result.add( PostConverter.fromTwitter(s) );
					}
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
			File[] files = FileFilter.getFilesByTimePeriod(dir, timespan);
			//files
			for (File file : files){
				if ( file.getName().contains(".json") ){
					JSONReader reader = new JSONReader(file.getAbsolutePath());
					List<String> lines = reader.readJSON();
					for(String line : lines){
						Status s = DataObjectFactory.createStatus(line);
						result.add( PostConverter.fromTwitter(s) );
					}
				}
			}
		}
	
		return result;
	}

}
