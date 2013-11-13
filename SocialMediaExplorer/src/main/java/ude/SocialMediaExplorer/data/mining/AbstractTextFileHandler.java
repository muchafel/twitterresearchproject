package ude.SocialMediaExplorer.data.mining;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * abstract class for reading input as {@link String}
 * @author henrikdetjen
 *
 */
public abstract class AbstractTextFileHandler {
	
	/**
	 * reads a text file at specified path
	 * @param path {@link String} 
	 * @return {@link String} the files content as a string
	 */
	public String readTextFile(String path){
		StringBuffer result = new StringBuffer();
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(path);
			br = new BufferedReader(fr);
			while (br.ready()){
				result.append(br.readLine());
			}
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result.toString();
	}

	/**
	 * 
	 * NOT SUPPORTED IN GOOGLE APP ENGINE
	 * 
	 * writes a given {@link String} to specified location (path)
	 * @param path {@link String}	
	 * @param toWrite a {@link String} representing the files' content
	 */
	public void writeTextFile(String path, String toWrite){
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(new File(path));
			bw = new BufferedWriter(fw);
			bw.write(toWrite);
		} catch (IOException e)	{
			e.printStackTrace();
		}finally{
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
