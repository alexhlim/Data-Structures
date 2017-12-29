
/**
 * This is a comparator class that sorts words alphabetically.
 * 
 * @author Alexander Lim
 * Data Structures, Fall 2016
 * 
 */

package project5;

import java.util.Comparator;

public class CompareWordByName implements Comparator<Word> {

	/**
	 * This is the compare method for this class. It takes in two Word objects
	 * and uses their keys as comparisons. It sorts two keys alphabetically. 
	 */
	@Override
	public int compare(Word w1, Word w2) {
		
		// store difference 
		int value = w1.getKey().compareToIgnoreCase(w2.getKey()); 
		// w1 key value > w2 key value, w1 is "larger"
		if (value > 0){
			return 1;
		}
		// w1 key value < w2 key value, w1 is "smaller"
		else {
			return -1;
		}
	}

}
