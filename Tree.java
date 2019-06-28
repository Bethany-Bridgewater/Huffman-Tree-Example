/**
 * HUFFMAN TREE 
 * COSC 3333 Data Structures and Algorithms II
 * @AUTHOR Bethany Bridgewater 
 * @LASTEDIT 12 February 2018
 * 
 * This class creates a Huffman tree specific to a given String, and the
 * corresponding character code table.
 */

//package huffmanTree;

import java.util.ArrayList;
import java.util.Stack;

class Tree {
	private Node root = null;
	private ArrayList <Integer> frequencies; // an array of int frequencies
	private ArrayList <Character> characters; // array chars associated with frequencies
	private ArrayList <Node> nodes; // array of nodes used in priority queue
	private String [] codes = new String [256]; // code table, 256 is the extended ASCII set
	private String message = ""; // the message to be encoded
	private String codedMessage = ""; // the message in huffman code

	Tree (String message){
		this.message = message; // store the message
		// initialize arrayList objects
		characters = new ArrayList<Character>(); 
		frequencies = new ArrayList<Integer>();
		nodes = new ArrayList<Node>();
		
		root = createTree(); 	// create the tree
		createCodeTable(codes, "", this.root); // create the code table
		codedMessage = encode();	// encode the message
	}
	
	private Node createTree (){
		char ch;
		// create an arrayList of unique characters, and arrayList of
		// their frequencies by corresponding index 
		for (int i = 0; i < this.message.length(); i++){
			ch = this.message.charAt(i);
			// if array already contains the character
			if(characters.contains(ch)){
				// increment the frequency of that char in the freq array
				int index = characters.indexOf(ch);
				frequencies.set(index, frequencies.get(index)+1);
			}
			// else character is unique, add to character array
			else {
				characters.add(this.message.charAt(i));
				frequencies.add(1);
			}
		} // end for loop, unique characters
		
		// create arrayList of nodes using arrayLists of characters and frequencies
		for (int n = 0; n < characters.size(); n++){
			nodes.add(new Node(characters.get(n), frequencies.get(n)));
		}

		// use priority queue to create the huffman tree
		while(nodes.size() > 1){
			// sort by frequency to create a priority queue
			nodes.sort(null);
			
			// create a subtree from the first 2 elements in priority queue
			int subFrequency = nodes.get(0).getFrequency() + nodes.get(1).getFrequency();
			Node branch = new Node(subFrequency);
			branch.setLeftChild(nodes.get(0)); // create subTree
			branch.setRightChild(nodes.get(1));
			nodes.remove(1); // remove subTree nodes from queue
			nodes.remove(0);
			nodes.add(branch); // add the subTree back into queue
		}
		
		// the last node in the queue is the root of the huffman tree
		return nodes.get(0);
	}
	
	// recursive method to create a code table
	private void createCodeTable(String[] codes, String currentCode, Node current){
		
		if(current.hasChildren()){
			createCodeTable(codes, currentCode+"0", current.getLeftChild());
			createCodeTable(codes, currentCode+"1", current.getRightChild());
			}
		else
			codes[current.getCharacter()] = currentCode;
	}
	
	// method to display the code table
	public void displayCodes(){
		for (int i = 0; i < 256; i++) {
			if (this.codes[i] != null)
			System.out.println(this.codes[i] + ":" + (char) i);
		}
	}
	
	// display the huffman tree, only works for trees less than 5 levels
	public void displayTree(){

			Stack<Node> globalStack = new Stack<Node>();
			globalStack.push(root);
			int nBlanks = 32;
			boolean isRowEmpty = false;
			System.out.println(
					"......................................................");
			while(isRowEmpty==false)
			{

				Stack<Node> localStack = new Stack<Node>();
				isRowEmpty = true;
				for(int j=0; j<nBlanks; j++)
					System.out.print(' ');
				while(globalStack.isEmpty()==false)
				{
					Node temp = (Node)globalStack.pop();
					if(temp != null)
					{
						
						System.out.print((temp.hasChar) ? temp.getCharacter() : "-");
						localStack.push(temp.getLeftChild());
						localStack.push(temp.getRightChild());
						if(temp.hasChildren())
							isRowEmpty = false;
					}
					else
					{
						System.out.print("--");
						localStack.push(null);
						localStack.push(null);
					}
					for(int j=0; j<nBlanks*2-2; j++)
						System.out.print(' ');
				}  // end while globalStack not empty
				System.out.println();
				nBlanks /= 2;
				while(localStack.isEmpty()==false)
					globalStack.push( localStack.pop() );
			}  // end while isRowEmpty is false
			System.out.println(
					"......................................................");
	} // end of display method
	
	
	// in order traversal of the tree that prints to the console
	public void inOrder (Node current){
		if(current != null){
			inOrder(current.getLeftChild());
			inOrder(current.getRightChild());
			System.out.print(current.getCharacter() + " ");
		}
	}

	// return the root of the tree
	public Node getRoot() {
		return root;
	}

	// return the code table for a tree
	public String[] getCodes() {
		return codes;
	}

	// create a coded message from a string using code table
	public String encode() {
		String codedMessage = "";
		char ch;
		for(int i = 0; i < this.message.length(); i++){
			ch = this.message.charAt(i);
			// get the code from the code table and concatenate to codedMessage
			codedMessage += getCharacterCode(ch); 
		}
		return codedMessage;
	}
	
	// method returns the code for a character from the huffman code table
	private String getCharacterCode(char ch){
		int index = (int) ch;
		return this.codes[index]; 
	}
	
	// public method to decode a tree, calls recursive method below
	public void decode (Tree tree){
		decode(tree.codes, tree.codedMessage, 0, 1);
	}
	
	// recursive method to decode a codedMessage using an array of huffman codes
	private void decode (String[] codes, String codedMessage, int startIndex, int endIndex){
		
		// create substring to search for
		String subString = "";
		if(endIndex == codedMessage.length())
			subString = codedMessage.substring(startIndex); // last substring, no endIndex
		else
			subString = codedMessage.substring(startIndex, endIndex);
		
		// iterate over the array of huff codes, find subString
		for(int i = 0; i< codes.length; i++){
			if(codes[i] != null){
				// is it a match? output the character
				 if(codes[i].equals(subString)){
					System.out.print((char)i); // output the decoded character
					startIndex = endIndex; // increment the substring
					break; // break out of for loop
				}
			} // end of if !null
		} // end of for loop
		// more message to decode? increment the substring
		if (endIndex < codedMessage.length())
			decode(codes, codedMessage, startIndex, endIndex+1);
		else // end of coded message
			System.out.println("\nend of message\n");
	} // end of searchDecode method

	public String getCodedMessage() {
		return this.codedMessage;
	}
} // end of class Tree
