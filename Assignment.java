/*______________________________________________________________
My name: Luke Moorhouse
My student number: 7603599
My course code: CSIT213
My email address: lm678@uowmail.edu.au
University of Wollongong - SWS - Autumn Session - 2024
Assignment number: 1
Subject coordinator: Dr Wei Zong
______________________________________________________________*/

public class Assignment { //opens class

    // ======================================== member Variables========================================
    private final String code;
    private final int number;
    private final String dueDate;
    private final int totalWorth;

    // ========================================Constructor==============================================

    public Assignment(String code, int number, String dueDate, int totalWorth) {
        this.code = code;
        this.number = number;
        this.dueDate = dueDate;
        this.totalWorth = totalWorth;
    }

    // ========================================Getters and Setters========================================


    // Returns the subject code of the assignment
    public String getCode() {
        return code;
    }

    //Returns the number of the assignment
    public int getNumber() {
        return number;
    }


    //Returns the due date of the assignment
    public String getDueDate() {
        return dueDate;
    }

    //Returns the total worth of the assignment
    public int getTotalWorth() {
        return totalWorth;
    }

    // ========================================ToString Method========================================

    //the toString method used when called to print out as a string
    @Override
    public String toString() {
        return  "Subject code: "+ code + "\n" + "Assignment number: " + number + "\n" + "Due date: " + dueDate + "\n" + "Total worth: " + totalWorth;
    }

}// close the class
