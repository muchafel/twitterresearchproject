package ude.SocialMediaExplorer.data.utils.io;


import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.CasCreationUtils;
import org.junit.rules.TemporaryFolder;

import de.tudarmstadt.ukp.dkpro.core.api.io.ResourceCollectionReaderBase;
import de.tudarmstadt.ukp.dkpro.core.io.bincas.BinaryCasReader;


public class CASReader {

	
	public JCas read(TypeSystemDescription aTSD,String path) throws Exception {
		System.out.println("--- READING ---");
		@SuppressWarnings("deprecation")
		CollectionReader reader = CollectionReaderFactory.createReader(
				BinaryCasReader.class, ResourceCollectionReaderBase.PARAM_PATH,
				path,
				ResourceCollectionReaderBase.PARAM_PATTERNS,
				new String[] { ResourceCollectionReaderBase.INCLUDE_PREFIX
						+ "*.bin" });

		CAS cas = CasCreationUtils.createCas(aTSD, null, null);
		//reader.getNext(cas);
		
		return cas.getJCas();

	}
}
