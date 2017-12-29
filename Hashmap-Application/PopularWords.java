
/**
 * This is the main class in project5. It takes in two command line arguments, which are 
 * the file name and the way words should be organized(name, frequency, or scarcity). Second,
 * a scanner is initialized in order to go through the file and find words. A word is considered
 * either a single letter or a single letter followed by one or more word connectors(_-').
 * Then, words are added into a HashMap, where the words are stored as the keys and their
 * frequencies are stored as the value. After, the words are put into an ArrayList and 
 * are sorted using a merge sort method according to a comparator. Finally, results are 
 * printed to the console.
 * 
 * @author Alexander Lim
 * Data Structures, Fall 2016
 */

package project5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class PopularWords {

	public static void main(String[] args) {
		// command lines 1 and 2 variables
		// checking command line 3 later in code
		String fileName = null;
		String order = null;
		try {
			fileName = args[0];
			order = args[1];
		}
		// make sure user enters inputs for command line 1 and 2
		// if there is nothing in command lines, produce an error and terminate the program
		catch (ArrayIndexOutOfBoundsException e){
			System.err.println("Please enter text file in command line 1 and order(name,frequency, scarcity) in command line 2.");
			System.exit(1);
		}
	
		
		
		// create new file object	
		File f = new File(fileName);

		if (!f.canRead()) {																	
			// if file cannot be read
			System.err.printf("Error:cannot read " + "data from file %s" , fileName);
			System.exit(1);
		}
		
		// create new scanner
		Scanner inputFile = null;
		
		try {
			// initialize new Scanner
			inputFile = new Scanner (f);
			// use delimiter -> any character not in line is considered a delimiter
			// ([\\-_']+[\\-_']+) -> multiple word delimiters followed by multiple word delimiters
			// (\\s+[\\-_']+) -> at least one space before at least one hyphen, underscore, or apostrophe 
			// | -> or 
			// ([\\-_']+\\s+) -> at least one hyphen,underscore, or apostrophe followed by at least one space
			// (\\s*)  0 or more spaces
			// + 1 or more 
			// [^a-zA-Z\\-_'] -> not letters a-z or A-Z, nor - _'
			// [\\-_'] -> not hyphens, underscores or apostrophe
			// (--+) hyphen 2 or more 
			// (__+) underscores 2 or more
			// (''+) apostrophe 2 or more
			// ([\\s]+) one or more spaces
			inputFile.useDelimiter("([\\-_']+[\\-_']+)|(\\s+[\\-_']+)|([\\-_']+\\s+)|((\\s*)[^a-zA-Z\\-_'][\\-_']*(\\s*))|(--+)|(__+)|(''+)|([\\s]+)");
		}
		
		// if file cannot be found
		catch (FileNotFoundException e) {
			System.err.printf("Error: cannot read " + "data from file %s",  fileName);		
			System.exit(1);
		}
		
		// check command line 2
		Comparator<Word> comparator = null;
		// sort by name
		if (order.equalsIgnoreCase("name")){
			comparator = new CompareWordByName();
		}
		// sort by frequency
		else if (order.equalsIgnoreCase("frequency")){
			comparator = new CompareWordByFrequency();
		}
		// sort by scarcity
		else if (order.equalsIgnoreCase("scarcity")) {
			comparator = new CompareWordByScarcity();
		}
		// make sure command line 2 is valid
		else {
			System.err.println("Please provide a valid argument for command line 2 (name, frequency, scarcity).");
			System.exit(1);
		}
			
		
		// create new HashMap with words as keys and frequencies as values
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		// iterate using scanner
		// use .next() because of delimiter
		while (inputFile.hasNext()) {
			String word = inputFile.next();
			// convert word to lower case
			word = word.toLowerCase();
			// make sure empty strings not added to hash table
			if (!(word.equals(""))) {
				// handles if word begins with underscore, hyphen, or apostrophe, if so remove them	
				if (word.charAt(0) == '_' || word.charAt(0) == '-' || word.charAt(0) == '\''){
					word = word.substring(1);
				}
				// check if last char is apostrophe, if so remove it
				if (word.charAt(word.length()-1) == '_' || word.charAt(word.length()-1) == '-' || word.charAt(word.length()-1) == '\''){
					word = word.substring(0, word.length()-1);
				}
				if (map.containsKey(word)){ 
					// get frequency of the key
					int value = map.get(word);	
					// add frequency to existing word
					map.put(word, value + 1);	
				} else {
					// word is not in file, add to hash table and frequency is 1
					map.put(word, 1);	
					}
			}
		}	
	
		
		// create set to extract keys and values from HashMap
		Set<Map.Entry<String, Integer>> newMap = map.entrySet(); 
		// create new ArrayList to store word objects
		ArrayList<Word> arr = new ArrayList<Word>();
		// populate the ArrayList
		for (Map.Entry<String, Integer> entry : newMap) {
			// name of word -> key, frequency -> value
			Word w1 = new Word(entry.getKey(), entry.getValue());
			arr.add(w1);
		}
		
		// determine how many words to print out by checking command line 3
		int number = 0;
		try{
			// parse command line 3 to integer
			number = Integer.parseInt(args[2]);
			// if number to print out more than total words, produce error and exit.
			if (number > arr.size()){
				System.err.println("Integer is too large. Enter another integer for command line 3.");
				System.exit(1);
			}
		}
		// catch if Integer.parseInt does not work
		catch (NumberFormatException e) {
			System.err.println("Command line 3 must be an integer.");
			System.exit(1);
		}
		// handles when nothing is inputed for third command line
		catch (ArrayIndexOutOfBoundsException e) {
			// set number equal to number of elements in ArrayList
			number = arr.size();
		}
		
		
		// call merge sort and respective comparator (given in command line 2)
		mergeSort(arr,comparator);
		
		// print results
		for (int i = 0; i < number; i++){
			System.out.println( arr.get(i).getKey() + " " + arr.get(i).getFreq());
		}
	}
	
	
	/**
	 * This is the public wrapper for the merge sort. It calls a private recursive function
	 * to actually do the sorting.
	 * @param arr : ArrayList to be sorted
	 * @param comparator : how to organize the ArrayList
	 */
	public static void mergeSort(ArrayList<Word> arr, Comparator<Word> comparator){			// does this return a String array??
		// call private recursive function
		mergeSortRec(arr, 0, arr.size()-1, comparator);
	}
	
	/**
	 * This method is the private recursive function for merge sort. It splits the ArrayList
	 * into smaller ArrayLists until the the entire ArrayList is sorted. Then it calls another
	 * private method that merges all of the ArrayLists back into one. 
	 * @param arr : ArrayList to be sorted
	 * @param firstIndex : first index of ArrayList
	 * @param lastIndex : last index of ArrayList
	 * @param comparator : comparator to be used for sorting 
	 */
	private static void mergeSortRec(ArrayList<Word> arr, int firstIndex, int lastIndex, Comparator<Word> comparator){ // does this return a String array??
		// base case
		if (firstIndex == lastIndex){
			return;
		}
		// calculate the middle of ArrayList to find out where to split it 
		int middle = (firstIndex + lastIndex)/2;
		// recursive call to new left ArrayList
		mergeSortRec(arr, firstIndex, middle, comparator);
		// recursive call to new right ArrayList
		mergeSortRec(arr, middle + 1, lastIndex, comparator);
		// merge ArrayLists back into one
		merge(arr, firstIndex, middle, middle + 1, lastIndex, comparator);
	}
	
	/**
	 * This is the second private method involved in merge sort. This method actually
	 * merges the ArrayLists together by using a temporary array with the size of elements
	 * in the ArrayList. This temporary array uses the compare method method to actually
	 * sort the elements in the ArrayList. After the temporary array is populated, elements
	 * from this array are then copied back to the original ArrayList. 
	 * @param arr : ArrayList to be sorted
	 * @param leftFirst : first index of the left side of the ArrayList
	 * @param leftLast : last index of the left side of the ArrayList
	 * @param rightFirst : first index of the right side of the ArrayList
	 * @param rightLast : last index of the right side of the ArrayList
	 * @param comparator : comparator to be used
	 */
	private static void merge(ArrayList<Word> arr, int leftFirst, int leftLast, int rightFirst, int rightLast, Comparator<Word> comparator) {
		// temporary array with size of number of elements in original ArrayList
		Word[] temp = new Word[(rightLast - leftFirst) + 1];
		// new variable to set to leftFirst
		int indexLeft = leftFirst;
		// new variable to set to rightFirst
		int indexRight = rightFirst;
		// index counter for temporary array
		int index = 0;
		// populating the temp array
		while ( (indexLeft <= leftLast) && (indexRight <= rightLast) ) {
			// use comparator compare method
			// if element in indexLeft is considered "smaller" 
			if ( comparator.compare(arr.get(indexLeft), arr.get(indexRight)) < 0) {	
				temp[index] = arr.get(indexLeft);
				// change position of indexLeft
				indexLeft++;
			}
			// if element in indexRight is considered "smaller"
			else {
				temp[index] = arr.get(indexRight);
				// change position of indexRight
				indexRight++;
			}
			index++;	
		}
		// copy elements in range indexLeft-leftLast to temp
		for (int i = indexLeft; i <=  leftLast; i++) { 
			temp[index] = arr.get(i);
			index++;	
		}
		// copy elements in range indexRight-rightLast to temp
		for (int j = indexRight; j <= rightLast; j++ ) {
			temp[index] = arr.get(j);
			index++;

		}
		// copy temp to array starting at leftFirst
		for (int k = leftFirst; k <= rightLast; k++ ) {
			arr.set(k, temp[k-leftFirst]);	
		}
		
	}
}
		
	
	
		
		
	
	


