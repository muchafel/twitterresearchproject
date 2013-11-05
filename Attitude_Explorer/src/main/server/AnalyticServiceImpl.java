package main.server;


import main.analysis.Analysis;
import main.client.interfaces.AnalyticService;
import main.shared.Response;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * An Implementation of {@link AnalyticServiceAsync} 
 * @author henrikdetjen
 *
 */
@SuppressWarnings("serial")
public class AnalyticServiceImpl extends RemoteServiceServlet implements AnalyticService{

	@Override
	public Response analyseHashtag(String hashtag)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		try {
			return new Analysis(hashtag).call();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	//Async
//	@Override
//	public void analyseHashtag(String hashtag, AsyncCallback<Result> callback)
//			throws IllegalArgumentException {
//		//no php, html code etc.
//		String input = FieldVerifier.escapeHtml(hashtag);
//		//"#hashtag" -> "hashtag"
//		if (input.charAt(0) == '#')
//			input = input.replaceFirst("#", "");
//		//call analysis with input
//		try {
//			Result r = new Analysis(input).call();
//			callback.onSuccess(r);
//		} catch (Exception e) {
//			callback.onFailure(e);
//		}
//		
//	}

}
