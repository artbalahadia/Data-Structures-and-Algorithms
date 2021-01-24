/** Data Structures II
 * 
 * Art Balahadia
 * 
 * Get help from anyone?  put that here
 * 
 * Homework 1 Tester   Version 1.0
 * 
 * Instructions:  
 *      There are two java files associated with this homework.
 *      LLST.java is a java class you will use to implement a portion of the 
 *         Ordered Symbol Table API from section 3.1 of the text.
 *         See the ToDo items in that file.   
 *      
 *      This file is a driver program to test your LLST methods implementation.
 *      I have provided some testing code to allow you to test the size method.
 *      
 *      You will need to create additional functions to:
 *         test the other member functions in your LLST implementation.
 *       AND
 *         create a reasonable set of test cases for each; 
 *                
 *      call your testing functions from main
 *      You can follow the approach suggested by   sizeTest    or use your own approach.
 *      If you use your own approach, you must include enough documentation so that the
 *      grader is convinced that it is correctly testing the instance method.
 *                
 */
		/*
		 * CONSOL OUTPUT
		 * 
		 * 	sizeTest: Correct  String  Answer: 0
			sizeTest: Correct  String abcde Answer: 5
			sizeTest: Correct  String aaabbbccc Answer: 3
			sizeTest: Correct  String aAbBcC Answer: 6
			secondSmallestTest: Correct  String  Answer: null
			secondSmallestTest: Correct  String abcd Answer: b
			secondSmallestTest: Correct  String jahsg Answer: g
			secondSmallestTest: Correct  String f Answer: null
			rankTest: Correct  String  Answer: -1
			rankTest: Correct  String abcd Answer: 2
			rankTest: Correct  String oisqn Answer: 1
			rankTest: Correct  String ymlzxt Answer: 3
			ceilingTest: Correct  String abcd Answer: c
			ceilingTest: Correct  String xfrs Answer: r
			ceilingTest: Correct  String nkevu Answer: n
			ceilingTest: Correct  String ppqqii Answer: i
			*** Original Symbol Table <Key, Value> ***
			(e, 4)
			(d, 3)
			(c, 2)
			(b, 1)
			(a, 0)
			-------------------------------------------
			*** Inverse Symbol Table <Value, Key> ***
			(4, e)
			(3, d)
			(2, c)
			(1, b)
			(0, a)
		 */



package homework;

import stdlib.StdIn;
import stdlib.StdOut;

public class hw1Tester {

		public static void main(String[] args) {

//			// the simple test client code from the textbook pg 370
//			// you may delete/comment this out if you wish
//			LLST<String, Integer> st = new LLST<>();
//			StdIn.fromFile("data/tinyST.txt");
//			for (int i = 0; !StdIn.isEmpty(); i++)
//			{
//				String key = StdIn.readString();
//				st.put(key, i);
//			}
//			for (String s : st.keys())
//				StdOut.println(s + " " + st.get(s));
			
		
			
			// To do:   call your testing modules
			
			allSizeTests();
			allSecondSmallestTests();
			allRankTests();
			allCeilingTests();
			allInverseTest();
		}

		/* performs all tests of the size method
		 * by calling   sizeTest   for a specific test case
		 */
		public static void allSizeTests() {
			sizeTest("",0);				// test size on an empty ST (symbol table)
			sizeTest("abcde",5);		// test size on a non-empty ST
			sizeTest("aaabbbccc",3);	// test size on duplicate keys
			sizeTest("aAbBcC", 6);		// test size on upper/lower case (should ignore duplicates)
										
			// ToDo   add at least 2 more test cases here
		    // label each test case with a comment describing what you are testing for.
			
		}
		// sample testing function.
		// param vals: data source for the keys for the symbol table
		//             each substring of length 1 is used as a key
		//             the value for each key is its position with the vals string
		// param expected:  the correct size value of the ST for the input:vals
		public static void sizeTest( String vals, int expected ) {
			
			// create and populate the table from the input string vals
			LLST<String,Integer> aList = new LLST<String,Integer>();
			for (int i=0; i < vals.length(); i++) {
				aList.put(vals.substring(i, i+1),i);
			}
			//  call the size function
			int result = aList.size();
			//report result
			if ( result == expected)  // test passes
				StdOut.format("sizeTest: Correct  String %s Answer: %d\n", vals,result);
			else
				StdOut.format("sizeTest: *Error*  String %s Expected: %d function result: %d\n", vals,expected,result);
		}
		
		//Todo: add your testing modules and functions here
		//See note about testing inverse function
		
		
		public static void allSecondSmallestTests() {
			secondSmallestTest("",null);		// test secondSmallest on a sorted string
			secondSmallestTest("abcd","b");		// test secondSmallest on a sorted string
			secondSmallestTest("jahsg","g");	// test secondSmallest on an unsorted string
			secondSmallestTest("f",null);		// test secondSmallest on a one key string

			
		}
		
		public static void secondSmallestTest( String vals, String expected ) {
			
			// create and populate the table from the input string vals
			LLST<String,Integer> aList = new LLST<String,Integer>();
			for (int i=0; i < vals.length(); i++) {
				aList.put(vals.substring(i, i+1),i);
			}
			//  call the secondSmallest function
			String result = aList.secondSmallestKey();
			//report result
			if ( result == expected || result.equals(expected))  // test passes (checks for string/null results)
				StdOut.format("secondSmallestTest: Correct  String %s Answer: %s\n", vals,result);
			else
				StdOut.format("secondSmallestTest: *Error*  String %s Expected: %s function result: %s\n", vals,expected,result);
		}
		
		public static void allRankTests() {
			rankTest("", "", -1);				// test rankTest on an empty string	
			rankTest("abcd","c",2);				// test rankTest on a sorted string
			rankTest("oisqn", "n",1);			// test rankTest on an unsorted string
			rankTest("ymlzxt","u",3);			// test rankTest on an unsorted string, with key not in string

			
		}
		
		public static void rankTest( String vals, String key, int expected ) {
			
			// create and populate the table from the input string vals
			LLST<String,Integer> aList = new LLST<String,Integer>();
			for (int i=0; i < vals.length(); i++) {
				aList.put(vals.substring(i, i+1),i);
			}
			//  call the rank function
			int result = aList.rank(key);
			//report result
			if ( result == expected)  // test passes 
				StdOut.format("rankTest: Correct  String %s Answer: %d\n", vals,result);
			else
				StdOut.format("rankTest: *Error*  String %s Expected: %d function result: %d\n", vals,expected,result);
		}
		
		
		public static void allCeilingTests() {
			ceilingTest("abcd","c","c");		// test ceilingTest on an ordered string
			ceilingTest("xfrs","r","r");		// test ceilingTest on an unoderdered string
			ceilingTest("nkevu","l","n");		// test ceilingTest with a key not on the string
			ceilingTest("ppqqii","i","i");		// test ceilingTest on first key in string (least value key)
		}
		
		public static void ceilingTest( String vals, String key, String expected ) {
			
			// create and populate the table from the input string vals
			LLST<String,Integer> aList = new LLST<String,Integer>();
			for (int i=0; i < vals.length(); i++) {
				aList.put(vals.substring(i, i+1),i);
			}
			//  call the secondSmallest function
			String result = aList.ceiling(key);
			//report result
			if ( result.equals(expected))  // test passes (checks for string/null results)
				StdOut.format("ceilingTest: Correct  String %s Answer: %s\n", vals,result);
			else
				StdOut.format("ceilingTest: *Error*  String %s Expected: %s function result: %s\n", vals,expected,result);
		}
		
		
		public static void allInverseTest() {
			inverseTest("abcde");		// test inverseTest on an ordered string
		}
		
		public static void inverseTest( String vals) {
			
			// create and populate the table from the input string vals
			LLST<String,Integer> aList = new LLST<String,Integer>();
			for (int i=0; i < vals.length(); i++) {
				aList.put(vals.substring(i, i+1),i);
			}
			
			aList.inverse();	// calls method to print the inversed symbol table
			
		}
		
		
		
		
		
}
