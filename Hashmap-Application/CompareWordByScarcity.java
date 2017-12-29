
/**
 * This is a comparator class that compares words by their scarcity(how infrequently they appear).
 * 
 * @author Alexander Lim
 * Data Structures, Fall 2016
 */

package project5;

import java.util.Comparator;

public class CompareWordByScarcity implements Comparator<Word> {

	/**
	 * This is the compare method for this class. It compares the frequencies
	 * of two word objects. If one Word's frequency is smaller than the other
	 * it is considered larger. If two Words have the same frequency, then they
	 * are compared alphabetically. 
	 */
	@Override
	public int compare(Word w1, Word w2) {
		// store value of difference
		int value = ( w1.getFreq() ) - ( w2.getFreq() );
		if (value == 0){
			// secondary comparison
			if ( w1.getKey().compareToIgnoreCase(w2.getKey()) > 0) {
				return 1;
			}
			else if (w1.getKey().compareToIgnoreCase(w2.getKey()) < 0) {
				return -1;
			}
			else {
				return 0;
			}
		}
		// if w1 frequency > w2 frequency, w2 is "larger" 
		if (value > 0){			
			return 1;
		}
		// if w1 frequency < w2 frequency, w2 is "smaller" 
		else {
			return -1;
		}
	}


}
