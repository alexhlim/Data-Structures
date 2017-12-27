/**
 * This class is the main program. It requires the user to input two keywords into the 2 command lines of this class.
 * Then, it will calculate the total size of the potential directory that user entered. Also, depending on the second keyword,
 * it will calculate and print the 20 largest/oldest/newest files in the potential directory using the toString method defined in the FileOnDisk class.
 * The main method that calculates the size uses recursion and checks whether the potential directory is a symbolic link, directory, or file. 
 * 
 * @author Alexander Lim
 * @version 10/4/2016
 */
package project2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class DirectoryInfo {
	
	// create an ArrayList to store files
	static ArrayList<FileOnDisk> allFileOnDisks = new ArrayList<FileOnDisk>();			
	
	public static void main(String[] args) {
		
		String command1 = args[0];														
		String command2 = args[1];													
		FileOnDisk directory = new FileOnDisk(command1);								
		// stores total size of potential directory	
		long total = (long) getSize(directory);														
		System.out.println("Total space used: " + convertBytes(total) + "	Total number of files: " + allFileOnDisks.size() );			
		
		CompareFilesByDate compareDate = new CompareFilesByDate();						
		CompareFilesBySize compareSize = new CompareFilesBySize();						
		
		int size = 0;													
		if (allFileOnDisks.size() < 20) {												
			size = allFileOnDisks.size();
		}
		else if (allFileOnDisks.size() >= 20) {											
			size = 20;
		}
		
		if (command2.equalsIgnoreCase("largest")) {										
			Collections.sort(allFileOnDisks, compareSize);								
			// since ArrayList is in ascending order, reverse so largest elements will be first
			Collections.reverse(allFileOnDisks); 										
			System.out.println("Displaying 20 largest files:");
			// print out 20 largest
			for (int i = 0; i < size ;i++) {
				System.out.println(allFileOnDisks.get(i).toString());					
			}
		}
		else if (command2.equalsIgnoreCase("oldest")) {						
			Collections.sort(allFileOnDisks, compareDate);								
			System.out.println("Displaying 20 oldest files:");
			// print out 20 oldest
			for (int i = 0; i < size ;i++) {
				System.out.println(allFileOnDisks.get(i).toString());				
			}
		}
		else if (command2.equalsIgnoreCase("newest")) {							
			Collections.sort(allFileOnDisks, compareDate);					
			Collections.reverse(allFileOnDisks);					
			System.out.println("Displaying 20 newest files:");
			// print out 20 newest
			for (int i = 0; i < size;i++) {
				System.out.println(allFileOnDisks.get(i).toString());					
			}
		}
		else {
			System.err.printf("'%s' is not a valid argument. Please select a valid argument for the second command line: 'largest','oldest', or 'newest'.", command2);		// make sure second command line is correct
		}
		
	}
	
		/**
		 * This method is recursive and returns the total size of the potential directory the user inputs on the first command line.
		 * It checks if the potential directory is a symbolic link, directory or a file and adds its size to the total size.
		 * Method finishes when there are no more paths to be added. 
		 * @param potentialDirectory - from the user first command line
		 * @return total size of the potential directory
		 */
		public static double getSize(FileOnDisk potentialDirectory) {
			
			double totalSize = 0;					
			
			try {
				// check if symbolic link
				if (!potentialDirectory.getCanonicalPath().equals(potentialDirectory.getAbsolutePath())) {		
					if (potentialDirectory.isDirectory()) {
						totalSize = potentialDirectory.length() + totalSize;
						return totalSize;
					}
					if (potentialDirectory.isFile()) {															
						return totalSize;
					}
				}
			} catch (IOException e) {
				System.err.printf("Error: cannot determine path for %s", potentialDirectory);		
			}	
			// check if directory
			if (potentialDirectory.isDirectory()) {														
				totalSize = potentialDirectory.length() + totalSize;							
				String[] fileList = potentialDirectory.list();						
				for (int i = 0; i < fileList.length; i++) { 
					// create new FileOnDisk for every file
					FileOnDisk newDirectory= new FileOnDisk(potentialDirectory.getAbsolutePath() + "/" + fileList[i] );			
					totalSize = totalSize + getSize(newDirectory);									
				}
			}
			else {
				// if not symbolic link or directory, it is a file
				allFileOnDisks.add(potentialDirectory);													
				totalSize = totalSize + potentialDirectory.length();									
			}
			return totalSize;																		
			
	}
		
		// method to convert bytes into KB,MB, or GB
		public static String convertBytes(double size) {												
			String unit = "bytes";																	
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
			
			return String.format("%6.2f ", size) + unit;									
			
		}
}

