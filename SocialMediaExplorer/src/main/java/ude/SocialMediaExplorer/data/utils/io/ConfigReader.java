package ude.SocialMediaExplorer.data.utils.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class ConfigReader {
	private static ArrayList<String> hashtags;
	
	public static ArrayList<String> readHashtags() {
		try {
			File fXmlFile = new File("crawlerConfig.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("hashtag");
			
			int listLength = nList.getLength();
			hashtags = new ArrayList<String>();
			
			for (int temp = 0; temp < listLength; temp++) {
				// System.out.println(nList.item(temp).getTextContent());
				String hashtag = nList.item(temp).getTextContent();
				hashtags.add(hashtag);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashtags;
	}
}
