/*
 * HUFFMAN TREE PROJECT
 * NODE CLASS
 * @author Bethany Bridgewater
 * @lastEdit 16 February 2018
 * 
 * This class defines a node associated with a Huffman tree. 
 * Stores a character, frequency, and left and right children, if any.
 * CompareTo method is overriden so that a call to ArrayList.sort() wil
 * use natural ordering according to frequency. This is used in the 
 * tree class to create a priority queue.
 */
//package huffmanTree; 
class Node implements Comparable{
	private char character;
	private int frequency;
	private Node leftChild;
	private Node rightChild;
	public boolean hasChar;
	
	// no args constructor
	Node(){
		// character not initialized, by default equal to null
		hasChar = false;
		frequency = 0;
		leftChild = null;
		rightChild = null;
	}
	
	// constructor with a given character to store
	Node (char character, int frequency){
		this.character = character;
		this.frequency = frequency;
		leftChild = null;
		rightChild = null;
		hasChar = true;
	}
	
	// constructor for a branch node, no character, but has a frequency
	Node (int frequency){
		this.frequency = frequency;
		hasChar = false;
	}
	
	// returns true if the node has at least one child
	public boolean hasChildren(){
		if(this.hasLeftChild() || this.hasRightChild())
			return true;
		else
			return false;
	}
	
	// returns true if the node has a left child
	public boolean hasLeftChild(){
		if (this.getLeftChild() == null)
			return false;
		else
			return true;
	}
	
	// returns true if the node has a right child
	public boolean hasRightChild(){
		if (this.getRightChild()== null)
			return false;
		else
			return true;
	}
	
	// override so that ArrayList.sort() will sort nodes by frequency
	@Override
	public int compareTo(Object arg0) {
		Node c = (Node)(arg0); //cast object to type node
		if(this.getFrequency() < c.getFrequency())
				return -1;
		else if (this.getFrequency() > c.getFrequency())
			return 1;
		else return 0;
		}

	//Getters and Setters ------------------------------------------------
	public char getCharacter() {
		return character;
	}

	public void setCharacter(char character) {
		this.hasChar = true;
		this.character = character;
	}

	public Node getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	public Node getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}

	public int getFrequency() {
		return frequency;
	}
} // end of class node
