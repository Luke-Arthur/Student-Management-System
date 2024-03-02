/*______________________________________________________________
My name: Luke Moorhouse
My student number: 7603599
My course code: CSIT121 
My email address: lm678@uowmail.edu.au
Assignment number: 1
______________________________________________________________*/

// ========================================Student.java========================================

//Class Student that provides all the attributes of a student object
class Student { //opens the class

	// ======================================== member Variables========================================

	private int number;
	private String name;
	private String dob;
	private String degree;
	private String[] codes;
	private int cnt;


	// ========================================Constructor========================================
	
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

	public Student(int number, String name, String dob, String degree, String[] codes) {
		//initialises the globals 
		this.number = number;
		this.name = name;
		this.dob = dob;
		this.degree = degree;
		this.codes = codes;
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

	//getter for the student name returning type string
	public String getName() {
		return name;
	}

	//getter for the student date of birth returning type string
	public String getDob() {
		return dob;
	}

	//getter for the student degree returning type string
	public String getDegree() {
		return degree;
	}

	//getter for the student codes returning type string
	public String[] getCodes() {
		return codes;
	}

	//getter for count returning type integer
	public int getCount() {
		return cnt;
	}


	// ========================================To String Method========================================


	//the toString method used when called to print out as a string 
	@Override
	public String toString() {

		String str = "Student number: " + number + "\n" + "Name: " + name + "\n" + "Date of birth: " + dob + "\n" + "Degree: " + degree ;

		for (int i = 0; i < cnt; i++) {
			str += codes[i] + " ";
		}
		return str;
	}

}//closes the class