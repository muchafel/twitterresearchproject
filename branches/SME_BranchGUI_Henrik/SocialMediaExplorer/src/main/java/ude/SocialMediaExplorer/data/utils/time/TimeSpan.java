package ude.SocialMediaExplorer.data.utils.time;

import java.util.Date;

/**
 * helper class to represent an time interval
 * 
 * Usage:
 * 
 * for dates:
 * <code>
 * TimeSpan t = new TimeSpan( Date1, (Date2) ); 
 * t.inDays() // returns day difference between 1 and 2
 * 
 * TimeSpan t = new TimeSpan().setDays(3); //=from now till in 3 days
 * Date d = t.getEnd();
 * </code>
 * 
 * for consversion of "normal" intervals
 * <code>
 * long ms = new TimeSpan().setHours(2).inMilliseconds();
 * </code>
 * 
 * @author henrikdetjen
 *
 */
public class TimeSpan {
	
	private Date start;
	private Date end;
	private long interval;
	
	//////////////////////////////////
	
	public TimeSpan(){
		this.start = this.end = new Date();
		this.interval = 0;
	}
	/**
	 * Constructs a timespan in ms FROM NOW ON
	 * @param interval {@link long}
	 */
	public TimeSpan(long interval){
		this.interval = interval;
		this.start = new Date();
		this.end = new Date( (start.getTime() + interval) );
	}
	
	/**
	 * Constructor for a timespan between two specified dates
	 * @param start {@link Date} 
	 * @param end {@link Date}
	 */
	public TimeSpan(Date start, Date end) {
		this.start = start;
		this.end = end;
		this.interval = this.end.getTime() - this.start.getTime();
	}
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
		this.interval = this.end.getTime() - this.start.getTime();
		
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
	
	//////////////////////////////////
	
	public long inMilliseconds(){
		return this.interval;
	}
	public long inSeconds(){
		return (this.interval/1000);
	}
	public long inMinutes(){
		return (this.interval/1000/60);
	}
	public long inHours(){
		return ( this.interval/1000/60/60 );
	}
	public long inDays(){
		return ( this.interval/1000/60/60/24 );
	}
	
	//////////////////////////////////
	
	public void setMilliseconds(long t){
		this.interval = t;
		this.start = new Date();
		this.end = new Date( (start.getTime() + t) );
	}
	public void setSeconds(long t){
		this.interval = (t*1000);
		this.start = new Date();
		this.end = new Date( (start.getTime() + t) );
	}
	public void setMinutes(long t){
		this.interval = (t*1000*60);
		this.start = new Date();
		this.end = new Date( (start.getTime() + t) );
	}
	public void setHours(long t){
		this.interval = (t*1000*60*60);
		this.start = new Date();
		this.end = new Date( (start.getTime() + t) );
	}
	public void setDays(long t){
		this.interval = (t*1000*60*60*24);
		this.start = new Date();
		this.end = new Date( (start.getTime() + t) );
	}
	
	
	//////////////////////////////////
	/////// GETTER AND SETTER ////////
	//////////////////////////////////
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public long getInterval() {
		return interval;
	}
	public void setInterval(long interval) {
		this.interval = interval;
	}
	
}
