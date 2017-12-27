package project2;

public class test {

	public static void main(String[] args) {
		
		//binarySequence(3);
		System.out.println(717%10);
		System.out.println(717/10);
		System.out.println(71%10);
		System.out.println(71/10);
		System.out.println(7%10);
		System.out.println(20%7);
	}
	
	public static void binarySequence(int number){
		String sequence = new String();
		int counter = 0;
		binarySequence(number, sequence);
		
	}
	
	private static void binarySequence(int number, String sequence) {
		//int counter = 0;
		if (number == sequence.length()) {
			System.out.println(sequence);
		}
		if ((sequence.charAt(0) == '0') && ( number == number - 1) ) {
			String seq0 = sequence + "1";			
		}
		
		else {
			String seq0 = sequence + "0";
			String seq1 = sequence + "1";
			binarySequence(number, seq0);
			binarySequence(number,seq1);
			///counter++;
		}
		
	}

}
