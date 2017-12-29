/**
 * This class acts as a comparator class that sorts by Size. 
 * It is used in the DirectoryInfo class to sort 20 largest files.
 * 
 * @author Alexander Lim
 * Data Structures, Fall 2016
 * 
 */
package project2;

import java.util.Comparator;

public class CompareFilesBySize implements Comparator<FileOnDisk> {

	/**
	 * This method compares two FileOnDisk objects and sorts them by size in ascending order (smallest first and largest last).
	 * @param two FileOnDisk objects
	 */
	public int compare(FileOnDisk o1, FileOnDisk o2) {
		int comp = (int) (o1.length() - o2.length() );
		if (comp == 0) {
			return o1.compareTo(o2);
		}
		if (comp > 0) 
			return 1;
		else return -1;
	}

}
