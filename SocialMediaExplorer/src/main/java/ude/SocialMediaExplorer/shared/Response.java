package ude.SocialMediaExplorer.shared;

import java.io.Serializable;
import java.util.ArrayList;

import ude.SocialMediaExplorer.shared.exchangeFormat.ClusterElement;

/**
 * Shared data representation (all infos for clients ui should be written in here)
 * @author henrikdetjen
 *
 */
@SuppressWarnings("serial")
public class Response implements Serializable {
	
	ClusterElement ce;
	
	
	public ClusterElement getCe() {
		return ce;
	}

	public void setCe(ClusterElement ce) {
		this.ce = ce;
	}

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
