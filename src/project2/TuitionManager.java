package project2;
import java.util.Scanner;
import java.io.File;

/**
 * User interface class that handles line commands entered on the console and displays the results on the console.
 * @author Joshua McArdle
 */
public class TuitionManager {

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
    private Date date;
    private static final int MIN_AGE = 16;

    /**
     * Empty constructor, used by the driver class RunProject1.
     */
    public TuitionManager() {

    }

    /**
     * Takes in the operation code from a line command and uses a switch case to perform various operations.
     * Calls different helper methods based on the operation code.
     */
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
                System.out.println("Tuition Manager terminated.");
                break;

            default:
                System.out.println(opCode + " is an invalid command!");
                break;
        }

    }

    /**
     * Helper method for operations(), adds a student to the roster.
     * @return false if date of birth is invalid, major doesn't exist, credits are invalid, or if student exists in
     * the roster already and true otherwise.
     */
    private boolean addCommand() {

        this.date = new Date(this.dob);

        if(dateError() == false) {

            return false;
        }

        Profile stProfile = new Profile(this.lname, this.fname, date);

        if(majorError() == false) {

            return false;
        }
        else {

            stMajor = Major.valueOf(this.major.toUpperCase());
        }

        if(creditsError() == false) {

            return false;
        }

        //this.student = new Student(stProfile, stMajor, Integer.parseInt(this.credits));

        if(roster.contains(student)) {
            System.out.println(this.fname + " " + this.lname + " " + this.dob + " already in the roster.");
            return false;
        }
        else{
            roster.add(student);
            numStudents++;
            System.out.println(this.fname + " " + this.lname + " " + this.dob + " added to the roster.");
        }
        return true;
    }

    /**
     * Helper method for operations(), removes a student from the roster.
     * @return false if student is not in the roster and true otherwise.
     */
    private boolean removeCommand() {

        this.date = new Date(this.dob);
        Profile stProfile = new Profile(this.lname, this.fname, date);
        //this.student = new Student(stProfile, null, Integer.parseInt(this.credits));

        if(roster.remove(student)) {
            numStudents--;
            System.out.println(this.fname + " " + this.lname + " " + this.dob + " removed from the roster.");
        }
        else {
            System.out.println(this.fname + " " + this.lname + " " + this.dob + " not in the roster.");
            return false;
        }
        return true;
    }

    /**
     * Helper method for operations(), prints out the roster of students sorted by last name, first name and DOB.
     * @return false if roster is empty and true otherwise.
     */
    private boolean printCommand() {

        if(numStudents == 0) {

            System.out.println("Student roster is empty!");
            return false;
        }
        else {
            System.out.println("* Student roster sorted by last name, first name, DOB **");
            roster.print();
            System.out.println("* end of roster **");
        }
        return true;
    }

    /**
     * Helper method for operations(), prints out the roster of students sorted by standing.
     * @return false if roster is empty and true otherwise.
     */
    private boolean printByStandingCommand() {

        if(numStudents == 0) {

            System.out.println("Student roster is empty!");
            return false;
        }
        else {
            System.out.println("* Student roster sorted by standing **");
            roster.printByStanding();
            System.out.println("* end of roster **");
        }
        return true;
    }

    /**
     * Helper method for operations(), prints out the roster of students sorted by school and major.
     * @return false if roster is empty and true otherwise.
     */
    private boolean printBySchoolMajorCommand() {
         if(numStudents == 0) {

            System.out.println("Student roster is empty!");
            return false;
        }
        else {
             System.out.println("* Student roster sorted by school, major **");
             roster.printBySchoolMajor();
             System.out.println("* end of roster **");

         }
        return true;
    }

    /**
     * Helper method for operations(), prints out the roster of students in a specified school, sorted by last name,
     * first name, and DOB.
     */
    private void listCommand() {
        if(!(this.major.equalsIgnoreCase("SAS") || this.major.equalsIgnoreCase("SOE") || this.major.equalsIgnoreCase("SC&I")
        || this.major.equalsIgnoreCase("RBS"))){
            System.out.println("School doesn't exist: " + this.major);
        }else {

            System.out.println("Students in " + this.major);
            roster.printMajor(this.major);
            System.out.println("End of list");
        }

    }

    /**
     * Helper method for operations(), changes a student's major.
     * @return false if roster is empty, student is not in the roster, or major doesn't exist and true otherwise.
     */
    private boolean changeMajorCommand() {

        this.date = new Date(this.dob);
        Profile stProfile = new Profile(this.lname, this.fname, this.date);
        //this.student = new Student(stProfile, null, Integer.parseInt(this.credits));

        if(this.numStudents == 0) {

            System.out.println("Student roster is empty!");
            return false;
        }if(majorError() == false) {

            return false;
        }if(roster.contains(student) == true) {
            this.stMajor = Major.valueOf(this.major);
            student.setMajor(stMajor);
            roster.updateMajor(student);
            System.out.println(this.fname + " " + this.lname + " " + this.dob + " major changed to "
                    + this.major + ".");

            return true;
        }

        else {
            System.out.println(this.fname + " " + this.lname + " " + this.dob + " is not in the roster.");
            return false;
        }
    }

    /**
     * Helper method for addCommand(), calls the isValid() method from Date class and prints whether a student is
     * younger than 16 years old or if the DOB is invalid.
     * @return false if student is younger than 16 years old or if DOB is invalid and true otherwise.
     */

    private boolean dateError() {

        Date today = new Date();

        if(date.isValid() == false) {

            if(date.equals(today) || date.compareTo(today) < 0 && date.getYear() >= today.getYear() - MIN_AGE) {
                System.out.println("DOB invalid: " + this.dob + " younger than 16 years old.");
                return false;
            }
            else {
                System.out.println("DOB invalid: " + this.dob + " not a valid calendar date!");
                return false;
            }
        }
        return true;
    }

    /**
     * Helper method for addCommand() and changeMajorCommand(), checks if the given major exists.
     * @return false if major is invalid and true otherwise.
     */
    private boolean majorError(){

        if(!(major.equalsIgnoreCase("CS") || major.equalsIgnoreCase("MATH")
                || major.equalsIgnoreCase("EE") || major.equalsIgnoreCase("ITI")
                || major.equalsIgnoreCase("BAIT"))) {

            System.out.println("Major code invalid: " + major);
            return false;
        }
        return true;
    }

    /**
     * Helper method for addCommand(), checks if the number of credits is valid.
     * @return false if credits is a negative number or not an integer and true otherwise.
     */
    private boolean creditsError() {

        try {
            Integer.parseInt(this.credits);
        }
        catch (NumberFormatException e) {
            System.out.println("Credits completed invalid: not an integer!");
            return false;
        }

        if(Integer.parseInt(this.credits) < 0) {

            System.out.println("Credits completed invalid: cannot be negative!");
            return false;
        }
        return true;
    }

    /**
     * Helper method for run() command, uses .split() method to grab the operation code and data tokens necessary to
     * manage the student roster.
     * @param elements the String input from line commands that is to be converted.
     */
    private void convertToTokens(String elements) {

        String [] arrOfTokens = elements.split("\\s+");

            this.opCode = arrOfTokens[0];
            if(this.opCode.equals("L")){
                this.major = arrOfTokens[1].toUpperCase();
            }else {

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

    }

    /**
     * Method for parsing input from the command line and continuously reads line commands until the user quits.
     */
    public void run() {

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("[\\r\\n]+");
        this.roster = new Roster();
        System.out.println("Tuition Manager running...");

        while(sc.hasNextLine()) {

            String command = sc.nextLine();

            convertToTokens(command);

            operations();

            if(command.equals("Q")) {

                break;
            }
        }
        sc.close();
    }

}
