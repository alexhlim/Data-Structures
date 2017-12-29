
/**
 * This class provides the building blocks for the MyBST<E> class. It is a generic type
 * that implements the Comparable interface, so that it may be compared with other nodes.
 *
 * @author Alexander Lim
 * Data Structures, Fall 2016
 *
 * @param <E> : Generic Data
 */

package project4;

class Node<E extends Comparable<E>> implements Comparable<Node<E>> {

	private E data;
	private Node<E> left;
	private Node<E> right;
	
	/**
	 * No argument constructor
	 */
	public Node(){
		data = null;
	}
	
	/**
	 * 1 argument constructor.
	 * @param data : Generic data 
	 */
	public Node(E data){ 
		this.data = data;
	}
	
	/**
	 * 3 argument constructor.
	 * @param data : Generic data
	 * @param left : Node that acts as the left 
	 * @param right : Node that acts as the right
	 */
	public Node(E data, Node<E> left, Node<E> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Getter method for data.
	 * @return generic data
	 */
	public E getData() {
		return data;
	}
	
	/**
	 * Setter method for data.
	 * @param newData : generic data
	 */
	public void setData(E newData){
		data = newData;
	}
	
	/**
	 * Getter method for left node.
	 * @return : left node
	 */
	public Node<E> getLeft(){			
		return this.left;
	}
	
	/**
	 * Setter method for the left node.
	 * @param newLeft : new left node
	 */
	public void setLeft(Node<E> newLeft){
		left = newLeft;
	}
	
	/**
	 * Getter method for the right node.
	 * @return : right node
	 */
	public Node<E> getRight() {				
		return this.right;
	}
	
	/**
	 * Setter method for the right node.
	 * @param newRight : new right node.
	 */
	public void setRight(Node<E> newRight){
		right = newRight;
	}
	
	 /**
	  * toString method, returns the Node's data, left node, and right node
	  */
	@Override
	public String toString() {			
		return "Data: " + data + " Left: " + left + " Right: " + right;
	}
	
	/**
	 * compareTo method, compares the data of two Nodes.
	 */
	@Override
	public int compareTo(Node<E> o) {
		// uses the generic compareTo method
		return this.getData().compareTo(o.getData());		
	}

}