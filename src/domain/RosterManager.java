package domain;
import java.util.Scanner;

/**
 User interface class that handles line commands entered on the console and displays the results on the console.
 @author Joshua McArdle
 */
public class RosterManager {

    private String opCode;
    private String fname;
    private String lname;
    private String dob;
    private String major;
    private String credits;
    private int numStudents;
    private Roster roster;
    private Student student;
    private Major stMajor;
    private static final int MIN_AGE = 16;



    public RosterManager() {

    }

    private void operations() {

        switch(opCode) {
            case "A":
                addCommand();
                break;

            case "R":
                removeCommand();
                break;

            case "P":
                printCommand();
                break;

            case "PS":
                printByStandingCommand();
                break;

            case "PC":
                printBySchoolMajorCommand();
                break;

            case "L":
                listCommand();
                break;

            case "C":
                changeMajorCommand();
                break;

            case "Q":
                System.out.println("Roster Manager terminated.");
                return;

            default:
                System.out.println(opCode + " is an invalid command!");
                break;
        }
    }

    private void addCommand() {

        Date today = new Date();
        Date date = new Date(this.dob);
        Profile stProfile = new Profile(this.lname, this.fname, date);

        if(date.isValid() == false) {

            if(date.equals(today) || date.compareTo(today) > 0 && date.getYear() >= today.getYear() - MIN_AGE) {
                System.out.println("DOB invalid: " + this.dob + " younger than 16 years old.");
                return;
            }
            else {
                    System.out.println("DOB invalid: " + this.dob + " not a valid calendar date!");
                    return;
            }
        }

        //Profile stProfile = new Profile(this.lname, this.fname, date);

        if(!(major.equalsIgnoreCase("CS") || major.equalsIgnoreCase("MATH")
                || major.equalsIgnoreCase("EE") || major.equalsIgnoreCase("ITI")
                || major.equalsIgnoreCase("BAIT"))) {

            System.out.println("Major code invalid: " + major);
            return;
        }
        else {

            stMajor = Major.valueOf(this.major);
        }

        try {
            Integer.parseInt(this.credits);
        }
        catch (NumberFormatException e) {
            System.out.println("Credits completed invalid: not an integer!");
            return;
        }

        if(Integer.parseInt(this.credits) < 0) {

            System.out.println("Credits completed invalid: cannot be negative!");
            return;
        }

        Student student = new Student(stProfile, stMajor, Integer.parseInt(this.credits));

        if(roster.contains(student)) {
            System.out.println(this.fname + " " + this.lname + " " + this.dob + " already in the roster.");
        }
        else{
            roster.add(student);
            //numStudents++;
            System.out.println(this.fname + " " + this.lname + " " + this.dob + " added to the roster.");
        }

    }

    private void removeCommand() {

        if(roster.remove(student) == true) {
            numStudents--;
            System.out.println(this.fname + " " + this.lname + " " + this.dob + " removed from the roster.");
        }
        else {
            System.out.println(this.fname + " " + this.lname + " " + this.dob + " not in the roster.");
        }
    }

    private void printCommand() {

        if(numStudents == 0) {

            System.out.println("Student roster is empty!");
        }
        else {
            System.out.println("* Student roster sorted by last name, first name, DOB **");
            roster.print();
            System.out.println("* end of roster **");
        }

    }

    private void printByStandingCommand() {

        if(numStudents == 0) {

            System.out.println("Student roster is empty!");
        }
        else {
            System.out.println("* Student roster sorted by standing **");
            roster.printByStanding();
            System.out.println("* end of roster **");
        }
    }

    private void printBySchoolMajorCommand() {

        if(numStudents == 0) {

            System.out.println("Student roster is empty!");
        }
        else {
            System.out.println("* Student roster sorted by school, major **");
            roster.printBySchoolMajor();
            System.out.println("* end of roster **");
        }
    }

    private void listCommand() {

        System.out.println("Prints list of students in specified major, sorted by last name, first name and DOB.");
    }

    private void changeMajorCommand() {

        Major newMajor = Major.valueOf(this.major);

        if(numStudents == 0) {

            System.out.println("Student roster is empty!");
        }

        if(roster.contains(student) == false) {

            System.out.println(this.fname + " " + this.lname + " " + this.dob + " is not in the roster.");
        }
        else {

            student.setMajor(newMajor);
            System.out.println(this.fname + " " + this.lname + " " + this.dob + " major changed to " + newMajor + ".");
        }
    }

    private void convertToTokens(String elements) {

        String [] arrOfTokens = elements.split("\\s+");

            this.opCode = arrOfTokens[0];

            if (arrOfTokens.length > 1) {
                this.fname = arrOfTokens[1];
            }
            if (arrOfTokens.length > 2) {
                this.lname = arrOfTokens[2];
            }
            if (arrOfTokens.length > 3) {
                this.dob = arrOfTokens[3];
            }
            if (arrOfTokens.length > 4) {
                this.major = arrOfTokens[4];
            }
            if (arrOfTokens.length > 5) {
                this.credits = arrOfTokens[5];
            }

    }

    public void run() {

        Scanner sc = new Scanner(System.in);
        this.roster = new Roster();
        int numStudents = 0;
        System.out.println("Roster Manager running...");

        while(sc.hasNextLine()) {

            String command = sc.nextLine();

            convertToTokens(command);

            operations();

            if(command.equals("Q")) {
                return;
            }
        }
    }
}
