
/**
 * This class acts as an object that will be put into the YearNames class. Each object has its own
 * name, gender, and count for a baby in a specified year. It implements the Comparable interface
 * that allows name objects to be compared with one another. 
 * 
 * @author Alexander Lim
 * Data Structures, Fall 2016
 *
 */

package project4;

public class Name implements Comparable<Name> {		

	private String name;
	private String gender;
	private int count;
	// value used in compareTo method
	private int compareNameValue;			
	// value used in compareTo method
	private int compareGenderValue;		
	
	/** 
	 *  This method is the constructor method for the Name Class. It takes in 3 parameters and checks whether 
	 *  the input is valid (no empty strings, M or F gender, and count is greater than 0).
	 * 
	 * @param name Name of the baby.
	 * 
	 * @param gender Gender of the baby.
	 * 
	 * @param count	Number of babies given this name in the given year (if name same for M and F, add them both to count)
	 * 
	 * @throws IllegalArgumentException When the user gives an invalid input, it will prevent the program from crashing
	 *  and tell the user to input something valid.
	 */
	public Name( String name, String gender, int count ) throws IllegalArgumentException {
		// flag used to determine whether input is valid or not
		boolean flag = false;										
		if (name.isEmpty() == false) 
		{
			if ( ( gender.equalsIgnoreCase("M")  == true )  || ( gender.equalsIgnoreCase("F") == true ) )
			{
					if (count > 0) 
					{
						// sets Name characteristics
						this.name = name;							
						this.gender = gender;
						this.count = count; 
						flag = true;		
					}
			} 
		}
	}
	
	/**
	 * Getter method for name variable.
	 * @return name in String format.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Setter for name variable.
	 * @param newName New name.
	 * @return The new name.
	 */
	public String setName(String newName) {
		this.name = newName;
		return this.name;
	}
	
	/**
	 * Getter method for gender variable.
	 * @return gender in String format.
	 */
	public String getGender() {
		return this.gender;
	}
	
	/**
	 * Setter method for gender variable.
	 * @param newGender New Gender.
	 * @return gender in String Format.
	 */
	public String setGender(String newGender) {
		this.gender = newGender;
		return this.gender;
	}
	
	/**
	 * Getter method for count.
	 * @return count as an integer. 
	 */
	public int getCount() {
		return this.count;
	}
	
	/**
	 * Setter method for count.
	 * @param newCount Set a new count.
	 * @return The new count as an integer.
	 */
	public int setCount(int newCount) {
		this.count = newCount;
		return newCount;
	}
	
	/**
	 * Getter method for compareNameValue.
	 * @return compareNamevalue as an integer.
	 */
	public int getCompareNameValue() {
		return compareNameValue;
	}
	
	/**
	 * Getter method for compareGenderValue.
	 * @return compareGenderValue as an integer
	 */
	public int getCompareGenderValue(){
		return compareGenderValue;
	}
	
	/**
	 * Setter method for compareNameValue.
	 * @param newSetCompareNameValue The new value for compareNameValue.
	 * @return The new value for compareNameValue.
	 */
	public int setCompareNameValue(int newSetCompareNameValue) {
		this.compareNameValue = newSetCompareNameValue;
		return newSetCompareNameValue;
	}
	
	/**
	 * Setter method for compareGenderValue.
	 * @param newSetCompareGenderValue  newCompareGenderValue.
	 * @return newCompareGenderValue.
	 */
	
	public int setCompareGenderValue(int newSetCompareGenderValue) {
		this.compareGenderValue = newSetCompareGenderValue;
		return newSetCompareGenderValue;
	}
	
	/** A name object is considered equal when name,count, and gender are identical.
	 *  @param Object s is what name is being compared to.
	 *  @return true if they are equal, false if they are not 
	 */
	public boolean equals(Object s) {
		if ( ( (Name) s).gender.equalsIgnoreCase(this.gender) && (( (Name) s ).name.equalsIgnoreCase(this.name)) && ( (Name) s).getCount() == this.getCount() ) {
			return true;
		}
		else { return false; }
	}

	/**
	 *  toString method for Name class.
	 *  @returns Name, Gender, and Count.
	 */
	public String toString() {
		return "Name: " + name + ", Gender: " + gender + ", count: " + count;				
	}
	
	/**
	 * This is the compareTo method for the name class. It compares two name objects by name
	 * as the primary key, gender as the secondary key, and count as the tertiary key.
	 */
	@Override
	public int compareTo(Name o) {			
		
		int nameDifference = this.getName().compareToIgnoreCase(o.getName());	
		// 1st key: names of two objects
		if (nameDifference == 0){
			int genderDifference = this.getGender().compareToIgnoreCase(o.getGender());
			// 2nd key: gender of two objects
			if (genderDifference == 0){
				// else: return difference of counts
				return this.getCount() - o.getCount();
			}
			else {
				return genderDifference;
			}
		}
		else {
			return nameDifference;
		}
	}

}
