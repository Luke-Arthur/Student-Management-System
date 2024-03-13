/*______________________________________________________________
My name: Luke Moorhouse
My student number: 7603599
My course code: CSIT213
My email address: lm678@uowmail.edu.au
University of Wollongong - SWS - Autumn Session - 2024
Assignment number: 1
Subject coordinator: Dr Wei Zong
______________________________________________________________*/

// ================================== ABOUT THE CODE ==================================================

/*
 * The Student class is used to create an object of type Student. The class has 5 member variables
 * which are the student number, the name of the student, the date of birth of the student, the degree of the student
 * and an array of the subjects the student is enrolled in. The class has a constructor which takes in the 4 member variables
 * and sets them to the object. The class also has getters for each of the member variables. The class also has a toString method
 * which is used to print out the object as a string. The main difference between this class and the other classes is that this class
 * has a method to add a subject to the student's list of subjects.
 */

// ======================================== Class Student =============================================

class Student { //opens class

	// ======================================== member Variables========================================

	private final int number;
	private final String name;
	private final String dob;
	private final String degree;
	private final String[] codes;
	private int cnt;


	// ========================================Constructor==============================================
	
	//constructor that passes arguments

	public Student(int number, String name, String dob, String degree) {
		//initialises the globals
		this.number = number;
		this.name = name;
		this.dob = dob;
		this.degree = degree;
		this.codes = new String[10];
		this.cnt = 0;
	}



	// ========================================Getters and Setters========================================


	//getter for the student number returning type integer
	public int getNumber() {
		return number;
	}

	// public add code method
	public String[] addCode(String code) {
		codes[cnt] = code;
		cnt++;
		return codes;
	}



	//getter for the student codes returning type string
	public String[] getCodes() {
		return codes;
	}


	// ========================================To String Method========================================


	//the toString method used when called to print out as a string 
	@Override
	public String toString() {

		String str = "Student number: " + number + "\n" + "Name: " + name + "\n" + "Date of birth: " + dob + "\n" + "Degree: " + degree + "\n" + "Subjects: ";

		for (int i = 0; i < cnt; i++) {
			str += codes[i] + " ";
		}
		return str;
	}

}//closes the class