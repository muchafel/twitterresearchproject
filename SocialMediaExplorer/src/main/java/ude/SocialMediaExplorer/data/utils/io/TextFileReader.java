package ude.SocialMediaExplorer.data.utils.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/**
 * simple file reader 
 * @author henrikdetjen
 *
 */
public class TextFileReader {
	/**
	 * reads a text file at specified path
	 * @param path {@link String} 
	 * @return {@link StringBuffer} the files content as a string (line represenation) 
	 */
	public static ArrayList<String> read(String path){
		ArrayList<String> result = new ArrayList<String>();
		BufferedReader br = null;
		try {
			java.io.FileReader fr = new java.io.FileReader(path);
			br = new BufferedReader(fr);
			while (br.ready()){
				result.add(br.readLine());
			}
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * reads a text file
	 * @param file {@link File} 
	 * @return {@link StringBuffer} the files content as a string (line represenation) 
	 */
	public static ArrayList<String> read(File f){
		ArrayList<String> result = new ArrayList<String>();
		BufferedReader br = null;
		try {
			java.io.FileReader fr = new java.io.FileReader(f);
			br = new BufferedReader(fr);
			while (br.ready()){
				result.add(br.readLine());
			}
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
