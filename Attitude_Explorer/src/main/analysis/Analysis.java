package main.analysis;

import java.util.concurrent.Callable;

//import twitter4j.Status;
import main.analysis.components.language.Language;
import main.analysis.components.language.LanguageClassificator;
import main.analysis.interfaces.LanguageClassification;
//import main.data.StatusList;
//import main.data.mining.DataMining;
//import main.data.mining.twitter.TweetReader_REST;
import main.shared.Response;

/**
 * App core: searches data for hashtag and analyzes them, afterwards a client response will be created
 * @author henrikdetjen
 *
 */
public class Analysis implements Callable<Response>{

	String hashtag;
//	DataMining dataGrabber;
	LanguageClassification lanuageClassificator;
	
	public Analysis(String hashtag){
		this.hashtag = hashtag;
//		dataGrabber = new TweetReader_REST();
		lanuageClassificator = new LanguageClassificator();
	}
	
	@Override
	public Response call() throws Exception {
		
//		//get Data
//		StatusList states = dataGrabber.getStates(hashtag);
//		
		ResultManager resultManager = new ResultManager();
//		
//		for (Status s : states){
//			
//			Result result = new Result();
//			
//			//ANALYZE 
//			Language l = lanuageClassificator.getLanguage(s);
//			result.test = s.getText();
//			//result.setLanguage(l); TODO: specify result
//			//...TODO: add further steps
//			
//			resultManager.addResult(result);
//		}
		System.out.println("called!");
//		... states -(analayse state)-> analysis.Result -(aggregation)-> analysis.ResultList -(extract information)-> shared.Response 
//		 ... 
		return resultManager.createResponse();
	}

}