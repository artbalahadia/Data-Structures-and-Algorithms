/**
 *
 * @author Tony
 */
public class BasicTextManipulations
{
    public String reverse(String in)
    {
    	/* reverses string using a for loop with "i" starting at the last char of string "in"
    	 * then adds that char at a new string "reversed"
    	 */
    	String reversed = "";
    	for(int i = in.length()-1; i >= 0; i--) {
    		reversed += in.charAt(i);
    	}
    	return reversed;
    }
    
    public int count(String within, char find)
    {
    	// increments "count" when instances of char "find" is in String "within"
    	int count = 0;
    	for(int i = 0; i < within.length(); i++) {
    		if(find == within.charAt(i)) {
    			count++;
    		}
    	}
        return count;
    }
    
    public String onlyAlpha(String within)
    {
    	// uses "replaceAll" to remove all non-alphanumeric chars (except space)
    	return within.replaceAll("[^A-Za-z ]", "");
    	
    }
    
    public int count(String within, String find)
    {
    	/* uses "indexOff" to find occurrences of string "find" in string "within"
    	 * returns -1 if none are present 
    	 */
    	int index = 0, count = 0;
    	while(index != -1) {
    		index = within.indexOf(find, index);
    		if(index != -1) {
    			count++;
    			index += find.length();
    		}
    	}
        return count;
    }

    public String markDoubles(String within)
    {
    	/* traverses through chars in string "within" and parses "2" if charAt(i) == charAt(i+1)
    	 * if i is at end of string length, break loop and return newString
    	 */
    	String newString = "";
    	for(int i = 0; i < within.length(); i++) {
    		newString += within.charAt(i);
    		if(i == within.length()-1) {
    			break;
    		}
    		if(within.charAt(i) == within.charAt(i+1)) {
    			newString += '2';
    		}
    	}
    	return newString;

    }
    
    public boolean isPalindrome(String within)
    {
    	// reverses the string "within" and compares it to its reversed string
    	String reversed = "";
    	for(int i = within.length()-1; i >= 0; i--) {
    		reversed += within.charAt(i);
    	}
    	if(within.equals(reversed)) {
    		return true;
    	}
    	
        return false;
    }

    public String swapCase(String within)
    {
    	// creates a new string "convertedString" that is used to parse converted cases from string "within"
    	String convertedString = "";
    	for(int i = 0; i < within.length(); i++) {
    		if(Character.isUpperCase(within.charAt(i))) {
    			convertedString += Character.toLowerCase(within.charAt(i));
    		} else {
    			convertedString += Character.toUpperCase(within.charAt(i));
    		}
    	}
        return convertedString;
    }

    public int countNs(String within)
    {
    	// counts the instances of char "n", while ignoring escape (\) key
    	int count = 0;
    	for(int i = 0; i < within.length(); i++) {
    		if(within.charAt(i) == 'n') {
    			count++;
    		}
    	}
    	
        return count;
    }
    
    ////////////////////////////////////
    private static void checkEquals(Object check, Object expected, String input, String message)
    {
        if (check.equals(expected))
        {
        } else
        {
            System.out.print(message);
            System.out.println(" got " + check + " expected " + expected + " for input " + input);
        }
    }

    public static void main(String[] args)
    {
        BasicTextManipulations b = new BasicTextManipulations();
        
        String testError = "reverse() failed: ";
        checkEquals(b.reverse("Tony Lowe"), "ewoL ynoT", "Tony Lowe", testError);
        checkEquals(b.reverse("racecar"), "racecar", "racecar", testError);
        checkEquals(b.reverse(""), "", "\"\"", testError);
        System.out.println("reverse() finished");        

        testError = "count(char) failed: ";
        checkEquals(b.count("Tony Lowe", 'o'), 2, "\"Tony Lowe\", 'o'", testError);
        checkEquals(b.count("Tony Lowe", ' '), 1, "\"Tony Lowe\", ' '", testError);
        checkEquals(b.count("Tony Lowe", 'z'), 0, "\"Tony Lowe\", 'z'", testError);
        checkEquals(b.count("racecar", 'r'), 2, "\"racecar\", 'r'", testError);
        checkEquals(b.count("", ' '), 0, "\"\", ' '", testError);
        System.out.println("count(char) finished");        

        testError = "onlyAlpha() failed: ";
        checkEquals(b.onlyAlpha("Tony Lowe"), "Tony Lowe", "Tony Lowe", testError);
        checkEquals(b.onlyAlpha("I’m excited!"), "Im excited", "I’m excited!", testError);
        checkEquals(b.onlyAlpha("&*(&#$*"), "", "&*(&#$*", testError);
        checkEquals(b.onlyAlpha("2332 3443"), " ", "2332 3443", testError);
        checkEquals(b.onlyAlpha(""), "", "\"\"", testError);
        System.out.println("onlyAlpha() finished");        

        testError = "count(String) failed: ";
        checkEquals(b.count("Tony Lowe", "o"), 2, "\"Tony Lowe\", \"o\"", testError);
        checkEquals(b.count("Tony Lowe", "Tony"), 1, "\"Tony Lowe\", \"Tony\"", testError);
        checkEquals(b.count("Mississippi", "ss"), 2, "\"Mississippi\", \"ss\"", testError);
        checkEquals(b.count("", " "), 0, "\"\"", testError);
        System.out.println("count(String) finished");        

        testError = "markDoubles() failed: ";
        checkEquals(b.markDoubles("Mississippi"), "Mis2sis2sip2pi", "\"Mississippi\"", testError);
        checkEquals(b.markDoubles("aaaa"), "a2a2a2a", "\"aaaa\"", testError);
        checkEquals(b.markDoubles(""), "", "\"\", \" \"", testError);
        System.out.println("markDoubles() finished");        

        testError = "isPalindrome() failed: ";
        checkEquals(b.isPalindrome("racecar"), true, "\"racecar\"", testError);
        checkEquals(b.isPalindrome("Madam"), false, "\"Madam\"", testError);
        checkEquals(b.isPalindrome("aaaa"), true, "\"aaaa\"", testError);
        checkEquals(b.isPalindrome(""), true, "\"\", \" \"", testError);
        System.out.println("isPalindrome() finished");        

        testError = "swapCase() failed: ";
        checkEquals(b.swapCase("Tony Lowe"), "tONY lOWE", "\"Tony Lowe\"", testError);
        checkEquals(b.swapCase("!@#$%"), "!@#$%", "\"!@#$%\"", testError);
        checkEquals(b.swapCase("aaaa"), "AAAA", "\"aaaa\"", testError);
        checkEquals(b.swapCase(""), "", "\"\", \" \"", testError);
        System.out.println("swapCase() finished");        

        testError = "countNs() failed: ";
        checkEquals(b.countNs("Tony Lowe"), 1, "\"Tony Lowe\"", testError);
        checkEquals(b.countNs("n\nn"), 2, "\"Tony Lowe\"", testError);
        checkEquals(b.countNs("nn\nnn\n"), 4, "\"Tony Lowe\"", testError);
        checkEquals(b.countNs(""), 0, "\"\", \" \"", testError);
        System.out.println("countNs() finished");        
    }
}