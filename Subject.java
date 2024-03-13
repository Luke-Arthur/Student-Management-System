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
 * The Subject class is used to create an object of type Subject. The class has 4 member variables
 * which are the subject code, the title of the subject, the number of credits and the school that offers the subject.
 * The class has a constructor which takes in the 4 member variables and sets them to the object. The class
 * also has getters for each of the member variables. The class also has a toString method which is used to
 * print out the object as a string.
 */

// ======================================== Class Subject =============================================

public class Subject {//opens class

	// ======================================== member Variables========================================
	private final String code;
	private final String title;
	private final int credit;
	private final String school;

	// ========================================Constructor==============================================
	public Subject(String code, String title, int credit, String school) {
		//initialises the globals
		this.code = code;
		this.title = title;
		this.credit = credit;
		this.school = school;
	}

	// ========================================Getters and Setters========================================

	public String getCode() {
		return code;
	}

	// ========================================ToString Method============================================

	//the toString method used when called to print out as a string 
	@Override
	public String toString() {
		return "Subject code: " + code + "\n" + "Title: " + title + "\n" + "Credits: " + credit + "\n" + "Offered by: " + school ;
	}

}//close the class
