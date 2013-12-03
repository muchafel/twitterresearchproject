package ude.SocialMediaExplorer.data.utils.io;


import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.CasCreationUtils;
import org.junit.rules.TemporaryFolder;

import de.tudarmstadt.ukp.dkpro.core.api.io.ResourceCollectionReaderBase;
import de.tudarmstadt.ukp.dkpro.core.io.bincas.BinaryCasReader;
import static org.apache.uima.fit.factory.TypeSystemDescriptionFactory.*;


public class CASReader {

	
	public List<JCas> read(String path) throws Exception {
		List<JCas> jCases= new ArrayList<JCas>();
		System.out.println("--- READING ---");
		@SuppressWarnings("deprecation")
		CollectionReader reader = CollectionReaderFactory.createReader(
				BinaryCasReader.class, ResourceCollectionReaderBase.PARAM_PATH,
				path,
				ResourceCollectionReaderBase.PARAM_PATTERNS,
				new String[] { ResourceCollectionReaderBase.INCLUDE_PREFIX
						+ "*.bin" });

		
		
		CAS cas = CasCreationUtils.createCas(createTypeSystemDescription(), null, null);
		
		while (reader.hasNext()){
		
		reader.getNext(cas);
//		System.out.println(cas.getJCas().getDocumentText());
		
		jCases.add(cas.getJCas());
		}
		
		
		return jCases;

	}
}
