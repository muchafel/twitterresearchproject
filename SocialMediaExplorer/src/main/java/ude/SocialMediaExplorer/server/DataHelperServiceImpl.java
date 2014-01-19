package ude.SocialMediaExplorer.server;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DataHelperServiceImpl extends RemoteServiceServlet implements ude.SocialMediaExplorer.client.rmi.IDataHelperService {
	private String[] configHashtags;
	
	//TODO Benny Clusterelemente aus files deserialisieren 
	
	public String[] getConfigHashtags() {
		try {
			File fXmlFile = new File("crawlerConfig.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("hashtag");
			
			int listLength = nList.getLength();
			configHashtags = new String[listLength];
			
			for (int temp = 0; temp < listLength; temp++) {
				// System.out.println(nList.item(temp).getTextContent());
				String hashtag = nList.item(temp).getTextContent();
				configHashtags[temp] = hashtag;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return configHashtags;
	}
}
