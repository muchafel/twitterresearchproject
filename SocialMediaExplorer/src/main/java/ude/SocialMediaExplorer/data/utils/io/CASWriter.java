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
		
		String directory="files/serializedCases/"+hashtag;
	
		String tempFileName =  DocumentMetaData.get(jcas).getDocumentId();
		tempFileName = tempFileName.substring(0, tempFileName.length()-5);
		String[] nameAndDate2 = tempFileName.split("_");
		
		Date tempDate = getDateFromString(nameAndDate2[0]);
		
		File root = new File(directory);
		String[] directories = root.list(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return new File(dir, name).isDirectory();
			}
		});
		
		Date targetDate = getDateFromString(directories[directories.length-1]);
		
		long dateDiff = tempDate.getTime() - targetDate.getTime();
		long dayDiff = dateDiff / (24 * 60 * 60 * 1000);
		
		if (dayDiff < 7) { // schreibe jCas in aktuellsten Ordner
			builder.add(createEngineDescription(BinaryCasWriter.class, BinaryCasWriter.PARAM_FORMAT, "4",
					BinaryCasWriter.PARAM_TARGET_LOCATION, directories[directories.length-1]));
			
		} else { // erzeuge neuen aktuellsten Ordner
			File tempDir = new File(nameAndDate2[0]);
			if (!tempDir.exists()) {
				System.out.println("creating directory: " + nameAndDate2[0]);
				boolean result = tempDir.mkdir();
				if (result) System.out.println("DIR created");
			}
			builder.add(createEngineDescription(BinaryCasWriter.class, BinaryCasWriter.PARAM_FORMAT, "4",
					BinaryCasWriter.PARAM_TARGET_LOCATION, directory+nameAndDate2[0]));	
			targetDate = tempDate;
		}

		AnalysisEngine engine = builder.createAggregate();
		engine.process(jcas);
	}
	
	public static Date getDateFromString(String s) throws ParseException {
		return format.parse(s.substring(0, s.length()-6));
	}
}
