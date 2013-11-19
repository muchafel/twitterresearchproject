package ude.SocialMediaExplorer.data.utils.time;

import java.util.Date;

import com.ibm.icu.text.SimpleDateFormat;

/**
 * helper for creation of timestamps
 * @author henrikdetjen
 *
 */
public class TimeStamp {
	/**
	 * get a Timestamp in format YYYYMMdd
	 * @return {@link String}
	 */
	public static String getShort(){
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyLocalizedPattern("YYYYMMdd");
		return format.format(new Date());
	}
	/**
	 * creates a Timestamp in format YYYYMMdd
	 * @param d {@link Date}
	 * @return {@link String}
	 */
	public static String createShort(Date d){
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyLocalizedPattern("YYYYMMdd");
		return format.format(d);
	}
	/**
	 * from ts to date
	 * @param s {@link String}
	 * @return {@link Date}
	 */
	public static Date reverseShort(String s) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyLocalizedPattern("YYYYMMdd");
		return format.parse(s);
	}
	/**
	 * get a Timestamp in format YYYYMMddHHmmss
	 * @return {@link String}
	 */
	public static String getLong(){
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyLocalizedPattern("YYYYMMddHHmmss");
		return format.format(new Date());
	}
	/**
	 * creates a Timestamp in format YYYYMMddHHmmss 
	 * @param d {@link Date}
	 * @return {@link String}
	 */
	public static String createLong(Date d){
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyLocalizedPattern("YYYYMMddHHmmss");
		return format.format(d);
	}
	/**
	 * from ts to date
	 * @param s {@link String}
	 * @return {@link Date}
	 */
	public static Date reverseLong(String s) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyLocalizedPattern("YYYYMMddHHmmss");
		return format.parse(s);
	}
}

