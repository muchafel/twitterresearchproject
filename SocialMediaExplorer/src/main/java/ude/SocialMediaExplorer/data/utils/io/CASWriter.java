package ude.SocialMediaExplorer.data.utils.io;



import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.jcas.JCas;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import de.tudarmstadt.ukp.dkpro.core.io.bincas.BinaryCasWriter;


public class CASWriter {
	

	public void write(JCas jcas,String hashtag) throws Exception {
		
		String directory="files/serializedCases/"+hashtag;
		
		AggregateBuilder builder = new AggregateBuilder();
		builder.add(createEngineDescription(BinaryCasWriter.class, BinaryCasWriter.PARAM_FORMAT, "4",
		BinaryCasWriter.PARAM_TARGET_LOCATION, directory));
		AnalysisEngine engine = builder.createAggregate();
		engine.process(jcas);
	}
}
