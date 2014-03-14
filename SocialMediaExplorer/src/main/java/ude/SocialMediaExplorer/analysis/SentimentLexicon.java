package ude.SocialMediaExplorer.analysis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

/*
 * class that connects a sentiment lexicon to the code 
 * (for examples of lexiocn see the attached sentimentlexicon.txt)
 */
public class SentimentLexicon {
	private String language;
	private HashMap<String, Double> lexicon ;
	
	public SentimentLexicon(String language){
		this.language=language;
		lexicon = new HashMap<String, Double>();
		genrateLexicon(language);
	}
	/*
	* generates the lexicon in the specfied language
	*/
	private void genrateLexicon(String language) {
		String path = "";
		if (language.equals("en")) {
			//TODO add EnglishLexicon
			path = "files/semantic_oriented_SentimentLexicon_German.txt";
			try {
				FileReader fr = new FileReader(path);
				BufferedReader br = new BufferedReader(fr);
				String currentString = "";
				while ((currentString = br.readLine()) != null) {
					String[] entry = currentString.split(" : ");
					lexicon.put(entry[0], Double.valueOf(entry[1]));
				}
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (language.equals("de")) {
			path = "files/semantic_oriented_SentimentLexicon_German.txt";
			try {
				FileReader fr = new FileReader(path);
				BufferedReader br = new BufferedReader(fr);
				String currentString = "";
				while ((currentString = br.readLine()) != null) {
					String[] entry = currentString.split(" : ");
					lexicon.put(entry[0], Double.valueOf(entry[1]));
				}
				br.close();
				
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
	public double getSentiment(String text, String tweetLanguage){
		if(tweetLanguage.equals(this.language)){
			double value = 0.0;
			if(lexicon.containsKey(text)){
			value = lexicon.get(text);
			return value;
			}
		}
		return 0;
	}
	public String getLanguage() {
		return language;
	}
}
