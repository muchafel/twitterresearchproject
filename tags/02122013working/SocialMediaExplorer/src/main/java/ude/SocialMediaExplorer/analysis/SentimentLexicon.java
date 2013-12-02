package ude.SocialMediaExplorer.analysis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class SentimentLexicon {

	private String language;
	

	private HashMap<String, ArrayList<Integer>> lexicon ;
	public SentimentLexicon(String language){
		this.language=language;
		lexicon = new HashMap<String, ArrayList<Integer>>();
		genrateLexicon(language);
		//System.out.println(lexicon);
	}
	/*
	* generates the lexicon in the specfied language
	*/
	private void genrateLexicon(String language) {
		String path = "";
		if (language.equals("En")) {
			path = "files/SentimentLexicon_English.txt";
			try {
				FileReader fr = new FileReader(path);
				BufferedReader br = new BufferedReader(fr);
				String currentString = "";
				while ((currentString = br.readLine()) != null) {
					ArrayList<Integer> values = new ArrayList<Integer>();
					String[] entry = currentString.split(" ");
					for (int i = 1; i < entry.length; i++) {
						values.add(Integer.valueOf(entry[i].split(":")[1]));
					}
					lexicon.put(entry[0], values);
				}
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (language.equals("De")) {
			path = "files/SentimentLexicon_German.txt";
			try {
				FileReader fr = new FileReader(path);
				BufferedReader br = new BufferedReader(fr);
				String currentString = "";
				while ((currentString = br.readLine()) != null) {
					ArrayList<Integer> values = new ArrayList<Integer>();
					String[] entry = currentString.split("\t");
					if (entry[3].equals("positive")){
						values.add(1);
						values.add(0);
					}
					else if (entry[3].equals("negative")){
						values.add(0);
						values.add(1);
					}
					else {
						values.add(0);
						values.add(0);
					}
//					for (int i = 1; i < entry.length; i++) {
//						if (entry[3].equals("positiv")&& i==1||entry[3].equals("negativ")&&i==2){
//							values.add(1);
//						}else{
//							values.add(0);
//						}
//					}
					lexicon.put(entry[0], values);
				}
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			return;
	}

	/*
	 * returns 0 if there is no entry in the lexicon or the entry is positiv and
	 * negativ (souldn't happen) else returns 1 for positive and -1 for negative
	 * returns -999 for wrong language
	 */
	public int getSentiment(String text, String tweetLanguage){
		if(tweetLanguage.equals(this.language)){
		ArrayList<Integer> values=lexicon.get(text);
		if(values != null ){
			return (values.get(0)-values.get(1));
		}else{
			return 0;
		}
		}else{
			return -999;
		}
	}
	public String getLanguage() {
		return language;
	}
}
