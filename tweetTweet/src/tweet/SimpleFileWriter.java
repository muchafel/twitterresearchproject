package tweet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// FileWriter --> writes to tweets.txt
public class SimpleFileWriter {

	FileWriter writer;
	File file;
	
	public SimpleFileWriter(){
		file= new File("tweets.txt");
	}
	public void write (String text){
		try {
		       writer = new FileWriter(file ,true);
		       writer.write(text);
		       writer.write(System.getProperty("line.separator"));
		       writer.close();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
	}
}
