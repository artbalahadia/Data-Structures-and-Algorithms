/**
 * Objects of the Time class hold a time value for a 
 * European-style 24 hour clock. 
 * The value consists of hours, minutes and seconds. 
 * The range of the value is 00:00:00 (midnight) to 23:59:59 (one 
 * second before midnight).
 */
public class Time {

    private int hours;
    private int minutes;
    private int seconds;


    /** 
     * Add one second to the current time. 
     * When the seconds value reaches 60, it rolls over to zero. 
     * When the seconds roll over to zero, the minutes advance. 
     * So 00:00:59 rolls over to 00:01:00. 
     * When the minutes reach 60, they roll over and the hours advance. 
     * So 00:59:59 rolls over to 01:00:00. 
     * When the hours reach 24, they roll over to zero. 
     * So 23:59:59 rolls over to 00:00:00. 
     */    
    public void advanceOneSecond() 
    {
        if(this.seconds >= 0 && this.seconds < 60 && this.minutes >= 0 && this.minutes < 60 && this.hours >= 0 && this.hours < 24) {     	
        	this.seconds++;
        	if(this.seconds > 59) {
        		this.minutes++;
        		this.seconds = 0;
        		if(this.minutes > 59) {
        			this.hours++;
        			this.minutes = 0;
        			if(this.hours > 23) {
        				this.hours = 0;
        			}
        		}
        	}
        }
    }

    /** 
     * Compare this time to otherTime. 
     * Assumes that both times are in the same day. 
     * Returns -1 if this Time is before otherTime. 
     * Returns 0 if this Time is the same as otherTime. 
     * Returns 1 if this Time is after otherTime. 
     */
    public int compareTo(Time otherTime) 
    {
    	if(this.seconds == otherTime.seconds && this.minutes == otherTime.minutes && this.hours == otherTime.hours) {
    		return 0;
    	}
    	
    	if(this.seconds <= otherTime.seconds) {
    		if(this.minutes <= otherTime.minutes) {
    			if(this.hours <= otherTime.hours) {
    				return -1;
    			} else {
    				return 1;
    			}
    		} else {
    			return 1;
    		}
    	} else {
    		return 1;
    	}
        

    }

    /** 
     * Add an offset to this Time. 
     * Rolls over the hours, minutes and seconds fields when needed. 
     */
    public void add(Time offset) 
    {
    	this.seconds += offset.seconds;
    	if(this.seconds > 59) {
    		minutes++;
    		this.seconds -= 60;
    	}
    	
    	this.minutes += offset.minutes;
    	if(this.minutes > 59) {
    		hours++;
    		this.minutes -= 60;

    	}
    	
    	this.hours += offset.hours;
    	if(this.hours > 23) {
    		this.hours -= 24;
    	}
    	
    }

    /** 
     * Subtract an offset from this Time. 
     * Rolls over (under?) the hours, minutes and seconds fields when needed. 
     */
    public void subtract(Time offset) 
    {
    	this.seconds -= offset.seconds;
    	if(this.seconds < 0) {
    		minutes--;
    		this.seconds += 60;
    	}
    	
    	this.minutes -= offset.minutes;
    	if(this.minutes < 0) {
    		hours--;
    		this.minutes += 60;
    	}
    	
    	this.hours -= offset.hours;
    	if(this.hours < 0) {
    		this.hours += 24;
    	}
    }

    /** 
     * Constructor for objects of class Time. 
     * Creates a new Time object set to 00:00:00. 
     * Do not change this constructor. 
     */
    public Time() {
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
    }


    /**
     * Creates a new Time object set to h:m:s. 
     * Assumes, without checking, that the parameter values are 
     * within bounds. * For this task, you don't need to worry about invalid parameter values. 
     * Do not change this constructor. 
    */
    public Time(int h, int m, int s) {
        this.hours = h;
        this.minutes = m;
        this.seconds = s;
    }

    /** 
     * Return a string representation of this Time. 
     * String is of the form hh:mm:ss with always two digits for h, m and s. 
     * Do not change this.
     */
    public String toString() {
        return pad(hours) + ":" + pad(minutes) + ":" + pad(seconds);
    }

    /** 
     * Returns a string representing the argument value, adding a leading 
     * "0" if needed to make it at least two digits long. 
     * Do not change this. 
     */
    private String pad(int value) {
        String sign = "";
        if (value < 0) {
            sign = "-";
            value = -value;
        }

        if (value < 10) {
            return sign + "0" + value;
        } else {
            return sign + value;
        }
    }
}