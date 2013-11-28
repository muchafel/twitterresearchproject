package ude.SocialMediaExplorer.data.utils.io;



import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.jcas.JCas;
import org.junit.rules.TemporaryFolder;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.pipeline.SimplePipeline.*;

import de.tudarmstadt.ukp.dkpro.core.api.io.ResourceCollectionReaderBase;
import de.tudarmstadt.ukp.dkpro.core.io.bincas.BinaryCasWriter;
import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader;


public class CASWriter {
	

	public void write(JCas jcas) throws Exception {
		
		AggregateBuilder builder = new AggregateBuilder();
		builder.add(createEngineDescription(BinaryCasWriter.class, BinaryCasWriter.PARAM_FORMAT, "4",
		BinaryCasWriter.PARAM_TARGET_LOCATION, "serializedCases"));
		AnalysisEngine engine = builder.createAggregate();
		engine.process(jcas);
	}
}
