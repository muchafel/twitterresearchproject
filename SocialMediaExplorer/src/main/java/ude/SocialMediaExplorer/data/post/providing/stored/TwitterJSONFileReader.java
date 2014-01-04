package ude.SocialMediaExplorer.data.post.providing.stored;

import java.io.File;
import java.util.List;

import twitter4j.Status;
import twitter4j.json.DataObjectFactory;
import ude.SocialMediaExplorer.Config;
import ude.SocialMediaExplorer.data.post.PostList;
import ude.SocialMediaExplorer.data.post.providing.IPostProviding;
import ude.SocialMediaExplorer.data.post.providing.PostConverter;
import ude.SocialMediaExplorer.data.utils.io.FileFilter;
import ude.SocialMediaExplorer.data.utils.io.TextFileReader;
import ude.SocialMediaExplorer.data.utils.time.TimeSpan;

/**
 * reads posts from filesystem
 * @author henrikdetjen
 *
 */
public class TwitterJSONFileReader implements IPostProviding{

	public PostList getPosts(String hashtag) throws Exception {
		
		hashtag = hashtag.toLowerCase();
		PostList result = new PostList();
		int id= 0;
		
		File dir = new File(Config.get_location_tweets() + hashtag);
		if ( dir.exists()  && dir.isDirectory() ){
			File[] files = dir.listFiles();
			for (File file : files){
				if ( file.getName().contains(".json") ){
					List<String> lines = TextFileReader.read(file.getAbsolutePath());
					for(String line : lines){
						Status s = DataObjectFactory.createStatus(line);
						result.add( PostConverter.fromTwitter(s,id) );
						id++;
					}
				}
			}
		}
	
		return result;
	}

	///TODO:
	// ID-handling/ Ablage der cases üderdenke, --> Gefahr von Verwechslung 
	public PostList getPosts(String hashtag, TimeSpan timespan) throws Exception {
		hashtag = hashtag.toLowerCase();
		int id= 0;
		
		PostList result = new PostList();
		//Folder
		File dir = new File(Config.get_location_tweets() + hashtag);
		if ( dir.exists() && dir.isDirectory() ){
			File[] files = FileFilter.getFilesByTimePeriod(dir, timespan);
			//files
			for (File file : files){
				if ( file.getName().contains(".json") ){
					List<String> lines = TextFileReader.read(file.getAbsolutePath());
					for(String line : lines){
						Status s = DataObjectFactory.createStatus(line);
						result.add( PostConverter.fromTwitter(s,id) );
						id++;
					}
				}
			}
		}
	
		return result;
	}

}
