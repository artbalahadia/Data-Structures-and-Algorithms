/*
 * This program runs a series of questions that takes in user input and uses it to
 * provide the "Fortune Teller" information on their reading.
 */

import java.util.*;

public class FortuneTeller {

	public static void main(String[] args) {
		System.out.println("Welcome to the Fortune Teller! Please answer the questions with all honesty for our tellers to give the most accurate"
				+ " fortune for you.");
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("What is your name?");
		String name = scanner.nextLine();
		
		System.out.println("What top are you wearing?");
		String top = scanner.nextLine();
		
		System.out.println("What color?");
		String topColor = scanner.nextLine();
		
		System.out.println("What bottom are you wearing?");
		String bottom = scanner.nextLine();
		
		System.out.println("What was your last meal?");
		String meal = scanner.nextLine();
		
		System.out.println("Who was the last person you talked to?");
		String lastPerson = scanner.nextLine();
		
		System.out.println("How old are you?");
		int age = scanner.nextInt();
		
		System.out.println("Which fortune teller would you like? \n"
				+ " [1] Good \n [2] Bad");
		int teller = scanner.nextInt();
		
		if(teller == 1) {
			goodFortune(name, top, topColor, bottom, meal, lastPerson, age);
		} else if(teller == 2){
			badFortune(name, top, topColor, bottom, meal, lastPerson, age);
		}
		
		
		scanner.close();
	}
	
	
	// Good and Bad fortune tellers will use the same parameters and provide their reading based on that.
	
	
	public static void goodFortune(String name, String top, String topColor, String bottom,
			 String meal, String lastPerson, int age) {
		
		System.out.println("Hi " + name + ", thanks for choosing the good fortune teller. I see you're in a good luck \n" +
				"because tomorrow you will receive a box with new " + top + ". If you wear anything \n" + topColor +
				" as well, you will win a fresh pair of " + bottom + " through your email. Upon checking your inbox check \n" +
				"the first restaurant message because you will also receive a free " + meal + ". To top it all off, you \n" +
				"will see " + lastPerson + " tomorrow and that person will give you " + age*10 + " as a gift for choosing me \n" +
				"as your fortune teller.");
		
	}
	
	
	public static void badFortune(String name, String top, String topColor, String bottom,
			 String meal, String lastPerson, int age) {

		System.out.println("Before you came I already knew that your name is " + name +". I'm here to tell you your bad fortune so please beware. \n"
				+ "Tomorrow you will wear the same " + top + " and you will go outside. Watch out for a \n" + topColor +
				" car as it will run over a puddle and will splash on you. Throughout the day make sure \n" + 
				"you sit properly as there is a chance your " + bottom + " will rip. Also, don't eat " + meal + " because it \n" + 
				"will give you a bad stomach. Most importantly, make sure to keep a distance from \n" + lastPerson + 
				" because that person's day will be even worse. Oh and I forgot, if any of these happen, check \n" + 
				"your bank account as there will be an extra " + age*10 +" dollar charge for my accuracy.");
	}
	
	

}
