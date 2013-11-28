package languageProcessing;

import java.util.List;

import de.tudarmstadt.ukp.dkpro.core.api.frequency.util.FrequencyDistribution;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JSONReader fileReader = new JSONReader("src/test/resources/names/tvog_20131031.json");
		fileReader.readJSON();
		List<String> list =fileReader.getTextList();
		MostFrequentFinder finder= new MostFrequentFinder();
		FrequencyDistribution<String> freq = new FrequencyDistribution<String>();
		
		for (String tweet: list){
			try {
				freq.incAll(finder.test(tweet));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println(freq.getMostFrequentSamples(30));
	}

}
