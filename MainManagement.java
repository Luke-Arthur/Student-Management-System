/*______________________________________________________________
My name: Luke Moorhouse
My student number: 7603599
My course code: CSIT213
My email address: lm678@uowmail.edu.au
University of Wollongong - SWS - Autumn Session - 2024
Assignment number: 1
Subject coordinator: Dr Wei Zong
______________________________________________________________*/

// ======================================= Import statements ==========================================================

// Importing the necessary packages for the program
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

// ======================================== About the code ============================================================
/*
 * This is the MainManagement class that provides the main menu and the main method to run the program.
 * The purpose of this class is to manage the main menu and the user input as well as the data validation.
 * There are various methods that are called from the main menu to add a student, subject, assignment, find a student,
 * find a subject, find all assignments of a subject, find all assignments of a student.
 * The main method creates a new instance of MainManagement and calls the menuCycle method to run the program.
 * The menuCycle method allows the user to make a certain selection through the menu until the user inputs 0.
 * The user input is validated and the data is stored in the arrays of students, subjects and assignments.
 */

// ======================================== Class MainManagement ======================================================
public class MainManagement {

    // ======================================== member Variables ======================================================
    private static final int MAXNUM = 100;
    private final Student[] students;
    private int cntStudents;
    private final Subject[] subjects;
    private int cntSubjects;
    private final Assignment[] assignments;
    private int cntAssignments;

    // ====================================== CTOR ====================================================================

    // no args constructor
    public MainManagement() {

        // initialising standard arrays with MAXNUM
        students = new Student[MAXNUM];
        subjects = new Subject[MAXNUM];
        assignments = new Assignment[MAXNUM];
        // initialising the count of students, subjects and assignments
        cntStudents = 0;
        cntSubjects = 0;
        cntAssignments = 0;
    }


    // ====================================== Main Method =============================================================

    // main method to run the program
    public static void main(String[] args) {

        // Try to run the program and catch any exceptions
        try (Scanner scanner = new Scanner(System.in)) {
            // creates a new instance of MainManagement and calls the menuCycle method to run the program
            MainManagement manager = new MainManagement();
            // calls the menuCycle method and passes the scanner object for user input
            manager.menuCycle(scanner);
        } catch (Exception e) {
            System.out.println("An error occurred at the start of the program: " + e.getMessage());
        }
    }

    // ====================================== Menu Items =============================================================

    // prints the menu and asks for user input.
    // This is a String block - it is a multi-line string which uses the triple quotes. It negates the need for concatenation.
    public void printMenu() {
        System.out.print("""
                1. Add a student
                2. Add a subject
                3. Add an assignment
                4. Find a student
                5. Find a subject
                6. Find all assignments of a subject
                7. Find all assignments of a student
                0. Quit
                """);

        System.out.print("Input a choice: ");

    }


    // Add a new student - menu select 1
    public void menuItem1(Scanner scan) {
        System.out.print("Student number: ");
        int number = getIntInput(scan,"");
        scan.nextLine();

        if (studentExists(number)) {
            System.out.println("The student " + number + " exists. Cannot add a student");
            return; // Exit the method to prevent adding a duplicate student
        }

        System.out.print("Student name: ");
        String name = getStringInput(scan,"", "Invalid input. Please enter a valid name.");

        System.out.print("Date of birth: ");
        String dob = readDate(scan,"");
        scan.nextLine();

        System.out.print("Degree: ");
        String degree = getStringInput(scan,"", "Invalid input. Please enter a valid degree.");

        System.out.print("Enrolled subjects (Separated by whitespace): ");
        // Now this nextLine call will work as expected, waiting for user input
        String enrolled = scan.nextLine();
        // Split the enrolled subjects by whitespace and add them to the student object
        String[] subjectCodes = enrolled.split("\\s+");
        Student student = new Student(number, name, dob, degree);
        for (String code : subjectCodes) {
            // the codes that are stored in subjectCodes are added to the student object using the addCode method
            student.addCode(code);
        }
        students[cntStudents] = student;

        if (cntStudents < MAXNUM) {
            students[cntStudents++] = student;
        } else {
            System.out.println("Maximum number of students reached.");
        }

        // add a verification to check if the subject exists "The student 1234 exists. Cannot add a student."

    }



    // Add a new subject for menu select 2
    public void menuItem2(Scanner scan) {

        System.out.print("Subject code: ");
        String code = scan.next();
        code = code.toUpperCase();
        scan.nextLine();

        if (SubjectExists(code)) {
            System.out.println("The subject " + code + " exists. Cannot add a subject.");
            return; // Exit the method to prevent adding a duplicate subject
        }

        System.out.print("Subject title: ");
        String title = getStringInput(scan,"", "Invalid input. Please enter a valid title.");


        System.out.print("Credits: ");
        int credit = getIntInput(scan,"");

            while (!(credit >= 0 && credit <= 24)) {
                System.out.println("Invalid input. Please enter a valid credit amount between 0 and 24 inclusive.");
                credit = getIntInput(scan,"");
            }

        scan.nextLine();

        System.out.print("Offered by: ");
        String offered = getStringInput(scan,"", "Invalid input. Please enter a valid school.");

        Subject subject = new Subject(code, title, credit, offered);
        if (cntSubjects < MAXNUM) {
            subjects[cntSubjects++] = subject;
        } else {
            System.out.println("Maximum number of subjects reached.");
        }

    }


    // Add an assignment for menu select 3
// Add an assignment for menu select 3
    public void menuItem3(Scanner scan) {
        System.out.print("Subject code: ");
        String code = scan.next();
        code = code.toUpperCase();
        scan.nextLine(); // Consume newline left-over

        System.out.print("Assignment number: ");
        int number = getIntInput(scan,"");
        scan.nextLine();
        // Now that we have the actual input, we can validate it
        if (assignmentExists(code, number)) {
            System.out.println("The assignment " + number + " of the subject " + code + " exists. Cannot add an assignment.");
            return; // Exit the method to prevent adding a duplicate assignment
        }

        while (!(number >= 1 && number <= 10)) {
            System.out.println("Invalid input. Please enter a valid assignment number between 1 and 10 inclusive.");
            number = getIntInput(scan,"");
        }

        System.out.print("Due date: ");
        String date = readDate(scan,"");
        scan.nextLine();


        System.out.print("Total worth: ");
        int mark = getIntInput(scan,"");

        String subjectCode = code.toUpperCase();
        int currentTotal = getTotalGradeForSubject(subjectCode);

        while (mark < 0 || mark > 100 - currentTotal) {
            if (mark < 0) {
                System.out.println("Invalid mark. Mark cannot be negative.");
            } else if (mark > 100 - currentTotal) {
                System.out.println("Invalid mark. The total grade for assignment " + code + " cannot exceed 100. You have " + (100 - currentTotal) + " marks left.");
                if(currentTotal == 100) {
                    System.out.println("The total grade for assignment " + code + " has reached 100. No more assignments can be added.");
                    return;
                }
            }
            mark = getIntInput(scan,"Please enter a valid mark: ");
        }

            // Proceed to add the assignment
            Assignment assignment = new Assignment(code, number, date, mark);

            if (cntAssignments < MAXNUM) {
                assignments[cntAssignments++] = assignment;
            } else {
                System.out.println("Maximum number of assignments reached.");
            }


    }




    // finds a student and prints it out, for menu select 4
    public void menuItem4(Scanner scan) {
        System.out.print("Student number: ");
        int value = getIntInput(scan,"");

        for (int i = 0; i < cntStudents; i++) {
            if (students[i].getNumber() == value) {
                System.out.println(students[i].toString()); // Correctly prints the student details
                return; // Return true after finding and printing the student and their subjects
            }
        }

        System.out.println("Student " + value + " does not exist");
    }


    // finds a subject and prints it out, for menu select 5
    public void menuItem5(Scanner scan) {
        System.out.print("Subject code: ");
        String value = scan.next();
        String upper = value.toUpperCase();
        for (int i = 0; i < cntSubjects; i++) {
            if (subjects[i].getCode().equals(upper)) {
                System.out.println(subjects[i]);
                return;
            }
        }

        System.out.println("Subject " + upper + " does not exist");
    }

    // finds all assignments of a subject, for menu select 6
    public void menuItem6(Scanner scan) {
        System.out.print("Subject code: ");
        String value = scan.next();
        String upper = value.toUpperCase(); // Assuming you want to handle case insensitivity

        // Check if the subject exists

        for (int i = 0; i < cntSubjects; i++) {
            if (subjects[i].getCode().equalsIgnoreCase(upper)) {

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
            System.out.println("No assignment for the subject " + upper);
        }


    }


    // finds all assignments of a student, for menu select 7
    public void menuItem7(Scanner scan) {
        System.out.print("Student number: ");
        int value = getIntInput(scan ,"");
        scan.nextLine(); // Consume newline left-over

        boolean studentFound = false;
        for (int i = 0; i < cntStudents; i++) {
            if (students[i].getNumber() == value) {
                System.out.println(students[i]); // Print student details
                studentFound = true;
                String[] enrolledSubjects = students[i].getCodes(); // Get enrolled subjects for the student
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

// ====================================== Menu Cycle =============================================================

    // allows user to make a certain selection through the menu until user inputs 8
    public void menuCycle(Scanner scan) {
        boolean flag = true;
        while (flag) {
            printMenu();
            int choice = getIntInput(scan,"");

            switch (choice) {
                case 1:
                    menuItem1(scan); //calls menuItem1
                    break;
                case 2:
                    menuItem2(scan); //calls menuItem2
                    break;
                case 3:
                    menuItem3(scan); //calls menuItem3
                    break;
                case 4:
                    menuItem4(scan); //calls menuItem4
                    break;
                case 5:
                    menuItem5(scan); //calls menuItem5
                    break;
                case 6:
                   menuItem6(scan); //calls menuItem6
                    break;
                case 7:
                    menuItem7(scan); //calls menuItem7
                    break;
                case 0:
                    flag = false;
                    System.out.println("Bye!"); //prints bye
                    break; // breaks the for each loop

                // if user inputs the 8th selection an error message will appear
                default:
                    System.out.println("Incorrect choice");
                    break;
            }
        }
        scan.close();
    }


    // ====================================== user input and data validation ======================================


    // menu Item 1 validation
    public boolean studentExists(int number) {
        for (int i = 0; i < cntStudents; i++) {
            if (students[i].getNumber() == number) {
                return true; // Found an existing student
            }
        }
        return false; // No existing student found
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


    // Check if an assignment already exists
    public boolean assignmentExists(String code, int number) {
        for (int i = 0; i < cntAssignments; i++) {
            if (assignments[i].getCode().equals(code) && assignments[i].getNumber() == number) {
                return true; // Found an existing assignment
            }
        }
        return false; // No existing assignment found
    }


    public int getIntInput(Scanner scan,String prompt) {
        System.out.print(prompt);
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("Invalid input. Please enter a number.");
            System.out.print(prompt);
        }
        return scan.nextInt();
    }

    // Method for validating String input with a specific pattern (e.g., date)
    public String getStringInput(Scanner scan,String prompt, String errorMessage) {
        System.out.print(prompt);
        String input = scan.nextLine();
            while (!input.matches("^[a-zA-Z\\s]*$")
                    || input.isEmpty()){
                System.out.println(errorMessage);
                System.out.print(prompt);
                input = scan.nextLine();
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


    public String readDate(Scanner scan,String prompt) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        while (true) {
            try {
                System.out.print(prompt);
                String dateStr = scan.next();
                Date date = dateFormat.parse(dateStr);

                return dateFormat.format(date); // Return the formatted date as a String
            } catch (ParseException e) {
                System.out.println("Please enter a date in the format DD/MM/YYYY.");
            }
        }
    }

}
