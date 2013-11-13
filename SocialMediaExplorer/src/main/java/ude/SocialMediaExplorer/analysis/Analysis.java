package ude.SocialMediaExplorer.analysis;

import java.util.concurrent.Callable;






//import twitter4j.Status;
import ude.SocialMediaExplorer.analysis.components.language.Language;
import ude.SocialMediaExplorer.analysis.components.language.LanguageClassificator;
import ude.SocialMediaExplorer.analysis.interfaces.LanguageClassification;
import ude.SocialMediaExplorer.data.Status;
import ude.SocialMediaExplorer.data.StatusList;
import ude.SocialMediaExplorer.data.mining.DataMining;
import ude.SocialMediaExplorer.data.mining.twitter.TweetReader_REST;
import ude.SocialMediaExplorer.shared.Response;

/**
 * App core: searches data for hashtag and analyzes them, afterwards a client response will be created
 * @author henrikdetjen
 *
 */
public class Analysis implements Callable<Response>{

	String hashtag;
	DataMining dataGrabber;
	LanguageClassification lanuageClassificator;
	
	public Analysis(String hashtag){
		this.hashtag = hashtag;
		dataGrabber = new TweetReader_REST();
		lanuageClassificator = new LanguageClassificator();
	}

	public Response call() throws Exception {
		//get Data
		StatusList states = dataGrabber.getStates(hashtag);
		
		ResultManager resultManager = new ResultManager();
		
		for (Status s : states){
			
			Result result = new Result();
			
			//ANALYZE 
			Language l = lanuageClassificator.getLanguage(s.getMessage());
			result.test = s.getMessage();
			//result.setLanguage(l); TODO: specify result
			//...TODO: add further steps i.e rating
			
			resultManager.addResult(result);
		}
		
		System.out.println("called!");
//				... states -(analayse state)-> analysis.Result -(aggregation)-> analysis.ResultList -(extract information)-> shared.Response 
//				 ... 
		return resultManager.createResponse();
	}
	
}