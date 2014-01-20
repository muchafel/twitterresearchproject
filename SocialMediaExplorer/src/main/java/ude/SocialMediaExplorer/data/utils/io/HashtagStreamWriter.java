package ude.SocialMediaExplorer.data.utils.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import ude.SocialMediaExplorer.Config;
import ude.SocialMediaExplorer.data.utils.time.TimeStamp;

/**
 * Writes a Stream to a file
 * 
 * @author michealwojatzki, henrikdetjen
 * 
 */
public class HashtagStreamWriter {

	/**
	 * the hashtag on which the stream depends
	 */
	private String	hashtag;
	/**
	 * 
	 */
	private File	file;
	/**
	 * where to put the files
	 */
	private String	path;
	/**
	 * interval after that a new json-file will be created, where the stream is
	 * written in
	 * in milliseconds
	 */
	private long	interval;		// in ms
	/**
	 * the next timeout for new file, s.a.
	 */
	private Date	nextTimeout;

	/**
	 * Constructs a Stream Writer that writes a json file to disk
	 * 
	 * @param hashtag
	 *            {@link String} for filename
	 * @param interval
	 *            in ms - afer this time a new file with timestamp will be
	 *            created
	 */
	public HashtagStreamWriter( String hashtag ) {
		this.interval = Config.get_crawler_newFileInterval();
		// save date of first call and increment it later (after timeout)
		nextTimeout = new Date( new Date().getTime() + interval );
		// normalize hashtag
		this.hashtag = hashtag.replaceAll( "#", "" );
		// set file location
		this.path = Config.get_location_tweets() + this.hashtag;

		// make folder for hashtag (if not already existing)
		try {
			File dir = new File( path );
			dir.mkdirs();
		}
		catch ( Exception e ) {
			System.out.println( e.getMessage() );
		}

		// create File (hashtag_timestamp.json)
		file = new File( path + "/" + TimeStamp.getLong() + "_" + this.hashtag + ".json" );
		try {
			file.createNewFile();
			System.out.println( "new Stream to File" + file.getAbsolutePath() );
		}
		catch ( IOException e ) {
			System.out.println( e.getMessage() );
		}

	}

	public void write( String a ) {

		try {
			checkTimeout(); // new file?

			OutputStreamWriter writer = new OutputStreamWriter( new FileOutputStream( file, true ), "UTF-8" );
			writer.write( a.toString() );
			writer.write( System.getProperty( "line.separator" ) );
			writer.flush();
			writer.close();

		}
		catch ( IOException e ) {
			e.printStackTrace();
		}
	}

	private void checkTimeout() {

		Date now = new Date();
		if ( now.after( nextTimeout ) ) {

			// prevent: double tick, but no stream -> 2 files..
			while ( nextTimeout.before( now ) ) {
				nextTimeout = new Date( ( nextTimeout.getTime() + interval ) );
			}

			// timeout -> change file
			file = new File( path + "/" + TimeStamp.createLong( new Date( nextTimeout.getTime() - interval ) ) + "_"
					+ this.hashtag + ".json" );

			try {
				file.createNewFile();
			}
			catch ( IOException e ) {
				e.printStackTrace();
			}

		}

	}

	public String getName() {
		return this.hashtag;
	}

}
