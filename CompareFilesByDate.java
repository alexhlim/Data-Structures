/**
 * This class acts as a comparator class that sorts by Date. 
 * It is used in the DirectoryInfo class to sort 20 oldest and 20 newest files.
 * 
 * @author Alexander Lim
 * Data Structures, Fall 2016
 * 
 */
package project2;

import java.util.Comparator;

public class CompareFilesByDate implements Comparator<FileOnDisk>  {

	/**
	 * This method compares two FileOnDisk objects and sorts them by date in ascending order (oldest first and newest last).
	 * @param two FileOnDisk objects
	 */
	public int compare(FileOnDisk o1, FileOnDisk o2) {
		long comp = o1.lastModified() - o2.lastModified();
		if (comp == 0) {
			return o1.compareTo(o2);
		}
		if (comp > 0) {
			return 1;
		}
		else return -1;
	}

}
