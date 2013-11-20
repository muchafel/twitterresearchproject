package ude.SocialMediaExplorer.data.utils.io;



import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.junit.rules.TemporaryFolder;

import static org.apache.uima.fit.pipeline.SimplePipeline.*;

import de.tudarmstadt.ukp.dkpro.core.api.io.ResourceCollectionReaderBase;
import de.tudarmstadt.ukp.dkpro.core.io.bincas.BinaryCasWriter;
import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader;


public class CASWriter {
	
	public TemporaryFolder testFolder = new TemporaryFolder();

	public void write(String aFormat) throws Exception {
		System.out.println("--- WRITING ---");
		CollectionReader textReader = CollectionReaderFactory.createReader(
				TextReader.class, ResourceCollectionReaderBase.PARAM_PATH,
				"src/test/resources/texts",
				ResourceCollectionReaderBase.PARAM_PATTERNS, "[+]*.txt",
				ResourceCollectionReaderBase.PARAM_LANGUAGE, "latin");

		AnalysisEngine xmiWriter = AnalysisEngineFactory.createEngine(
				BinaryCasWriter.class, BinaryCasWriter.PARAM_FORMAT, aFormat,
				BinaryCasWriter.PARAM_TARGET_LOCATION, testFolder.getRoot()
						.getPath());

		// AnalysisEngine dumper = createEngine(CASDumpWriter.class);

		runPipeline(textReader, xmiWriter);
//		assertTrue(new File(testFolder.getRoot(), "example1.txt.bin").exists());

	}
}
