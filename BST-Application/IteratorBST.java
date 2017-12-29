
/**
 * This Class acts as an iterator for the MyBST class. It iterates through MyBST
 * and also implements methods from the iterator interface. 
 * 
 * @author Alexander Lim
 * Data Structures, Fall 2016
 *
 * @param <E> : generic data type
 */

package project4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class IteratorBST<E extends Comparable<E>> implements Iterator<E> {

	// array list that stores elements(generic type)
	private ArrayList<E> list = new ArrayList<E>();	
	// index of of the next item to returned in ArrayList
	private int nextIndex;
	
	/**
	 * This is the constructor class for IteratorBST. It calls the 
	 * inOrderTraversal method to store elements into the ArrayList.
	 * @param root : Node to traverse the tree
	 */
	public IteratorBST(Node<E> root) {
		this.inOrderTraversal(root);			
	}
	
	/**
	 * This is the getter method for list
	 * @return : ArrayList stored in this class
	 */
	public ArrayList<E> getList(){
		return list;
	}
	
	/**
	 * This is the setter method for the list
	 * @param newList : new ArrayList for this class
	 */
	public void setList(ArrayList<E> newList){	
		this.list = newList;	
	}
	
	/**
	 * This is the getter for the nextIndex variable.
	 * @return : Index of Next Index
	 */
	public int getNextIndex(){
		return this.getNextIndex();
	}
	
	/**
	 * This is the setter for the nextIndex variable
	 * @param newIndex : newIndex to be set
	 */
	public void setNextIndex(int newIndex){				// BLEH
		nextIndex = newIndex;
	}
	
	/**
	 * This is the method that puts elements in an ArrayList by using
	 * in order traversal.
	 * @param root : Node to traverse through tree
	 * @return : an ArrayList filled with elements
	 */
	public ArrayList<E> inOrderTraversal(Node<E> root) {
		if (root != null) {
			// recursive call to left child
			inOrderTraversal(root.getLeft());
			// add to ArrayList
			list.add(root.getData());
			// recursive call to right child
			inOrderTraversal(root.getRight());
		}
		return list;
		
	}
	/**
	 * This is the hasNext method returns a boolean whether the iterator has more elements.
	 */
	@Override
	public boolean hasNext() {
		// if index is less than ArrayList size, there are more elements
		if (this.nextIndex < list.size()) {
			return true;
		}
		return false;
	}

	/**
	 * This is the next method for the Iterator class. It returns the next element
	 * in the iteration.
	 * @throws NoSuchElementException : when element does not exist
	 */
	@Override
	public E next() {
		// calls hasNext method
		if (this.hasNext()){
			E data = list.get(nextIndex);
			// increments the nextIndex variable
			nextIndex++;
			return data;
		}
		// else: element does not exist
		throw new NoSuchElementException("Element does not exist.");
	}
		

	/**
	 * This is the remove method for the Iterator class.
	 * Traditionally, it would remove from the underlying collection the last
	 * element returned by the iterator. However, since this method only works
	 * once the next() method is called, this method is not supported in this
	 * particular iterator class.
	 * @throws UnsupportedOperationException : remove method not accessible in this class
	 */
	@Override									
	public void remove() {					
		// throw exception
		throw new UnsupportedOperationException("This iterator class does not perform remove.");
	}
	
	/**
	 * This is the forEachRemaning method, which performs the given action for each remaining
	 * element until all elements have been processed or an exception was being thrown. However, in this 
	 * project I will not allow the iterator to use this operation to prevent any complication.
	 * @param action : action is to be performed
	 * @throws NullPointerException : when action parameter is null
	 */
	// method commented out because it was not supported on my version of eclipse
//	@Override
//	public void forEachRemaining(Consumer<? super E> action){
		// throw exception	
//		throw new UnsupportedOperationException("This iterator class does not perform forEachRemaining.");
//	}
	
}
	
