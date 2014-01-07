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
		List<String> binCases= getBinCases();
		CASReader reader= new CASReader();
		
		List<JCas> jCases= getJCases(binCases,reader);
		
		//testing
		for (JCas jcas : jCases){
			System.out.println("Original text: "+jcas.getDocumentText());
		}


	}

	private static List<JCas> getJCases(List<String> binCases,CASReader reader) {
		List<JCas> jCases= new ArrayList<JCas>();
		
		for(String path : binCases){
			try {
				jCases.add(reader.read(createTypeSystemDescription("desc.type.metadata"), path));
			} catch (Exception e) {
				System.out.println("Deserialization failed!");
				e.printStackTrace();
			}
		}
		return jCases;
	}

	private static List<String> getBinCases() {
		List<String> result= new ArrayList<String>();
		File dir= new File("files/serializedCases");
		File[] files = dir.listFiles();
		if (files != null) { 
			for (int i = 0; i < files.length; i++) {
//				System.out.print(i+". "+files[i].getAbsolutePath());
//				result.add(files[i].getAbsolutePath());
				result.add("files/serializedCases");
			}
		}
	return result;
	}

}
