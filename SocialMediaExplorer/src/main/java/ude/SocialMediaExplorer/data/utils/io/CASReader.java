package ude.SocialMediaExplorer.data.utils.io;


import java.io.File;

import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.CasCreationUtils;
import org.junit.rules.TemporaryFolder;

import de.tudarmstadt.ukp.dkpro.core.api.io.ResourceCollectionReaderBase;
import de.tudarmstadt.ukp.dkpro.core.io.bincas.BinaryCasReader;


public class CASReader {
	
	public TemporaryFolder testFolder = new TemporaryFolder();

	
	public CAS read(TypeSystemDescription aTSD) throws Exception {
		System.out.println("--- READING ---");
		CollectionReader reader = CollectionReaderFactory.createReader(
				BinaryCasReader.class, ResourceCollectionReaderBase.PARAM_PATH,
				testFolder.getRoot().getPath(),
				ResourceCollectionReaderBase.PARAM_PATTERNS,
				new String[] { ResourceCollectionReaderBase.INCLUDE_PREFIX
						+ "*.bin" });

		CAS cas = CasCreationUtils.createCas(aTSD, null, null);
		reader.getNext(cas);
		
		return cas;
		
		// createEngine(CASDumpWriter.class).process(cas);

//		String refText = readFileToString(new File("src/test/resources/texts/example1.txt"));

//		assertEquals(refText, cas.getDocumentText());
//		assertEquals("latin", cas.getDocumentLanguage());

	}
}
