package ude.SocialMediaExplorer.data.utils.io;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
 


public class JSONReader {
	
	private String path;
	private List<String> list = new ArrayList<String>();

	
	public JSONReader(String path){
		this.path=path;
	}
	public List<String> readJSON(){
	
		
		try{
		FileReader fr = new FileReader(path);
	    BufferedReader br = new BufferedReader(fr);
	    String currentJSONString = "";
	    while ((currentJSONString = br.readLine()) != null)
	    {
	      list.add(currentJSONString);
	    }
	    br.close(); 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return this.list;

    }
	
}
