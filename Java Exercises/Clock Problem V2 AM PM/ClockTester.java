/*
 *  This program tests all possible scenarios for the Clock class. 
 *  It includes adding seconds, comparing different Clocks, adding and subtracting other Clocks.
 *  Checks are tried upon rolling over/under and are also considering the periods of the day (AM and PM)
 *  Tests are independent for all methods.
 *  
 */


public class ClockTester {

	public static void main(String[] args) {
		

//		Individual Checks		
//		System.out.println("addOneSecond method is " + testAddOneSecond());
//		System.out.println("compareTo method is " + testCompareTo());
//		System.out.println("add method is " + testAdd());
//		System.out.println("subtract method is " + testSubtract());
//		
		if(testAddOneSecond() && testCompareTo() && testAdd() && testSubtract()) {
			System.out.println("All tests passed. Program is working!");
		}

	}
	
	public static boolean testAddOneSecond() {
		boolean result;
		
		// clock1 tests from 00:00:00 AM and checks for seconds
		Clock clock1 = new Clock();
		clock1.addOneSecond();
		result = (clock1.toString().equals("00:00:01 AM")) ? true : false;
		
		// clock2 tests from 00:00:59 PM and checks for minutes
		Clock clock2 = new Clock(0, 0, 59, "PM");
		clock2.addOneSecond();
		result = (clock2.toString().equals("00:01:00 PM")) ? true : false;
		
		// clock3 tests from 00:59:59 AM and checks for hours
		Clock clock3 = new Clock(0, 59, 59, "AM");
		clock3.addOneSecond();
		result = (clock3.toString().equals("01:00:00 AM")) ? true : false;
		
		// clock4 tests from 11:59:59 AM and checks for period change (should be 12:00:00 PM)
		Clock clock4 = new Clock(11, 59, 59, "AM");
		clock4.addOneSecond();
		result = (clock4.toString().equals("12:00:00 PM")) ? true : false;
		
		return result;
	}
	
	public static boolean testCompareTo() {
		boolean result;
		
		// clock11 of 00:00:01 AM vs clock12 of 00:10:42 AM returns -1
		Clock clock11 = new Clock(0, 0, 1, "AM");
		Clock clock12 = new Clock(0, 10, 42, "AM");
		result = (clock11.compareTo(clock12) == -1) ? true : false;
		
		// clock21 of 03:07:53 AM vs clock22 of 01:00:02 PM returns -1
		Clock clock21 = new Clock(3, 7, 53, "AM");
		Clock clock22 = new Clock(1, 0, 2, "PM");
		result = (clock21.compareTo(clock22) == -1) ? true : false;
		
		// clock31 of 06:54:19 AM vs clock32 of 06:54:19 AM returns 0;
		Clock clock31 = new Clock(6, 54, 19, "AM");
		Clock clock32 = new Clock(6, 54, 19, "AM");
		result = (clock31.compareTo(clock32) == 0) ? true : false;
		
		// clock41 of 10:30:01 PM vs clock42 of 07:41:05 AM returns 1;
		Clock clock41 = new Clock(10, 30, 1,"PM");
		Clock clock42 = new Clock(7, 41, 5, "AM");
		result = (clock41.compareTo(clock42) == 1) ? true : false;
		
		// clock51 of 05:23:52 PM vs clock52 of 11:46:00 AM returns 1;
		Clock clock51 = new Clock(5, 23, 52, "PM");
		Clock clock52 = new Clock(11, 46, 0, "AM");
		result = (clock51.compareTo(clock52) == 1) ? true : false;
		
		
		return result;
	}
	
	public static boolean testAdd() {
		// Note that timeToAdd clock will always be AM as it will not be used in the addition process.
		boolean result;
		
		// clock11 of 00:00:00 AM + clock12 of 12:12:12 AM =  12:12:12 AM
		Clock clock11 = new Clock(0, 0, 0, "AM");
		Clock clock12 = new Clock(12, 12, 12, "AM");
		clock11.add(clock12);
		result = (clock11.toString().equals("12:12:12 AM")) ? true : false;
		
		// clock21 of 04:40:59 AM + clock22 of 01:06:22 AM =  05:57:21 AM
		Clock clock21 = new Clock(4, 40, 59, "AM");
		Clock clock22 = new Clock(1, 6, 22, "AM");
		clock21.add(clock22);
		result = (clock21.toString().equals("05:47:21 AM")) ? true : false;
		
		// clock31 of 12:59:01 AM + clock32 of 02:34:03 AM =  03:33:04 PM
		Clock clock31 = new Clock(12, 59, 01, "AM");
		Clock clock32 = new Clock(2, 34, 3, "AM");
		clock31.add(clock32);
		result = (clock31.toString().equals("03:33:04 PM")) ? true : false;		
		
		// clock41 of 10:27:09 PM + clock42 of 06:55:11 AM =  05:22:20 AM
		Clock clock41 = new Clock(10, 27, 9, "PM");
		Clock clock42 = new Clock(6, 55, 11, "AM");
		clock41.add(clock42);
		result = (clock41.toString().equals("05:22:20 AM")) ? true : false;	
		
		
		
		return result;
		
	}
	
	public static boolean testSubtract() {
		// Note that timeToSubtract clock will always be AM as it will not be used in the addition process.
		boolean result;
		
		
		// clock11 of 00:05:00 AM - clock12 of 00:01:52 AM =  00:03:08 AM
		Clock clock11 = new Clock(0, 5, 0, "AM");
		Clock clock12 = new Clock(0, 1, 52, "AM");
		clock11.subtract(clock12);
		result = (clock11.toString().equals("00:03:08 AM")) ? true : false;
		
		// clock21 of 10:20:37 AM - clock22 of 07:44:55 AM =  02:35:42 AM
		Clock clock21 = new Clock(10, 20, 37, "AM");
		Clock clock22 = new Clock(7, 44, 55, "AM");
		clock21.subtract(clock22);
		result = (clock21.toString().equals("02:35:42 AM")) ? true : false;
		
		// clock31 of 09:11:46 PM + clock32 of 04:04:04 AM =  05:07:42 PM
		Clock clock31 = new Clock(9, 11, 46, "PM");
		Clock clock32 = new Clock(4, 4, 4, "AM");
		clock31.subtract(clock32);
		result = (clock31.toString().equals("05:07:42 PM")) ? true : false;		
		
		// clock41 of 03:59:33 PM + clock42 of 09:00:56 AM =  05:57:21 AM
		Clock clock41 = new Clock(3, 59, 33, "PM");
		Clock clock42 = new Clock(9, 0, 56, "AM");
		clock41.subtract(clock42);
		result = (clock41.toString().equals("06:58:37 AM")) ? true : false;	
		
		
		return result;
		
	}
	

}
