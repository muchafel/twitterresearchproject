package ude.SocialMediaExplorer.analysis;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static org.apache.uima.fit.factory.TypeSystemDescriptionFactory.*;
import org.apache.uima.jcas.JCas;

import ude.SocialMediaExplorer.data.utils.io.CASReader;

public class Clusterer {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		CASReader reader= new CASReader();
		List<JCas> jCases= new ArrayList<JCas>();
		try {
			jCases=reader.read("files/serializedCases");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//testing
		for (JCas jcas : jCases){
			System.out.println("Original text: "+jcas.getDocumentText());
		}


	}	

}
