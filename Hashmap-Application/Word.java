
/**
 * This class stores a particular word that is placed into an ArrayList so that the main program,
 * PopularNames may sort it by a comparator. A Word object has two characteristics:
 * its key(the word itself), and its frequency(how many times the word appears).
 * 
 * @author Alexander Lim
 * Data Structures, Fall 2016
 */

package project5;

public class Word {

	private String key;
	private int freq;
	
	/**
	 * This is the two argument constructor for the Word class
	 * @param s : word
	 * @param frequency : how many times word appeared
	 */
	public Word(String s, int frequency){
		key = s;
		freq = frequency;
	}
	
	/**
	 * This is the getter method for the key variable.
	 * @return : key
	 */
	public String getKey(){
		return key;
	}
	
	/**
	 * This is the setter method for the key variable.
	 * @param newKey : new key
	 */
	public void setKey(String newKey){
		key = newKey;
	}
	
	/**
	 * This is the getter method for the freq variable.
	 * @return : frequency of the Word object.
	 */
	public int getFreq(){
		return freq;
	}
	/**
	 * This is the setter method for the freq variable.
	 * @param newFreq
	 */
	public void setFreq(int newFreq){
		freq = newFreq;
	}
	
}
