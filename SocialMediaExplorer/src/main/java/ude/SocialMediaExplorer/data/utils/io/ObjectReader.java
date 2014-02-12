package ude.SocialMediaExplorer.data.utils.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Reads serialized objects
 * 
 * @author henrikdetjen
 * 
 */
public class ObjectReader {

	/**
	 * reads a singe object a specified pathname (incl. filename)
	 * @param filepath
	 * @return an {@link Object} - must be casted into right type
	 */
	public static Object readObject(String filepath) {
		
		Object readObject = null;
		
		FileInputStream fi = null;
		ObjectInputStream is = null;

		try {
			
			fi = new FileInputStream(filepath);
			is = new ObjectInputStream(fi);
			readObject = is.readObject(); // Read Object
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
	
}
