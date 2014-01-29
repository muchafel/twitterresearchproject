package ude.SocialMediaExplorer.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import ude.SocialMediaExplorer.shared.exchangeFormat.ClusterElement;

public class TestCE {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClusterElement readObject =getClusters( "tatort" );
		printCluster(readObject);

	}
	public static ClusterElement getClusters( String hashtag ) {
		ClusterElement readObject = null;
		FileInputStream fi = null;
		ObjectInputStream is = null;
		// Versuchen die Streams zu initialisieren
		try {
			fi = new FileInputStream("files/serializedClusterElements/tatort/tatort.ser");
			is = new ObjectInputStream(fi);
			readObject = (ClusterElement) is.readObject(); // Read Object
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// im finally block alle Streams schließen
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fi != null) {
				try {
					fi.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return readObject;
	}
	private static void printCluster(ClusterElement clusterElement) {
		System.out.println("Cluster: "+clusterElement.getName()+" Sentiment (+): "+clusterElement.getSentiment().getPositive() +"  Sentiment (-): "+clusterElement.getSentiment().getNegative());
		if (!clusterElement.getSubcluster().isEmpty()) {
			for (ClusterElement c : clusterElement.getSubcluster()) {
				printCluster(c);
			}
		}
	}
}
