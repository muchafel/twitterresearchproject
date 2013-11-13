package ude.SocialMediaExplorer.shared;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Shared data representation (all infos for clients ui should be written in here)
 * @author henrikdetjen
 *
 */
@SuppressWarnings("serial")
public class Response implements Serializable {
	
	ArrayList<String> list = new ArrayList<String>();
	public void addString(String s){
		list.add(s);
	}
	
	public Response() {
		System.out.println("new Response");
	}
	
	public String toString(){
		String res = "result: ";
		for(String s : list){
			res += s+"\n";
		}
		return res;
	}

	
}
