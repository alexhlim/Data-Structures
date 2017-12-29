
/**
 * This class extends MyBST and acts as a Binary Search Tree that stores all of the Name objects.
 * It specifically takes in Name objects and also this class implements Comparable so that 
 * YearName objects can be compared according to their year. Also, this class calculates
 * the total number of babies in a given year when given a name, the number given as a fraction
 * from the total number of babies, and prints out histogram bars for the BabyNames program.
 * 
 * @author Alexander Lim
 * Data Structures, Fall 2016
 *
 */

package project4;

public class YearNames extends MyBST<Name> implements Comparable<YearNames> {		

	private int year; 
	private double histogramBars;
	
	/**
	 * 1 parameter constructor class for the Year Class.
	 * @param year Enter in the year.
	 */
	public YearNames (int year) {
		this.year = year;
	}
	
	/**
	 * Getter method for year.
	 * @return year as a String.
	 */
	
	public int getYear() {
		return this.year;
	}
	/**
	 * Setter method for year.
	 * @param newYear Enter in the new year.
	 * @return The new year. 
	 */
	public int setYear(int newYear) {
		this.year = newYear;
		return newYear;
	}
	
	/** 
	 * Getter method for histogramBars. There is no setter because we do not 
	 * want user to change the number of histogram bars when printing.
	 * @return The number of histogramBars.
	 */
	public double getHistogramBars() {
		return this.histogramBars;
	}
	
	
	/**
	 * This method adds a Name object to the tree for a current year by
	 * using the add method from the MyBST class.
	 * @param n : Name object to be added
	 * @throws IllegalArgumentException : when name already exists
	 */
	public boolean add(Name n){
		// check if name already exists
		if (!super.contains(n)) {				
			// if not add to tree
			super.add(n);	
			return true;
		}
		// throw exception if name already exists
		else { throw new IllegalArgumentException("Name already exists in " + n.toString() +" " +  year );
		}
	}
	
	/**
	 * This is the public wrapper method for getCountByName. It takes in the name and gender
	 * and checks whether either of those parameters or null, or if an invalid parameter is given.
	 * If an invalid parameter is given it will throw an exception. Otherwise if the parameters
	 * are correct then it will make a call to the recursive  getCountByName private method.
	 * @param name : Name of baby
	 * @param gender : Gender of baby
	 * @return : number of babies with that name and gender in a given year
	 * @throws IllegalArgumentException : if name is not valid or gender is not valid
	 */
	public int getCountByName(String name, String gender) {
		// check if parameters are null
		if (name == null || gender == null){
			// throw exception if either are null
			throw new IllegalArgumentException("Please enter a valid name or gender.");
		}
		// check to see if the gender parameter is valid
		if( !( (gender.equalsIgnoreCase("m")) || (gender.equalsIgnoreCase("f")) ) ) {
			// throw exception if it is invalid 
			throw new IllegalArgumentException("Please enter a valid gender ('M' or 'F'");
		}
		// recursive call to the private getCountByname method
		int namecount = getCountByName(name, gender, super.root);
		return namecount;
		
	}

	/**
	 * This is the private recursive getCountByName method. It takes in the same parameters
	 * as the public wrapper, except it takes in a node as well to traverse the tree.
	 * It traverses through the tree until it finds the correct Name object, then it returns
	 * the count of that object
	 * @param name : Name of the baby
	 * @param gender : Gender of the baby
	 * @param root : Root to traverse the tree
	 * @return : total number of babies of a given name and gender
	 */
	private int getCountByName(String name, String gender, Node<Name> root) {
	
		// base case: return 0 if root is at bottom of tree and points to null (object not there)
		if (root == null){
			return 0;
		}
		// base case: if names are equal
		if (root.getData().getName().equalsIgnoreCase(name)){	
			// check if gender is equal, if not traverse left
			if ( root.getData().getGender().compareToIgnoreCase(gender) > 0){
				return getCountByName(name,gender,root.getLeft());
			}
			// check if gender is equal, if not traverse right
			else if (root.getData().getGender().compareToIgnoreCase(gender) < 0){
				return getCountByName(name,gender,root.getRight());
			}
			// both name and gender equal, return count of the name object
			else {
				return root.getData().getCount();
			}
		}
		// names are not equal: traverse left
		else if (root.getData().getName().compareToIgnoreCase(name) > 0){
			return getCountByName(name,gender, root.getLeft());
		}
		//else: names are not equal, traverse right
		return getCountByName(name,gender,root.getRight());	
	}
	
	/**
	 * This is the public wrapper for the getFractionByName method.
	 * It checks to see if the parameters are correct (none are null and gender is valid),
	 * then it makes a call to the private recursive method.
	 * @param name : name of the baby
	 * @param gender : gender of the baby
	 * @return : fraction of the baby name to total babies
	 * @throws IllegalArgumentException : when name is not valid or gender is not valid
	 */
	public double getFractionByName(String name, String gender){
		// check if parameters are null
		if (name == null || gender == null){
			// if so, throw exception
			throw new IllegalArgumentException("Please enter a valid name or gender.");
		}
		// check if gender is valid
		if( !((gender.equalsIgnoreCase("m")) || (gender.equalsIgnoreCase("f")) ) ) {
			// if so, throw exception
			throw new IllegalArgumentException("Please enter a valid gender ('M' or 'F'");
		}
		// private recursive call
		return getFractionByName(name, gender, super.root);	
	}
	
	/**
	 * This is the private recursive call for the getFractionByName method. It stores the number
	 * of babies with a certain name by calling the getCountByName method. Then it calculates the sum
	 * of total babies using another sum method. Finally it calculates the fraction of baby names to total
	 * babies and returns that value.
	 * @param name : name of baby to be found
	 * @param gender : gender of baby to be found
	 * @param root : node to traverse the tree
	 * @return : fraction of babies with a certain name to the total babies in a given year
	 */
	private double getFractionByName(String name, String gender, Node<Name> root){
		// call getCountByName and store to variable
		int numOfBabiesWithName = this.getCountByName(name, gender);
		// calculate total babies using sum method
		int totalBabies = sum(root);
		// calculate fraction
		double fractionOfBabies = (numOfBabiesWithName/ (double) totalBabies) * 100.00;	
		// calculate number of histogram bars to be printed
		histogramBars = Math.ceil( (numOfBabiesWithName/ (double) totalBabies * 10000) );	// uses math.ceil to round up
		// return fraction
		return fractionOfBabies;
		
	}
	/**
	 * This method takes in node and traverses through the YearNames BST to calculate
	 * the total number of babies in a given year by combining all of the Name objects counts.
	 * @param root : node to traverse the tree
	 * @return : total number of babies in a given YearNames tree
	 */
	public int sum(Node<Name> root){
		// base case: root is null, done traversing
		if (root == null){
			return 0;
		}
		// recursive case: call sum to left and right children
		return root.getData().getCount() + sum(root.getLeft()) + sum(root.getRight());
		
	}
	
	/**
	 * This method draws the histograms for each year for a baby with a particular name.
	 * @param fractionOfBabies Needs the fractionOfBabies number obtained from the getFractionByName method.
	 */
	public void drawHistogram(double fractionOfBabies) {
	for ( int i = 0; i < histogramBars; i++) {
		System.out.print("|");
	}
}

	/**
	 * CompareTo method for the YearNames class. Takes in a YearName tree
	 * and returns difference between two YearNames years'.
	 */
	@Override
	public int compareTo(YearNames year) {
		return this.getYear() - year.getYear();
	}
	
	/**
	 * toString method for YearNames class. Prints out year YearName tree.
	 */
	@Override
	public String toString(){
		// parse integer to string and returns year
		return Integer.toString(this.getYear());
	}
	
	
}
