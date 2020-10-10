/**
 *
 * @author Tony
 */
public class BasicTextManipulations
{
    public String reverse(String in)
    {    	
    	if(in.isEmpty()) {
    		return in;
    	}
    	return reverse(in.substring(1)) + in.charAt(0);
    }

    public int count(String within, char find)
    {
    	if(within.isEmpty()) {
    		return 0;
    	}
    	
    	int counter = 0;
    	if(within.charAt(0) == find) {
    		counter++;
    	}	
        return counter + count(within.substring(1), find);
        
    }
     
    public String onlyAlpha(String within)
    {
    	if(within.isEmpty()) {
    		return within;
    	}
    	String newString = "";
    	
    	if(Character.isLetter(within.charAt(0)) || Character.isWhitespace(within.charAt(0))) {
    		newString += within.charAt(0);
    	}
    	
    	return newString + onlyAlpha(within.substring(1));	
    }
    
    public int count(String within, String find)
    {
    	int count = 0;
  
    	if(within.isEmpty()) {
    		return 0;
    	}
    	if(within.startsWith(find)) {
    		count++;
    	}
    	return count + count(within.substring(1), find);

    }

    public String markDoubles(String within)
    {
    	if(within.length() <= 1) {
    		return within;
    	}
    	
    	String newString = "";
    	
    	if(within.charAt(0) == within.charAt(1)) {
    		newString += within.charAt(0) + "2";
    	} else {
    		newString += within.charAt(0);
    	}
    	return newString + markDoubles(within.substring(1));

    }
    
    public boolean isPalindrome(String within)
    {
    	if(within.length() <= 1) {
    		return true;
    	}	
    	if(within.charAt(0) == within.charAt(within.length()-1)) {
    		return isPalindrome(within.substring(1, within.length()-1));
    	}
    	return false;
    }

    public int countNs(String within)
    {
    	if(within.isEmpty()) {
    		return 0;
    	}
    	
    	int count = 0;

    	if(within.charAt(0) == '\\') {
    		countNs(within.substring(2));
    	} else if(within.charAt(0) == 'n') {
    		count++;
    	}
    	return count + countNs(within.substring(1));
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

//        testError = "swapCase() failed: ";
//        checkEquals(b.swapCase("Tony Lowe"), "tONY lOWE", "\"Tony Lowe\"", testError);
//        checkEquals(b.swapCase("!@#$%"), "!@#$%", "\"!@#$%\"", testError);
//        checkEquals(b.swapCase("aaaa"), "AAAA", "\"aaaa\"", testError);
//        checkEquals(b.swapCase(""), "", "\"\", \" \"", testError);
//        System.out.println("swapCase() finished");        

        testError = "countNs() failed: ";
        checkEquals(b.countNs("Tony Lowe"), 1, "\"Tony Lowe\"", testError);
        checkEquals(b.countNs("n\nn"), 2, "\"Tony Lowe\"", testError);
        checkEquals(b.countNs("nn\nnn\n"), 4, "\"Tony Lowe\"", testError);
        checkEquals(b.countNs(""), 0, "\"\", \" \"", testError);
        System.out.println("countNs() finished");        
    }
}