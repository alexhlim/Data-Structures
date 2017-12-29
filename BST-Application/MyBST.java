
/**
 * This class represents a generic binary search tree and acts as a container. It contains all the methods a normal BST
 * would have including: add, remove, contains, first, last, isEmpty, and iterator.
 * 
 * @author Alexander Lim
 * Data Structures, Fall 2016
 *
 * @param <E> : generic data that extends the comparable interface so that the compareTo method
 * 				may be used
 */

package project4;

import java.util.NoSuchElementException;

public class MyBST<E extends Comparable<E>>  {

	// protected root so it may be accessed in other classes
	protected Node<E> root;
	
	/**
	 * No argument constructor: creates an instance of MyBST. It sorts 
	 * according to ascending order.
	 */
	public MyBST(){			
		root = null;
	}
	
	/**
	 * Public wrapper method for add. It checks if the data is null or if the root is null
	 * @param data
	 * @return : boolean of whether the element was added
	 * @throws : NullPointerEception : when data is null
	 */
	public boolean add(E data){
		// check if data is null
		if (data == null) {
			throw new NullPointerException("Data is pointing to null"); }		
		// check if the root is null, if so, add a node as the root
		if (root == null) {
			Node<E> newNode = new Node<E>(data);
			root = newNode;
		}
		// recursive call to private method
		return add(data,root);
	}
	
	/**
	 * This is the private recursive method for add. It uses compareTo to check if the data
	 * is already present in the tree. It tries to add nodes to the leaves of the tree 
	 * to maximize efficiency.
	 * @param data : generic data
	 * @param n : Node
	 * @return boolean of whether Node was added or not
	 */
	private boolean add(E data, Node<E> n) {
		// check if node data is greater than E data
		if (n.getData().compareTo(data) > 0) {
			// check if left is null, if so place node there
			if (n.getLeft() == null) {
				Node<E> newLeftNode = new Node<E>(data); 
				n.setLeft(newLeftNode); 
				return true;
			}
			else {
				// recursive call to the left child
				return add(data, n.getLeft());
			}
		}
		else if (n.getData().compareTo(data) == 0) {
			// if the data is in the tree, do not add into tree
			return false;
		}
		else{
			// check if right is null, if so place node there 
			if (n.getRight() == null) {
				Node<E> newRightNode = new Node<E>(data);
				n.setRight(newRightNode);
				return true;
			}
			else {
				// recursive call to the right child
				return add(data, n.getRight());
			}
		}
	}
	
	/**
	 * This is the public wrapper method for contains. It checks whether the parameter is correctly
	 * inputed and returns a boolean if the object is present in the tree.
	 * @param o : Object
	 * @return : boolean, true if Object is there, false if it is not there
	 * @throws : NullPointerException : when data is null
	 */
	public boolean contains(Object o){
		// check if the object is null
		if (o == null) {
			throw new NullPointerException("Object is pointing to null");
		}
		// recursive call to the private method
		return contains(o, root);
		
	}
	
	/**
	 * This is the private contains method that uses the compareTo method to check if a certain
	 * object is within the tree. It traverses through the tree by making various recursive calls
	 * based on the compareTo values.
	 * @param o : Object needed to be found
	 * @param n : Node to traverse the tree
	 * @return  : boolean whether the object is found or not
	 */
	private boolean contains(Object o, Node<E> n ) {
		// casting to E and storing to variable for convenience
		E object = (E) o;	
		// base case: node is null, meaning the node is at the bottom and object is not in the tree
		if (n == null){			
			return false;
		}
		if (n.getData().compareTo(object) > 0) {
			// recursive call to left child
			return contains(o, n.getLeft());
		}
		else if (n.getData().compareTo(object) < 0) {
			// recursive call to right child
			return contains(o, n.getRight());
		}
		else if (n.getData().compareTo(object) == 0) {
			// if object found, return true
			return true;
		}
		else {
			// normal case: object not there, return false
			return false;
		}
	}
	
	/**
	 * This is the public wrapper method for the first method. It checks to see if the root is null,
	 * otherwise it will call to the private first method.
	 * @return : data of the first (lowest) element
	 * @throws NoSuchElementException : When tree does not exist
	 */
	public E first() throws NoSuchElementException{			
		// check if root is null
		if (root == null){
			throw new NoSuchElementException("Tree does not exist"); 		
		}
		else {
			// recursive call to private method
			return first(root);
		}
	}
	
	/**
	 * This is the private first method that traverses to the leftmost part of the tree to find
	 * the object with the lowest element.
	 * @param n : Node to traverse the tree
	 * @return : Data of the lowest element 
	 */
	private E first(Node<E> n){		
		// base case: leftmost child (leaf)
		if (n.getLeft() == null) {			
			return n.getData();
		}
		// recursive case: go to the left child
		else {
			return first(n.getLeft());
		}
	}
	
	/**
	 * This method checks to see if the tree is empty by checking the root.
	 * @return : boolean if the tree is empty or not
	 */
	public boolean isEmpty(){
		// empty case
		if (root == null) {
			return true;
		}
		// else not empty
		else {
			return false;
		}
	}
	
	/**
	 * This is the iterator method that creates and instance of IteratorBST and returns it.
	 * This iterator is used to traverse the tree, especially in BabyNames class.
	 * @return : IteratorBST (class in this project)
	 */
	public IteratorBST<E> iterator() { 
		// return tree iterator 
		return new IteratorBST<E>(root);			
	}
	
	/**
	 * This is the public wrapper method for last. It checks to see if the root is null, otherwise
	 * it makes a call to its private recursive method
	 * @return : data of the greatest element
	 * @throws NoSuchElementException : when tree does not exist
	 */
	public E last() throws NoSuchElementException {
		// check if root is null
		if (root == null){
			throw new NoSuchElementException("Tree does not exist");  		
		}
		// otherwise call private recursive
		else {
			return last(root);
		}
	}
	/**
	 * This is the private last method that uses a node to traverse to the rightmost part of the
	 * tree and returns that element (the largest element)
	 * @param n : Node that traverses the tree
	 * @return : largest element in the tree
	 */
	private E last(Node<E> n) {		
		// if last node on right is pointing to null, return the data in root
		if (n.getRight() == null) {		
			return n.getData();
		}
		// recursive case: return recursive call to right child
		else {
			return last(n.getRight());
		}
	}
	
	/**
	 * This is the public wrapper method for remove. It checks to see if the parameter is null, 
	 * and calls the private remove method to check if there is anything to remove in the tree.
	 * @param o : Object that is to be removed
	 * @return : boolean of whether that object was removed or not.
	 * @throws NullPointerEception when Object is pointing to null
	 */
	public boolean remove(Object o){			
		// check if parameter is correct
		if (o == null) {
			throw new NullPointerException("Object is pointing to null"); }	
		// create node to test if node is in tree
		Node<E> test = remove(o, root);				
		// object not inside tree
		if (test == null){							
			return false;							
		}
		// object inside tree
		else {
			return true;							
		}
	}
	
	/**
	 * First private method for remove. This method checks to see if the object is within the tree.
	 * @param o : Object to be removed
	 * @param n : Node to traverse the tree
	 * @return : Node 
	 */
	private Node<E> remove(Object o, Node<E> n){
		// Casting object into variable for convenience
		E o2 = (E) o;
		E object = o2;
		// check if Node is null
		if (n == null){				
			// this returns null to public method 
			return n;	
		}
		// traverse left
		if (n.getData().compareTo(object) > 0) {
			Node<E> newLeft = remove(o, n.getLeft());	
			n.setLeft(newLeft);
						
		}
		// traverse right
		else if (n.getData().compareTo(object) < 0) {	
			Node<E> newRight = remove(o, n.getRight());
			n.setRight(newRight);
					
		}
		// found the object
		else if ((n.getData().compareTo(object) == 0)) {
			// special case for removing root
			if (n.getData() == root.getData())
			{
				// remove root with no children
				if (n.getLeft() == null && n.getRight() == null){
					// set root to null
					n.setData(null); 
				}
				// remove root with only right child
				else if (n.getLeft() == null){	
					// store value of root successor
					E data1 = getSuccessor(n);
					// set value of root successor
					n.setData(data1);
					// delete root successor
					n.setRight(remove(data1,n.getRight()));
					return n;
					}
				// remove root with only left child
				else if (n.getRight() == null){  
					// store value of root predecessor
					E data2 = getPredecessor(n);
					// set value of root predecessor
					n.setData(data2);
					// delete root predecessor
					n.setLeft(remove(data2,n.getLeft()));	
					return n;
				}
			}
			// private recursive call
			n = remove(n);
			return n;	
		}
		return n;
	}
	
	/**
	 * This method is the 2nd private method for remove. It considers three scenarios: when
	 * there is 0 children, 1 child, and 2 children. This method handles the remove differently
	 * according to the scenarios. If there are 2 children, it will call the getPredecessor method
	 * to figure out how to reconstruct the tree
	 * @param n : Node that traverses through the tree
	 * @return : Node that was removed
	 */
	private Node<E> remove(Node<E> n){
		// 1 child case
		if (n.getLeft() == null) {		
			// if left is null, remove the right
			return n.getRight();
		}
		// 1 child case
		if (n.getRight() == null) {			
			// if right is null, remove the left
			return n.getLeft();
		}
		// 2 child case, call getPredecessor method
		E data = getPredecessor(n);
		n.setData(data);
		Node<E> newLeft = remove(data,n.getLeft());	
		n.setLeft(newLeft);
		return n;
	}
	
	/**
	 * This method is for finding the predecessor when removing a node with two children.
	 * It looks for the rightmost node in a left subtree.
	 * @param n : Node for which we want the predecessor.
	 * @return : data of the predecessor
	 * @throws Error : left subtree is null
	 */
	public E getPredecessor(Node<E> n){
		// this case should not happen, throw an error 
		if (n.getLeft() == null){
			throw new Error("Left subtree is null.");
		}
		else {
			// create node to traverse through tree
			Node<E> current = n.getLeft();
			// traverse to the rightmost node of the left subtree
			while (current.getRight() != null) {
				current = current.getRight();
			}
			// return the data
			return current.getData();
		}
	}
	
	/**
	 * This method finds the successor, specifically for the root node. It is needed
	 * when the root is removed because the root will be replaced by its successor
	 * @param n : Node to traverse tree
	 * @return : Data of successor
	 * @throws Error : Right subtree is null
	 */
	public E getSuccessor(Node<E> n){
		// check if right subtree is null, otherwise
		// throw an exception because we want to traverse through there
		if (n.getRight() == null){
			throw new Error("Right subtree is null.");
		}

		else {
			// set current to right of root
			Node<E> current = n.getRight();
			// traverse to leftmost node (successor)
			while (current.getLeft() != null){
				current = current.getLeft();
			}
			
		return current.getData();
		
		}

	}
	
	
	
}

