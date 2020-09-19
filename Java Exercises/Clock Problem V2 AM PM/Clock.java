/*
 * This program mimics the first exercise "Clock Problem" where we hold
 * a time value but with a twist - now based on 12 hour clock.
 * The value displays hours, minutes, seconds including the period (AM and PM).
 * Range of values are from 00:00:00 (midnight) to 12:00:00 (noon).
 */



public class Clock {
	
	private int hours;
	private int minutes;
	private int seconds;
	private String period; // AM or PM
	
	// Constructor for clock set at beginning (00:00:00)
	public Clock() {
		this.hours = 0;
		this.minutes = 0;
		this.seconds = 0;
		this.period = "AM"; // set at midnight
	}
	
	// Constructor for new clock with given set values
	public Clock(int hours, int minutes, int seconds, String period) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		this.period = period;
	}
	
	/*
	 *  Adds one second to current time. If seconds hit 60, it resets to 0 and
	 *  adds 1 minute. If minutes hit 60, it resets to 0 and adds 1 hour. Hours go up to 12:00:00,
	 *  switches the period of day after 12:00:00 (AM/PM) then rolls to 01:00:00 after 12:59:59.
	 */
	public void addOneSecond() {
		if(this.seconds >= 0 && this.seconds < 60 && this.minutes >= 0 && this.minutes < 60 && this.hours >= 0 && this.hours <= 12) {
			this.seconds++;
			if(this.seconds > 59) {
				minutes++;
				this.seconds = 0;
				if(this.minutes > 59) {
					hours++;
					this.minutes = 0;
					if(this.hours == 12) {
						if(period.equals("AM")) {
							this.period = "PM";
						} else {
							this.period = "AM";
						}
					} else if(this.hours > 12) {
						this.hours = 1;
					}
				}
			}
		}
	}
	
	/*
	 * Compares to another clock object in the same day. Returns -1 if this Clock is before otherClock.
	 * Returns 0 if both are at the same time and returns 1 if this Clock is after before otherClock.
	 */
	public int compareTo(Clock otherClock) {
		/*
		 *  using this to convert this Clock's and otherClock's period to int and compare. 
		 *  AM = 0 and PM = 1
		 */
		int thisClockPeriod = (this.period.equals("AM")) ? 0 : 1;
		int otherClockPeriod = (otherClock.period.equals("AM")) ? 0 : 1;

		// Checks Period -> Hours -> Minutes -> Seconds
		if(thisClockPeriod == otherClockPeriod) {
			if(this.hours == otherClock.hours) {
				if(this.minutes == otherClock.minutes) {
					if(this.seconds == otherClock.seconds) {
						return 0;
					} else if(this.seconds < otherClock.seconds) {
						return -1;
					} else {
						return 1;
					}
				} else if(this.minutes < otherClock.minutes) {
					return -1;
				} else {
					return 1;
				}
			} else if(this.hours < otherClock.hours) {
				return -1;
			} else {
				return 1;
			}
		} else if(thisClockPeriod < otherClockPeriod) {
			return -1;
		} else {
			return 1;
		}

	}
	
	/*
	 *  Adds time to this Clock. Rolls over and switches period if surpasses 12.
	 *  Note that timeToAdd clock will always be AM as it will not be used in the addition process.
	 */
	public void add(Clock timeToAdd) {
		this.seconds += timeToAdd.seconds;
		if(this.seconds > 59) {
			minutes++;
			this.seconds -= 60;
		}
		
		this.minutes += timeToAdd.minutes;
		if(this.minutes > 59) {
			hours++;
			this.minutes -= 60;
		}
		
		this.hours += timeToAdd.hours;
		if(this.hours > 12) {
			this.hours -= 12;
			this.period = (this.period.equals("AM")) ? "PM" : "AM";
		}
		
	}

	/*
	 *  Subtracts time to this Clock. Rolls over/under and switches if surpasses 12.
	 *  Note that timeToSubtract clock will always be AM as it will not be used in the addition process.
	 */
	public void subtract(Clock timeToSubtract) {
		this.seconds -= timeToSubtract.seconds;
		if(this.seconds < 0) {
			minutes--;
			this.seconds += 60;
		}
		
		this.minutes -= timeToSubtract.minutes;
		if(this.minutes < 0) {
			hours--;
			this.minutes += 60;
		}
		
		this.hours -= timeToSubtract.hours;
		if(this.hours < 0) {
			this.hours+= 12;
			this.period = (this.period.equals("AM")) ? "PM" : "AM";
		}
	}
	
	
	public String toString() {
		return pad(hours) + ":" + pad(minutes) + ":" + pad(seconds) + " " + this.period;
	}
	
	
	private String pad(int value) {
		String sign = "";
		if(value < 0) {
			sign = "-";
			value = -value;
		}
		
		if(value < 10) {
			return sign + "0" + value;
		} else {
			return sign + value;
		}
	}
	
	
}



