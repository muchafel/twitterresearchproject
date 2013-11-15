package crawler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Config {

	private List<String> hashtags;
	public Config(){
		
		hashtags = new ArrayList<String>();
		try{
		File fXmlFile = new File("crawlerConfig.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("hashtag");
		
		
		for (int temp = 0; temp < nList.getLength(); temp++){

			//System.out.println(nList.item(temp).getTextContent());
			String hashtag=nList.item(temp).getTextContent();
			hashtags.add(hashtag);
			this.createFolder(hashtag);
		}
		
		}
		catch (Exception e ){
			e.printStackTrace();
		}
	}
	
	public String[] getHashtags() {
		 
//		String[] tempArray= new String[hashtags.size()];
//		for (String a : hashtags){
//			
//		}
		return (String[]) hashtags.toArray( new String[hashtags.size()]);
	}
	public List<String> getHashtagList() {
		
		return hashtags;
	}
	
	private void createFolder(String folder) {

		try {
			File dir = new File(folder);
			dir.mkdirs();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
