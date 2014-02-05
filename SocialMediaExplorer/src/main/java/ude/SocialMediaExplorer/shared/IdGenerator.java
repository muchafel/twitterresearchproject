package ude.SocialMediaExplorer.shared;

/**
 * simple id generator
 * 
 * @author henrikdetjen
 * 
 */
public class IdGenerator {

	private static int id = 0;

	public static int getId() {
		if ( id >= Integer.MAX_VALUE) {
			id = 0;
		}
		return id++;
	}
	
}
