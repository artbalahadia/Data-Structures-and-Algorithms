/**
 * @author Tony
 */

import java.util.*;

public class CelebrityImpersonator {

    public String generateQuote(String quote, String generatorName)
    {
    	switch(generatorName) {
    		case "Canadian":
    			quote = quote + ", eh?";
    			break;
    		case "Valley Girl":
    			quote = "like " + quote.replaceAll(" ", " like ");
    			break;
    		case "Shatner":
    			quote = quote.replaceAll(" ", "...\n") + "...\n";
    			break;
    		case "Pirate":
    			quote = quote.replaceAll("r", "RRRr");
    			break;
    		case "Zatanna":
    			String[] zatannaWords = quote.split("\\s");
    			String reversed = "";
    			for(String each : zatannaWords) {
    				StringBuilder sb = new StringBuilder(each);
    				sb.reverse();
    				reversed += sb.toString()+" ";
    			}
    			quote = reversed.trim();
    			break;
    		case "Yoda":
    			String[] yodaWords = quote.split("\\s");
    			String flipped = ""; 
    			String temp = "";
    			for(int i = 0; i < yodaWords.length; i+=2) {
    				temp = yodaWords[i];
    				yodaWords[i] = yodaWords[i+1];
    				yodaWords[i+1] = temp;
    			}
    			for(String each : yodaWords) {
    				StringBuilder sb = new StringBuilder(each);
    				flipped += sb.toString() + " ";
    			}
    			quote = flipped.trim();
    			break;
    		case "Scooby Doo":
    			String bark = " woof!";
    			String newWord = "";
    			String[] annoyingDogWords = quote.split("\\s");
    			for(int i = 0; i < annoyingDogWords.length; i++) {
    				for(int j = 0; j <= i; j++) {
    					annoyingDogWords[i] += bark;
    				}
    			}
    			for(String each: annoyingDogWords) {
    				StringBuilder sb = new StringBuilder(each);
    				newWord += sb.toString() + " ";
    			}
    			quote = newWord.trim();
    			break;
    	}
        return quote;
    }

    
    static final String[][] tests = 
        {{"Canadian", "Four score and seven years ago, eh?"},
         {"Valley Girl", "like Four like score like and like seven like years like ago"},
         {"Shatner", "Four...\nscore...\nand...\nseven...\nyears...\nago...\n"},
         {"Pirate", "FouRRRr scoRRRre and seven yeaRRRrs ago"},
         {"Zatanna", "ruoF erocs dna neves sraey oga"},
         {"Yoda", "score Four seven and ago years"},
         // adding test for Scooby Doo :)}
         {"Scooby Doo", "Four woof! score woof! woof! and woof! woof! woof! seven woof! woof! woof! woof! years woof! woof! woof! woof! woof! ago woof! woof! woof! woof! woof! woof!"}};
    
    public static void main(String[] args)
    {
    	Scanner scanner = new Scanner(System.in);
    	/* 
    	 * Run program and instructions will be stated accordingly. 
    	 * User is expected to provide a quote and choose a celebrity to impersonate.
    	 */
    	
    	System.out.println("Welcome to the Celebrity Impersonator! \n"
    			+ "	This game allows you to enter a quote and choose from our celebrity list to impersonate. \n"
    			+ " Please enter your quote:");
    	String userQuote = scanner.nextLine();

    	System.out.println("Then enter the celebrity options below whom you'd like to impersonate: \n"
    			+ " Canadian \n"
    			+ " Valley Girl \n"
    			+ " Shatner \n"
    			+ " Pirate \n"
    			+ " Zatanna \n"
    			+ " Yoda \n"
    			+ " Scooby Doo");
    	String userGeneratorName = scanner.nextLine();
    	scanner.close();
    	
    	
    	CelebrityImpersonator ci = new CelebrityImpersonator();
    	;
    	System.out.println(ci.generateQuote(userQuote, userGeneratorName));
    	
    	
//		** Default test cases **
//        CelebrityImpersonator ci = new CelebrityImpersonator();
//        String quote = "Four score and seven years ago";
//        System.out.println("Original Quote: \"" + quote + "\"");
//        for (String[] test : tests)
//        {
//            System.out.println("Testing " + test[0] + ":");
//            String answer = ci.generateQuote(quote, test[0]);
//            if (answer.equals(test[1]))
//            {
//                System.out.println("Success");
//            } else
//            {
//                System.out.println("Expected: " + test[1]);
//                System.out.println("Got:      " + answer);
//            }
//            
//        }
    }
}
