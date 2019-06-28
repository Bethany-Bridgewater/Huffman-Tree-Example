
/*
 * @author Bethany Bridgewater
 * @Last edit: 16 February 2018
 * 
 * This class is a test and a basic UI for the huffman tree class.
 * 
 */
//package huffmanTree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


class HuffmanTreeTest {


	public static void main(String[] args) {
		String message = "default message.";
		Tree hTree = new Tree(message);
		boolean exit = false;
		int choice = 0;
		String codedMessage ="";

		
		// control loop
		while(!exit){
			// display the user menu and get user choice
			menu();
			Scanner input = new Scanner(System.in);
			choice = input.nextInt();

			// select and perform menu choice
			switch(choice){
			case 1:    //create a new huffman tree
				message = getValidInput();  // get a message from the user
				hTree = new Tree(message); // create new tree
				break;
				
			case 2:		// display the coded message
				System.out.println(hTree.getCodedMessage());
				break;
				
			case 3:		// display the code table
				hTree.displayCodes();
				break;
			case 4:		// display the decoded message
				hTree.decode(hTree);
				break;
				
			case 5:		// display the huffman tree
				hTree.displayTree();
				break;
				
			case 6:		// exit the program
				exit = true;
				input.close();
				break;
			default:
				System.out.println("Input a number between 1 and 6.");
				break;
					
			} // end of switch statement
		} // end of control loop
	} // end of main

	// display the UI on the console
	public static void menu(){
		System.out.println("Enter a number to select from the options below:");
		System.out.println("1. Create a new Huffman Tree");
		System.out.println("2. Display my coded message");
		System.out.println("3. Display the code table");
		System.out.println("4. Display my decoded message");
		System.out.println("5. Display the Huffman tree");
		System.out.println("6. Exit the program");
	} // end of menu method
	
	// gets a valid message from the user, possibly multiple lines
	public static String getValidInput() {
		Scanner input = new Scanner(System.in);
		boolean isValid = false;
		String inputString ="";
		System.out.println("How many lines of text would you like to enter?");
		int lines = input.nextInt();
		System.out.println("Enter your message:");
		for(int i = 0; i <= lines; i++){
			if( i > 0)
				inputString += "\n";
			inputString += input.nextLine();			
		}
		return inputString;
	} // end of getValidInput method
} // end of class huffman tree test
