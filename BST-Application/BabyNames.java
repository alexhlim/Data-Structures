
/**
 * 
 * This class is the main method for Project 4. It goes through a list of files and reads each individual line using Scanner.
 * While it is reading through the files, it creates a Name object and stores these objects into a YearNames tree. Then, the 
 * YearName objects are put into a MyBST so that it may be accessible after the files are read. Finally,
 * it prompts the user to enter in a name and gender, then it will print the percentage of a particular baby name in a particular year.
 * 
 * @throw IllegalArugmentException : when user enters in incorrect information to command line
 *
 * @author Alexander Lim
 * Data Structures, Fall 2016
 *
 */

package project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class BabyNames {

	public static void main(String[] args) {
		
		// values that store command line arguements 
		int beginYear = 0;												
		int endYear = 0;
		
		try{
			// if user does not enter in command lines, default case (1880-2015)
			if (args.length == 0) {
				beginYear = 1880;
				endYear = 2015;
			}
			// if user enters in command lines
			else {
				// parse from String to integer
				beginYear = Integer.parseInt(args[0]);
				endYear = Integer.parseInt(args[1]);
				// user can only enter in dates between 1880 and 2015
				if ( beginYear < 1880 || beginYear > 2015 ) {
					System.err.printf("%s should be between 1880 and 2015.", beginYear );
					System.exit(0);
				}
				// user can only enter in dates between 1880 and 2015
				else if ( endYear < 1880 || endYear > 2015 ) {
					System.err.printf("%s should be between 1880 and 2015.", endYear );
					System.exit(0);
				}
				// beginYear cannot be larger than endYear
				else if (beginYear > endYear){
					System.err.printf("%s should not be greater than %s.",beginYear, endYear );
					System.exit(0);
				}
			}
			
		}
		// if user enters only 1 argument in
		catch (ArrayIndexOutOfBoundsException e){
			System.err.println("Arguements could not be parsed. Enter in two command line args.");
			System.exit(0);
		}
		// catch if user enters wrong information into command line
		catch ( NumberFormatException e ){
			System.err.println("Please enter a valid range (between 1880 and 2015).");
			System.exit(0);
		}
	
		
		// create instance of MyBST to store YearName objects
		MyBST<YearNames> allYears = new MyBST<YearNames>();
		
		// for loop using parameters from command line args
		for (int j = beginYear; j <= endYear; j++ ) 													
		{
			// iterate through these files
			String fileName = "data/yob" + Integer.toString(j) + ".txt";						
			File f = new File(fileName);
	
			if (!f.canRead()) {		
				// if file cannot be read
				System.err.printf("Error:cannot read " + "data from file %s" , fileName);		
				System.exit(1);
			}
			
			Scanner inputFile = null;
			try {
				// initialize new Scanner
				inputFile = new Scanner (f);													
				
				}
			 catch (FileNotFoundException e) {
				// if file cannot be found
				System.err.printf("Error: cannot read " + "data from file %s",  fileName);		
				System.exit(1);
			}
			
			// create new YearName for every file
			YearNames year = new YearNames(j);													
			while (inputFile.hasNextLine())
			{	
				// each line of file
				String[] nameObject = new String[3];										
				String nextLine;
				nextLine = inputFile.nextLine();	
				// split line by commas
				nameObject = nextLine.split(",");
				// assign name,gender, and count
				String name = nameObject[0]; 													
				String gender = nameObject[1];
				int count = Integer.parseInt(nameObject[2]);
				// create new instance of a name
				Name n = new Name(name, gender, count);											
				// check if name exist in YearNames tree
				if (!year.contains(n)){
					// if it does not exist, add it 
					year.add(n);
				}
				else {
					System.err.printf("Name: %s and Gender: %s already exists in file. Skipping line.", name, gender);
				}
			}
			// add YearName object into MyBST						
			allYears.add(year);																	
			// close the file
			inputFile.close();																	
		
		
	}
		// once done reading files, it will print this
		System.out.println("All files have been read.");										
		// variables to store user input
		String searchName;
		String searchGender;
		boolean finish = false;																				
		while (!finish) 	
		{
			try 
			{
				// create new scanner
				Scanner sc = new Scanner(System.in);
				System.out.println("Please enter a name to search for or enter q to exit: ");
			    // store value of name
				searchName = sc.nextLine();
			    // check if user quits
				if (searchName.equalsIgnoreCase("Q") ){
			    	finish = true;
			    	System.out.println("Thank you for using this program!");
			    	// close scanner
			    	sc.close();
			    	return;
			    }
				
			    System.out.println("Please enter the gender or enter q to exit: ");
			    // store value of gender
			    searchGender = sc.nextLine();
			    
			    if (searchGender.equalsIgnoreCase("Q") ){
			    	finish = true;
			    	System.out.println("Thank you for using this program!");
			    	// close scanner
			    	sc.close();
			    	return;
			    }
			    
			    // check to see if valid gender inputed
			    if( !((searchGender.equalsIgnoreCase("m")) || (searchGender.equalsIgnoreCase("f")) ) ) {
					throw new IllegalArgumentException("Please enter a valid gender ('M' or 'F'");
				}
			    
			    else 
				{
					int totalBabies = 0;																				
					// create instance of iterator to traverse through MyBST 
					IteratorBST<YearNames> itr = allYears.iterator();
					// while there is a next line in iterator
					while (itr.hasNext())
					{
						YearNames currentYear = itr.next();
						// print year
						System.out.print(currentYear.getYear());
						// print fraction
						System.out.printf(" (" + "%.4f" + ")" + ": " , currentYear.getFractionByName(searchName, searchGender));
						// print histogram
						currentYear.drawHistogram(currentYear.getFractionByName(searchName, searchGender));
						// add to totalBabies count
						totalBabies += currentYear.getCountByName(searchName,searchGender);
						// formatting
						System.out.println();
					}
					// if no babies in all years, print this
					if (totalBabies == 0) {																				
						System.out.println("No name is found in the dataset.");
					}
				}
			} 
			// when user enters incorrect gender
			catch(IllegalArgumentException e){
				System.err.println("Please enter a valid gender: 'M' or 'F'. ");
			}

		}
		
	}
}
	
	
	
	




