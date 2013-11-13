package ude.SocialMediaExplorer.analysis;

import java.util.ArrayList;

import ude.SocialMediaExplorer.shared.Response;

/**
 * Result Storage and Processing
 * @author henrikdetjen
 *
 */
public class ResultManager {
	/**
	 * result storage
	 */
	private ArrayList<Result> results;
	public void addResult(Result r){
		results.add(r);
	}
	
	/**
	 * constructor
	 */
	public ResultManager(){
		results = new ArrayList<Result>();
	}
	
	/**
	 * Creates a new client response from results
	 * @return {@link Response}
	 */
	public Response createResponse(){
		Response response = new Response();
		for (Result r : results){
			response.addString(r.test);
		}
		return response;
	}
}
