package ude.SocialMediaExplorer.data.result;

import static org.apache.uima.fit.factory.TypeSystemDescriptionFactory.createTypeSystemDescription;

import org.apache.uima.cas.CAS;

import ude.SocialMediaExplorer.data.utils.io.CASReader;

public class ResultPoolingImpl implements IResultPooling {

	private CASReader reader = new CASReader();
	private CAS cas;
	
	public void pool() throws Exception {
		// TODO Aus CAS sinnvolle Clusterbilden und als ClusterElement zurückgeben
		
//		cas = reader.read(createTypeSystemDescription());
		
		
	}
	
}
