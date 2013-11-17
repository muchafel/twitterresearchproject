package languageProcessing;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
 


public class JSONReader {
	
	private String path;
	private String text;
	private List<String> list = new ArrayList<String>();
	private List<JSONObject> jsonList=  new ArrayList<JSONObject>();
	
	public JSONReader(String path){
		this.path=path;
	}
	
	public void readJSON(){
	
		
		try{
	    JSONParser parser = new JSONParser();	
		FileReader fr = new FileReader(path);
	    BufferedReader br = new BufferedReader(fr);

	    String currentJSONString = "";
	    while ((currentJSONString = br.readLine()) != null)
	    {
	      JSONObject currentObject = (JSONObject) parser.parse(currentJSONString);
	      jsonList.add(currentObject);
	      list.add((String) currentObject.get("text"));
	      text+=(String) currentObject.get("text");
	    }
	    br.close(); 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(text);

    }
	public List<String> getTextList(){
		return this.list;
	}
	public List<JSONObject> getObjects(){
		return this.jsonList;
	}
	public String getText(){
		return this.text;
	}
}
