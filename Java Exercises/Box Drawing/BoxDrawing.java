import java.util.*;
public class BoxDrawing {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the height of the box followed by its width:");
		int length = scanner.nextInt();
		int width = scanner.nextInt();
		System.out.println("Enter 1 if box will be filled or 2 if border only:");
		int filled = scanner.nextInt();
		System.out.println("Enter 1 for a simple box or 2 for a box with special borders:");
		int special = scanner.nextInt();
		if(special == 1) {
			printSimpleBox(length, width, filled);
		} else if(special == 2) {
			printSpecialBox(length, width, filled);
		}
		scanner.close();
	}
	
	public static void printSimpleBox(int length, int width, int filled) {
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				if(filled == 1) {
					if(j == width-1) {
						System.out.print("*\n");
					} else {
						System.out.print("*");
					}
				} else if(filled == 2) {
					if(i > 0 && i < width-1) {
						if(j > 0 && j < width-1) {
							System.out.print(" ");
						} else if(j == 0){
							System.out.print("*");
						} else if(j == width-1) {
							System.out.print("*\n");
						}
					} else {
						if(j == width-1) {
							System.out.print("*\n");
						} else {
							System.out.print("*");
						}
					}
				}
			}
		}
	}
	
	public static void printSpecialBox(int length, int width, int filled) {
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				if(filled == 1) {
					if(i == 0 || i == width-1) {
						if(j == 0) {
							System.out.print("+");
						} else if(j == width-1) {
							System.out.print("+\n");
						} else {
							System.out.print("-");
						}
					} else {
						if(j == 0) {
							System.out.print("|");
						} else if(j == width-1) {
							System.out.print("|\n");
						} else {
							System.out.print("*");
						}
					}
				} else if(filled == 2) {
					if(i == 0 || i == width-1) {
						if(j == 0) {
							System.out.print("+");
						} else if(j == width-1) {
							System.out.print("+\n");
						} else {
							System.out.print("-");
						}
					} else {
						if(j == 0) {
							System.out.print("|");
						} else if(j == width-1) {
							System.out.print("|\n");
						} else {
							System.out.print(" ");
						}
					}
				}
			}
		}
	}
}