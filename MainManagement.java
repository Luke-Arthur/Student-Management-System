import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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



    // ====================================== Main Method =============================================================
    public static void main(String[] args) {
        //creates a new instance of SES
        MainManagement manager = new MainManagement();

        //calls the menu cycle that loops the program until user input is 8
       manager.menuCycle();

    } //todo: END OF MAIN



    // ====================================== Menu Items =============================================================

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
        int number = getIntInput("");
        scan.nextLine();

        if (studentExists(number)) {
            System.out.println("The student " + number + " already exists. Cannot add a student.");
            return; // Exit the method to prevent adding a duplicate student
        }

        System.out.print("Student name: ");
        String name = getStringInput("", "Invalid input. Please enter a valid name.");

        System.out.print("Date of birth: ");
        String dob = readDate("");
        scan.nextLine();

        System.out.print("Degree: ");
        String degree = getStringInput("", "Invalid input. Please enter a valid degree.");

        System.out.print("Enrolled subjects (separated by whitespace): ");
        // Now this nextLine call will work as expected, waiting for user input
        String enrolled = scan.nextLine();
        String[] subjectCodes = enrolled.split("\\s+"); // Split the enrolled subjects by whitespace./
        Student student = new Student(number, name, dob, degree);
        for (String code : subjectCodes) {
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
        String title = getStringInput("", "Invalid input. Please enter a valid title.");


        System.out.print("Credits: ");
        int credit = getIntInput("");

            while (!(credit >= 0 && credit <= 24)) {
                System.out.println("Invalid input. Please enter a valid credit amount between 0 and 24 inclusive.");
                credit = getIntInput("");
            }

        scan.nextLine();

        System.out.print("Offered by: ");
        String offered = getStringInput("", "Invalid input. Please enter a valid school.");

        Subject subject = new Subject(code, title, credit, offered);
        if (cntSubjects < MAXNUM) {
            subjects[cntSubjects++] = subject;
        } else {
            System.out.println("Maximum number of subjects reached.");
        }

    }


    // Add an assingment for menu select 3
// Add an assignment for menu select 3
    public void menuItem3() {
        System.out.print("Subject code: ");
        String code = scan.next();
        code = code.toUpperCase();
        scan.nextLine(); // Consume newline left-over

        System.out.print("Assignment number: ");
        int number = getIntInput("");
        scan.nextLine();
        // Now that we have the actual input, we can validate it
        if (assignmentExists(code, number)) {
            System.out.println("The assignment " + number + " of the subject " + code + " already exists. Cannot add an assignment.");
            return; // Exit the method to prevent adding a duplicate assignment
        }

        while (!(number >= 1 && number <= 10)) {
            System.out.println("Invalid input. Please enter a valid assignment number between 1 and 10 inclusive.");
            number = getIntInput("");
        }

        System.out.print("Due date: ");
        String date = readDate("");
        scan.nextLine();


        System.out.print("Total worth: ");
        int mark = getIntInput("");

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
            mark = getIntInput("Please enter a valid mark: ");
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
    public boolean menuItem4() {
        System.out.print("Input a student number: ");
        int value = getIntInput("");

        for (int i = 0; i < cntStudents; i++) {
            if (students[i].getNumber() == value) {
                System.out.println(students[i].toString()); // Correctly prints the student details
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
        int value = getIntInput("");
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
    public void menuCycle() {
        boolean flag = true;
        while (flag) {
            printMenu();
            int choice = getIntInput("");

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


    // ====================================== user input and data validation ======================================


    // menut Item 1 validation
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
    public String getStringInput(String prompt, String errorMessage) {
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


    public String readDate(String prompt) {
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
