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
    private Date date;
    private static final int MIN_AGE = 16;



    public RosterManager() {

    }

    private boolean operations() {

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
                return true;

            default:
                System.out.println(opCode + " is an invalid command!");
                break;
        }
        return true;
    }

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

        this.student = new Student(stProfile, stMajor, Integer.parseInt(this.credits));

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

    private boolean removeCommand() {

        this.date = new Date(this.dob);
        Profile stProfile = new Profile(this.lname, this.fname, date);
        this.student = new Student(stProfile, null, Integer.parseInt(this.credits));

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

    private void listCommand() {

        System.out.println("Students in " + this.major);
        roster.printMajor(this.major);
        System.out.println("End of list");

    }

    private boolean changeMajorCommand() {

        this.date = new Date(this.dob);
        Profile stProfile = new Profile(this.lname, this.fname, this.date);
        this.student = new Student(stProfile, null, Integer.parseInt(this.credits));

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

    private boolean majorError() {

        if(!(major.equalsIgnoreCase("CS") || major.equalsIgnoreCase("MATH")
                || major.equalsIgnoreCase("EE") || major.equalsIgnoreCase("ITI")
                || major.equalsIgnoreCase("BAIT"))) {

            System.out.println("Major code invalid: " + major);
            return false;
        }
        return true;
    }

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

    public void run() {

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("[\\r\\n]+");
        this.roster = new Roster();
        System.out.println("Roster Manager running...");

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
