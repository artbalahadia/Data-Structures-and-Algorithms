package homework;

import java.util.Date;

public class GPStime {
	
	private final double longitude;
	private final double latitude;
	private Date date;
	
	public GPStime(double longitude, double latitude, Date date) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.date = date;
	}
	
	/*
	 * The hashCode function will follow the "31x+y" rule for each field in the class (long, lat, date).
	 * By utilizing all keys to compute a hashCode, it is likely to have good performance
	 */
	public int hashCode() {
		int hash = 17;
		hash = 31 * hash + ((Double)longitude).hashCode();		// use wrapper class for primitive type double
		hash = 31 * hash + ((Double)latitude).hashCode();		// use wrapper class for primitive type double
		hash = 31 * hash + date.hashCode();						
		return hash;
	}
	
	public static void main(String[] args) {
		// Test cases
		GPStime art = new GPStime(41.875, -87.624, new Date());
		int artHashCode = art.hashCode();
		System.out.println(artHashCode);
		
		GPStime cza = new GPStime(14.590, 120.980, new Date());
		int czaHashCode = cza.hashCode();
		System.out.println(czaHashCode);

	}
}
