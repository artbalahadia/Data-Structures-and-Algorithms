/*
 * TipCalculator prompts the user for the total amount of purchase (totalPurchase) and the tip amount (tip).
 * The program then runs and calculates the amount to be added as tip and provides the totalAmount to be paid (including tip).
 * Note that the program can also be run through the command line using parameters totalPurchase and tip (see solution below).
 */

import java.util.*;

public class TipCalculator {
	
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		
		/*
		 * ----- SOLUTION FOR PROFICIENT ------
		 * The block below is to be used if user intends to pass arguments using the command line.
		 * Please uncomment in order to run the program and provide totalPurchase and tip (omit % sign).
		 * (i.e. java TipCalculator 10 15)
		 * If user is not using command line, run as is and follow through the program.
		 */
		
//		double cLTotalPurchase = 0;
//		double cLTip = 0;
//		
		/*
		 * converts the parameter passed from the command line to a double and uses the tipCalculator to execute.
		 * throws an error if input was invalid.
		 */
//		try {
//			cLTotalPurchase = Double.parseDouble(args[0]);
//			cLTip = Double.parseDouble(args[1]);
//		} catch(Exception e) {
//			System.out.println("Invalid input. Please enter arguments as numbers.");
//		}
//		
//		tipCalculator(cLTotalPurchase, cLTip);
//		System.exit(0);
		
		/*
		 * ----- SOLUTION FOR COMPETENT/ADVANCED -----
		 * Asks the user for input on totalPurchase and tip then uses the tipCalculator to execute.
		 */
		
		System.out.println("Please enter the total amount of purchase:");
		double totalPurchase = scanner.nextDouble();
		
		System.out.println("Please enter the tip amount: (omit the % sign)");
		double tip = scanner.nextDouble();
		scanner.nextLine();
		
		tipCalculator(totalPurchase, tip);
			
		
	}
	
	public static void tipCalculator(double totalPurchase, double tip) {
		// checker for invalid values (negatives, etc.)
		if(totalPurchase < 0 && tip < 0) {
			System.out.println("Invalid values, please try again.");
		}
		
		// standard/exact calculator
		double tipAmount = totalPurchase * (tip / 100);
		double totalAmount = totalPurchase + tipAmount;
		printReceipt(totalPurchase, tip, tipAmount, totalAmount);
		
		/*
		 *  flags for amounts that can be rounded up and asks if user would like to do so. 
		 *  Rounds up to the next higher dollar amount (i.e. totalAmount = 11.50 -> 12.00
		 */
		if(totalAmount % 1 != 0) {
			System.out.println("Would you like to round up? (yes/no)");
			String answer = scanner.nextLine().toLowerCase();
			if(answer.equals("yes")) {
				double totalAmountRounded = Math.ceil(totalAmount);
				double tipAmountRounded = totalAmountRounded - totalPurchase;
				double tipRounded = Math.round((tipAmountRounded / totalAmountRounded) * 100);
				printReceipt(totalPurchase, tipRounded, tipAmountRounded, totalAmountRounded);
				System.out.println("Thank you!");
			} else {
				System.out.println("Okay. Thank you!");
			}
		} 
		
	}
	
	// prints the details (total purchase, tip (%), calculated tip amount and total amount with tip) to the user
	public static void printReceipt(double totalPurchase, double tip, double tipAmount, double totalAmount) {
		System.out.println("Total purchase: " + totalPurchase + "\n" +
				"Tip amount: " + tipAmount + " (" + tip + "%) \n" +
				"Total amount with tip: " + totalAmount);
	
	}
	

	
	

}
