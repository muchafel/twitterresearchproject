package ude.SocialMediaExplorer.data.utils.time;

import java.util.Date;

/**
 * helper class to represent an time interval
 * @author henrikdetjen
 *
 */
public class TimeSpan {
	
	private Date start;
	private Date end;
	
	/**
	 * Constructs a TimeSpan from a past/future date FROM NOW ON
	 * @param d {@link Date} past/future date
	 */
	public TimeSpan(Date d) {
		Date now = new Date();
		
		if ( d.before(now) ){
			this.start = d;
			this.end = now;
		}else{
			this.start = now;
			this.end = d;			
		}
		
	}
	/**
	 * Constructor
	 * @param start {@link Date} 
	 * @param end {@link Date}
	 */
	public TimeSpan(Date start, Date end) {
		this.start = start;
		this.end = end;
	}
	/**
	 * if a date is included in this timespan
	 * @param d {@link Date}
	 * @return {@link Boolean}
	 */
	public boolean includes(Date d){
		if( d.after(start) && d.before(end) ){
			return true;
		}
		return false;
	}
	
}
