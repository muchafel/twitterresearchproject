package ude.SocialMediaExplorer.data.utils.io;



import java.io.File;
import java.io.FilenameFilter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.jcas.JCas;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.io.bincas.BinaryCasWriter;


public class CASWriter {
	
	static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

	public void write(JCas jcas,String hashtag) throws Exception {
		AggregateBuilder builder = new AggregateBuilder();
		
		String directory="files/serializedCases/"+hashtag+"/";
	
		String tempFileName =  DocumentMetaData.get(jcas).getDocumentId();
		System.out.println("doc_id (tempfileName): " + tempFileName);
		//tempFileName = tempFileName.substring(0, tempFileName.length());
		String[] nameAndDate2 = tempFileName.split("_");
		
		System.out.println("NameAndDate: "+nameAndDate2[0]);
		Date tempDate = getDateFromString(nameAndDate2[0]);
		
		File root = new File(directory);
		String[] directories = root.list(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return new File(dir, name).isDirectory();
			}
		});
		System.out.println("dirs: "+directories);
		
		Date targetDate = new Date();
		if (directories != null) {
			targetDate = getDateFromStringShort(directories[directories.length-1]);
			System.out.println("Datum aus Ordner gelesen:" + targetDate);
		} else { 
			targetDate = format.parse("20121221");
			System.out.println("kein ordner, Datum manuell:" + targetDate);
		}
		
		long dateDiff = tempDate.getTime() - targetDate.getTime();
		long dayDiff = dateDiff / (24 * 60 * 60 * 1000);
		
		if (dayDiff < 7) { // schreibe jCas in aktuellsten Ordner
			String abc = directory + format.format(targetDate);
			System.out.println("daydiff kleiner 7. Order, in den geschrieben wird:" + abc);
			builder.add(createEngineDescription(BinaryCasWriter.class, BinaryCasWriter.PARAM_FORMAT, "4",
					BinaryCasWriter.PARAM_TARGET_LOCATION, abc));
			
		} else { // erzeuge neuen aktuellsten Ordner
			File tempDir = new File(nameAndDate2[0].substring(0, nameAndDate2[0].length()-6));
			if (!tempDir.exists()) {
				System.out.println("creating directory: " + nameAndDate2[0]);
				boolean result = tempDir.mkdir();
				if (result) System.out.println("DIR created");
			}
			String abc = directory+tempDir;
			System.out.println("daydiff min. 7. Order, in den geschrieben wird:" + abc);
			builder.add(createEngineDescription(BinaryCasWriter.class, BinaryCasWriter.PARAM_FORMAT, "4",
					BinaryCasWriter.PARAM_TARGET_LOCATION, abc));	
			targetDate = tempDate;
		}

		AnalysisEngine engine = builder.createAggregate();
		engine.process(jcas);
	}
	
	public static Date getDateFromString(String s) throws ParseException {
		return format.parse(s.substring(0, s.length()-6));
	}
	
	public static Date getDateFromStringShort(String s) throws ParseException {
		return format.parse(s);
	}
}
