import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class MainManagement {

    //global variables for the entire class
    private static final int MAXNUM = 100;
    private Student[] students;
    private int cntStudents;
    private Subject[] subjects;
    private int cntSubjects;
    private Assignment[] assignments;
    private int cntAssignments;
    private Scanner scan = new Scanner(System.in);

    // ====================================== CTOR ========================================================================

    // no args constructor
    public MainManagement() {

        // initialising standard arrays with MAXNUM
        students = new Student[MAXNUM];
        subjects = new Subject[MAXNUM];
        assignments = new Assignment[MAXNUM];
        //creates a new Array List for each

        cntStudents = 0;
        cntSubjects = 0;
        cntAssignments = 0;
    }


    // ====================================== Implement other necessary methods here ======================================

    // Driver - AKA main - used to run the program
    public static void main(String[] args) {
        //creates a new instance of SES
        MainManagement manager = new MainManagement();

        //calls the menu cycle that loops the program until user input is 8
       manager.menuCycle();

    } //todo: END OF MAIN


    // prints the menu and asks for user input
    public void printMenu() {
        System.out.println("\n"

                + "1.  Add a student\n" + "2.  Add a subject\n" + "3.  Add an assignment\n"
                + "4.  Find a student\n" + "5.  Find a subject\n" + "6.  Find all assignments of a subject\n"
                + "7.  Find all assignments of a student\n" + "0.  Quit\n");

        System.out.print("Input a choice: ");

    }

    // users input for the menu


    // Add a new student for menu select 1
    public void menuItem1() {
        System.out.print("Student number: ");
        int number = scan.nextInt();
        scan.nextLine();

        if (studentExists(number)) {
            System.out.println("The student " + number + " already exists. Cannot add a student.");
            return; // Exit the method to prevent adding a duplicate student
        }

        System.out.print("Student name: ");
        String name = scan.nextLine();

        System.out.print("Date of birth: ");
        String dob = scan.nextLine();

        System.out.print("Degree: ");
        String degree = scan.nextLine();

        System.out.print("Enrolled subjects (separated by whitespace): ");
        // Now this nextLine call will work as expected, waiting for user input
        String enrolled = scan.nextLine();
        String[] subjectCodes = enrolled.split("\\s+"); // Split the enrolled subjects by whitespace./
        Student student = new Student(number, name, dob, degree, subjectCodes);
        students[cntStudents] = student;

        if (cntStudents < MAXNUM) {
            students[cntStudents++] = student;
        } else {
            System.out.println("Maximum number of students reached.");
        }

        // add a verification to check if the subject exists "The student 1234 exists. Cannot add a student."

    }

    // menut Item 1 validation
    public boolean studentExists(int number) {
        for (int i = 0; i < cntStudents; i++) {
            if (students[i].getNumber() == number) {
                return true; // Found an existing student
            }
        }
        return false; // No existing student found
    }


    // Add a new subject for menu select 2
    public void menuItem2() {

        System.out.print("Subject code: ");
        String code = scan.next();
        code = code.toUpperCase();
        scan.nextLine();

        if (SubjectExists(code)) {
            System.out.println("The subject " + code + " already exists. Cannot add a subject.");
            return; // Exit the method to prevent adding a duplicate subject
        }

        System.out.print("Subject title: ");
        String title = scan.nextLine();

        System.out.print("Credits: ");
        String credit = scan.nextLine();

        System.out.print("Offered by: ");
        String offered = scan.nextLine();

        Subject subject = new Subject(code, title, Integer.parseInt(credit), offered);
        if (cntSubjects < MAXNUM) {
            subjects[cntSubjects++] = subject;
        } else {
            System.out.println("Maximum number of subjects reached.");
        }

    }


    // Check if a Subject already exists
    public boolean SubjectExists(String code) {
        for (int i = 0; i < cntSubjects; i++) {
            if (subjects[i].getCode().equals(code)) {
                return true; // Found an existing subject
            }
        }
        return false; // No existing subject found
    }


    // Add an assingment for menu select 3
// Add an assignment for menu select 3
    public void menuItem3() {
        System.out.print("Subject code: ");
        String code = scan.next();
        code = code.toUpperCase();
        scan.nextLine(); // Consume newline left-over

        System.out.print("Assignment number: ");
        int number = Integer.parseInt(scan.nextLine()); // Parse the integer after reading the full line

        // Now that we have the actual input, we can validate it
        if (assignmentExists(code, number)) {
            System.out.println("The assignment " + number + " of the subject " + code + " already exists. Cannot add an assignment.");
            return; // Exit the method to prevent adding a duplicate assignment
        }

        System.out.print("Due date: ");
        String date = scan.nextLine();

        System.out.print("Total worth: ");
        int mark = getIntInput("");

        String subjectCode = code.toUpperCase();
        int currentTotal = getTotalGradeForSubject(subjectCode);
        if (mark < 0 || mark > 100 - currentTotal) {
            System.out.println("Invalid mark. The total grade for " + subjectCode + " assignments cannot exceed 100.");
            currentTotal = 100 - currentTotal;
            System.out.println("the remaining total worth is " + currentTotal + ". Please enter a valid mark.");
        } else {
            // Proceed to add the assignment

            Assignment assignment = new Assignment(code, number, date, mark);

            if (cntAssignments < MAXNUM) {
                assignments[cntAssignments++] = assignment;
            } else {
                System.out.println("Maximum number of assignments reached.");
            }

        }
    }

    // Check if an assignment already exists
    public boolean assignmentExists(String code, int number) {
        for (int i = 0; i < cntAssignments; i++) {
            if (assignments[i].getCode().equals(code) && assignments[i].getNumber() == number) {
                return true; // Found an existing assignment
            }
        }
        return false; // No existing assignment found
    }


    // finds a student and prints it out, for menu select 4
    public boolean menuItem4() {
        System.out.print("Input a student number: ");
        int value = scan.nextInt();

        for (int i = 0; i < cntStudents; i++) {
            if (students[i].getNumber() == value) {
                System.out.println(students[i].toString()); // Correctly prints the student details
                // Now print the subjects for this found student
                String[] codes = students[i].getCodes();
                System.out.print("Subjects: ");
                for (String code : codes) {
                    if (code != null) {
                        System.out.print(code + " "); // Print each enrolled subject code
                    }
                }
                System.out.println();
                return true; // Return true after finding and printing the student and their subjects
            }
        }

        System.out.println("The student " + value + " does not exist");
        return false;
    }


    // finds a subject and prints it out, for menu select 5
    public void menuItem5() {
        System.out.print("subject code: ");
        String value = scan.next();
        String upper = value.toUpperCase();
        for (int i = 0; i < cntSubjects; i++) {
            if (subjects[i].getCode().equals(upper)) {
                System.out.println(subjects[i]);
                return;
            }
        }

        System.out.println("the subject " + upper + " does not exist");
    }

    // finds all assignments of a subject, for menu select 6
    public void menuItem6() {
        System.out.print("subject code: ");
        String value = scan.next();
        String upper = value.toUpperCase(); // Assuming you want to handle case insensitivity

        // Check if the subject exists
        boolean subjectExists = false;
        for (int i = 0; i < cntSubjects; i++) {
            if (subjects[i].getCode().equalsIgnoreCase(upper)) {
                subjectExists = true;
                break;
            }
        }

        // Find and display all assignments for the subject
        boolean foundAssignments = false;
        for (int i = 0; i < cntAssignments; i++) {
            if (assignments[i] != null && assignments[i].getCode().equalsIgnoreCase(upper)) {
                System.out.println(assignments[i]);
                foundAssignments = true;
            }
        }

        if (!foundAssignments) {
            System.out.println("No assignments found for the subject " + upper + ".");
        }
    }


    // finds all assignments of a student, for menu select 7
    public void menuItem7() {
        System.out.print("Student number: ");
        int value = scan.nextInt();
        scan.nextLine(); // Consume newline left-over

        boolean studentFound = false;
        for (int i = 0; i < cntStudents; i++) {
            if (students[i].getNumber() == value) {
                System.out.println(students[i]); // Print student details
                studentFound = true;
                String[] enrolledSubjects = students[i].getCodes(); // Get enrolled subjects for the student
                System.out.print("Subjects: ");
                for (String subjectCode : enrolledSubjects) {
                    if (subjectCode != null) {
                        System.out.print(subjectCode + " "); // Print each enrolled subject code
                    }
                }
                System.out.println();
                for (String subjectCode : enrolledSubjects) {
                    if (subjectCode != null) {
                        // Find the subject by code and print details
                        for (int j = 0; j < cntSubjects; j++) {
                            if (subjects[j].getCode().equals(subjectCode)) {
                                System.out.println("Subject code: " + subjects[j].getCode());
                                // Now print assignments for this subject
                                for (int k = 0; k < cntAssignments; k++) {
                                    if (assignments[k].getCode().equals(subjectCode)) {
                                        System.out.println("Assignment number: " + assignments[k].getNumber());
                                        System.out.println("Due date: " + assignments[k].getDueDate());
                                        System.out.println("Total worth: " + assignments[k].getTotalWorth());
                                    }
                                }
                            }
                        }
                    }
                }
                break; // Break after processing the found student
            }
        }

        if (!studentFound) {
            System.out.println("No student found with the number " + value + ".");
        }
    }



    // allows user to make a certain selection through the menu until user inputs 8
    public void menuCycle() {
        boolean flag = true;
        while (flag) {
            printMenu();
            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    menuItem1(); //calls menuItem1
                    break;
                case 2:
                    menuItem2(); //calls menuItem2
                    break;
                case 3:
                    menuItem3(); //calls menuItem3
                    break;
                case 4:
                    menuItem4(); //calls menuItem4
                    break;
                case 5:
                    menuItem5(); //calls menuItem5
                    break;
                case 6:
                   menuItem6(); //calls menuItem6
                    break;
                case 7:
                    menuItem7(); //calls menuItem7
                    break;
                case 0:
                    flag = false;
                    System.out.println("Bye!"); //prints bye
                    break; // breaks the for each loop
                // if user inputs outside of the 8 selections an error message will appear
                default:
                    System.out.println("Incorrect choice, please try again..");
                    break;
            }
        }
        scan.close();
    }


    // ====================================== user input validation ======================================

    // Method for validating integer input
    public int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("Invalid input. Please enter a number.");
            System.out.print(prompt);
        }
        return scan.nextInt();
    }

    // Method for validating String input with a specific pattern (e.g., date)
    public String getStringInput(String prompt, String pattern, String errorMessage) {
        System.out.print(prompt);
        String input = scan.next();
            while (!input.matches(pattern)) {
                System.out.println(errorMessage);
                System.out.print(prompt);
                input = scan.next();
            }
        return input;
    }


    // Method to calculate the total grade of assignments for a subject
    public int getTotalGradeForSubject(String subjectCode) {
        int total = 0;
        for (int i = 0; i < cntAssignments; i++) {
            if (assignments[i] != null && assignments[i].getCode().equalsIgnoreCase(subjectCode)) {
                total += assignments[i].getTotalWorth();
            }
        }
        return total;
    }


}
