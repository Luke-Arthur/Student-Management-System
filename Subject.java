/*______________________________________________________________
My name: Luke Moorhouse
My student number: 7603599
My course code: CSIT121 
My email address: lm678@uowmail.edu.au
Assignment number: 1
______________________________________________________________*/

//Class called Subject 
public class Subject {//opens class

	// ======================================== member Variables========================================
	private String code;
	private String title;
	private int credit;
	private String school;

	// ========================================Constructor========================================
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

	public String getTitle() {
		return title;
	}

	public int getCredit() {
		return credit;
	}

	public String getSchool() {
		return school;
	}


	// ========================================ToString Method========================================

	//the toString method used when called to print out as a string 
	@Override
	public String toString() {
		return "Subject code: " + code + "\n" + "Title: " + title + "\n" + "Credits: " + credit + "\n" + "Offered by: " + school ;
	}

//closes class
}
