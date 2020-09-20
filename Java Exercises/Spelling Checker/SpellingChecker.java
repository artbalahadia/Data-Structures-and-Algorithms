/*
 * This program acts as the checker for a spelling test. The program will read file SpellingTest
 * which contains all the words that will be mentioned by a proctor.The user will then input the 
 * answers then the program will record and check if this is correct.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SpellingChecker {
	
	

	public static void main(String[] args) throws FileNotFoundException {
		// to read Spelling Test file containing words to spell
		Scanner testFile1 = new Scanner(new File("SpellingTest"));
		// ArrayList that will store words in file which would be converted into an array
		List<String> wordList = new ArrayList<String>();
	
		String word = "";
		while(testFile1.hasNext()) {
			word = testFile1.next();
			wordList.add(word);
		}
		testFile1.close();
		
		String[] wordArray = wordList.toArray(new String[0]);
		// Starts the program/test
		test(wordArray);

	}
	
	/*
	 *  this method will prompt user for each word to be spelled. It will then be sent to
	 *  the checker method that will respond accordingly.
	 */
	public static void test(String[] wordArray) {
		
		Scanner input = new Scanner(System.in);
		
		if(wordArray[0] == null) {
			System.out.println("File does not contain any words.");
		}
		
		for(int i = 0; i < wordArray.length; i++) {
			int attempt = 1;
			// Question starts from 1, 2, 3 .. (if any)
			System.out.println("Enter spelling for word " + (i+1));
			String answer = input.nextLine();
			checker(wordArray[i], answer, attempt);
		}
		
		input.close();
	}
	
	/*
	 * Checker will print the correct answer, number of attempts, if correct or
	 * incorrect and the exact user input. If incorrect, user will be given a chance to retry.
	 */
	public static void checker(String correctAnswer, String userInput, int attempt) {
		
		Scanner input = new Scanner(System.in);
		
		String check;
		
		if(correctAnswer.equals(userInput)) {
			check = "correct";
			System.out.println(correctAnswer + " - " + attempt + " attempts ending with the " + check +
					" answer: " + userInput);
		} else {
			check = "incorrect";
			System.out.println(check + ". Try again? (yes/no)");
			String tryAgain = input.nextLine().toLowerCase();
			if(tryAgain.equals("yes")) {
				attempt++;
				System.out.println("Enter answer:");
				String answerAgain = input.nextLine();
				checker(correctAnswer, answerAgain, attempt);
			} else {
				System.out.println(correctAnswer + " - " + attempt + " attempts ending with the " + check +
						" answer: " + userInput);
			}
		}
		input.close();
	}
	
}
