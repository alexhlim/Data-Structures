/** 
 * This class creates FileOnDisk objects, which essentially will store a path to either a symbolic link, directory or file.
 * It also inherits from the File class and makes use of its methods. 
 * 
 * @author Alexander Lim
 * Data Structures, Fall 2016
 * 
 */

package project2;

import java.io.File;
import java.text.SimpleDateFormat;

public class FileOnDisk extends File  {

	/** 
	 * This is the constructor method for FileOnDisk. It takes a string that is a potential directory. 
	 * It calls the File class constructor to create the FileOnDisk object.
	 * @param potentialDirectory is an path to access some file, directory or symbolic link in your computer.
	 */
	public FileOnDisk(String potentialDirectory) {
		super(potentialDirectory);
	}
	
	/**
	 * This is the toString method inherited from the File class. 
	 * It will also convert bytes into kilobytes, megabytes, or gigabytes, depending on the size.
	 * @return It will print out the size of the path, the date last modified, and the actual path to the file,symbolic link or directory.
	 * 
	 */
	public String toString() {
		
		double size = this.length();
		// default case in bytes
		String unit = "bytes";								
		SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("dd/MM/YY HH:mm:ss");	
		
		// convert to KB
		if (size >= 1024 && size < (Math.pow(1024, 2))) { 				
			size = (size/1024);
			unit = "KB"; 
			}
		// convert to MB
		else if (size >= Math.pow(1024, 2) && size < Math.pow(1024, 3)) {	
			size = (size/( Math.pow(1024, 2) ) );
			unit = "MB";
		}
		// convert to GB
		else if (size >= Math.pow(1024, 3) ) {							
			size = (size/ ( Math.pow(1024, 3) ) );  
			unit = "GB";
		}
		
		String absolutePath = this.getAbsolutePath();						
		return String.format("%6.2f ", size) + " " + unit				
				+ "      " + simpleDateFormat.format(this.lastModified()) + "   " + absolutePath; 
		
	}
	

}
