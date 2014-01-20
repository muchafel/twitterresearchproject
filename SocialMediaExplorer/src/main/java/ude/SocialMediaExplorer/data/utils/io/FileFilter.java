package ude.SocialMediaExplorer.data.utils.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import ude.SocialMediaExplorer.data.utils.time.TimeSpan;
import ude.SocialMediaExplorer.data.utils.time.TimeStamp;

/**
 * A class for sorting and filtering files
 * 
 * @author henrikdetjen
 * 
 */
public class FileFilter {

	/**
	 * returns all files for a directory, which are in given period
	 * files must be in format
	 * timestamp_name.bla -> {@TimeStamp}
	 * 
	 * @param dir
	 *            {@File} the directory in which should be searched
	 * @param ts
	 *            the {@link TimeSpan} to lookup
	 * @return {@link File[]}
	 */
	public static File[] getFilesByTimePeriod( File dir, TimeSpan ts ) throws Exception {
		ArrayList <File> result = new ArrayList <File>();
		if ( dir.exists() && dir.isDirectory() ) {
			File[] files = dir.listFiles();
			for ( File file : files ) {
				if ( file.isFile() && file.getName().contains( "_" ) ) {
					// check time
					String timestamp = file.getName().split( "_" )[0];
					Date d;
					try {
						d = TimeStamp.reverseLong( timestamp );
					}
					catch ( Exception e ) {
						d = TimeStamp.reverseLong( timestamp );
					}
					// if in timespan
					if ( ts.includes( d ) ) {
						result.add( file );
					}
				}
			}
		}
		return result.toArray( new File[result.size()] );
	}

	public static File[] getFoldersByTimePeriod( File dir, TimeSpan ts ) throws Exception {
		ArrayList <File> result = new ArrayList <File>();
		if ( dir.exists() && dir.isDirectory() ) {
			File[] files = dir.listFiles();
			for ( File file : files ) {
				if ( file.isDirectory() && file.getName().contains( "_" ) ) {
					// check time
					String timestamp = file.getName().split( "_" )[0];
					Date d;
					try {
						d = TimeStamp.reverseLong( timestamp );
					}
					catch ( Exception e ) {
						d = TimeStamp.reverseLong( timestamp );
					}
					// if in timespan
					if ( ts.includes( d ) ) {
						result.add( file );
					}
				}
			}
		}
		return result.toArray( new File[result.size()] );
	}

}
