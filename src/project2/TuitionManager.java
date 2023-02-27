package project2;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.text.DecimalFormat;

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
    private String creditsEnrolled;
    private String state;
    private String scholarship;
    private boolean studyAbroad;
    private int numStudents;
    private int numEnrolledStudents;
    private Roster roster;
    private Student student;
    private EnrollStudent enrollStudent;
    private Enrollment enrollList;
    private Major stMajor;
    private Date date;
    private String file;
    private String[] arrOfTokens;
    private static final int MIN_AGE = 16;
    private static final int MIN_CREDITS_FULL_TIME = 12;
    private static final int MAX_SCHOLARSHIP_AMOUNT = 10000;
    private static final int MIN_SCHOLARSHIP_AMOUNT = 1;
    private static final int NUM_CREDITS_FOR_GRADUATION = 120;
    private static final int SUB_BY_THREE = 3;

    /**
     * Empty constructor, used by the driver class RunProject1.
     */
    public TuitionManager() {

    }

    /**
     * Takes in the operation code from a line command and uses a switch case to perform various operations.
     * Calls different helper methods based on the operation code.
     */
    private void operations() throws FileNotFoundException {

        switch (opCode) {
            case "LS" -> loadStudentCommand();
            case "E" -> enrollCommand();
            case "D" -> dropCommand();
            case "S" -> scholarshipCommand();
            case "PE" -> printEnrollCommand();
            case "PT" -> printTuitionCommand();
            case "SE" -> semesterEndCommand();
            case "AR", "AN", "AT", "AI" -> addCommand();
            case "R" -> removeCommand();
            case "P" -> printCommand();
            case "PS" -> printByStandingCommand();
            case "PC" -> printBySchoolMajorCommand();
            case "L" -> listCommand();
            case "C" -> changeMajorCommand();
            case "Q" -> System.out.println("Tuition Manager terminated.");
            default -> System.out.println(opCode + " is an invalid command!");
        }

    }



    //region Commands for operations() method
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Helper method for operations(), loads the student roster from an external file.
     * @throws FileNotFoundException
     */
    private void loadStudentCommand() throws FileNotFoundException {

        Scanner studentList = new Scanner(new File(this.file));

        while(studentList.hasNextLine()) {

            String command = studentList.nextLine();
            convertToTokens(command);
            this.date = new Date(this.dob);
            Profile stProfile = new Profile(this.lname, this.fname, date);
            stMajor = Major.valueOf(this.major.toUpperCase());
            createStudentType(stProfile);
            roster.add(student);
            numStudents++;

        }
        System.out.println("Students loaded to the roster.");
    }

    /**
     * Helper method for operations(), enrolls a student with a number of credits and adds them to the enrollment list.
     * @return
     */
    private boolean enrollCommand() {

        if(dateError() == false) {

            return false;
        }

        Profile stProfile = new Profile(this.lname, this.fname, date);

        if(creditsEnrolledError() == false) {

            return false;
        }

        this.enrollStudent = new EnrollStudent(stProfile,
                Integer.parseInt(this.creditsEnrolled));

        this.student = new Resident(stProfile, Major.CS, 0, 0);
        this.student = roster.returnStudent(this.student);

        if(!(roster.contains(student))) {
            System.out.println("Cannot enroll: " + this.fname + " " + this.lname + " " +
                    this.dob + " is not in the roster.");
            return false;
        }
        else if(student.isValid(Integer.parseInt(creditsEnrolled)) == false) {
            System.out.println(studentType(student) + " " + creditsEnrolled + ": invalid credit hours.");
            return false;
        }
        else {
            enrollList.add(enrollStudent);
            System.out.println(this.fname + " " + this.lname + " " + this.dob + " "
                    + "enrolled " + Integer.parseInt(this.creditsEnrolled)
                    + " credits.");
            numEnrolledStudents++;
        }
        return true;
    }

    /**
     * Helper method for operations(), drops a student from the enrollment list.
     * @return
     */
    private boolean dropCommand() {


        this.date = new Date(this.dob);
        Profile stProfile = new Profile(this.lname, this.fname, date);
        this.enrollStudent = new EnrollStudent(stProfile);

        if(enrollList.remove(enrollStudent)) {
            numEnrolledStudents--;
            System.out.println(this.fname + " " + this.lname + " " + this.dob
                    + " dropped.");
        }
        else {
            System.out.println(this.fname + " " + this.lname + " " + this.dob
                    + " is not enrolled.");
            return false;
        }
        return true;
    }

    /**
     * Helper method for operations(), awards a scholarship to a resident student.
     * @return
     */
    private boolean scholarshipCommand() {

        if(dateError() == false) {

            return false;
        }

        this.date = new Date(this.dob);
        Profile stProfile = new Profile(this.lname, this.fname, date);
        this.student = new Resident(stProfile);
        this.student = roster.returnStudent(this.student);
        this.enrollStudent = new EnrollStudent(stProfile);
        this.enrollStudent = enrollList.returnEnrollStudent(this.enrollStudent);

        if(checkResidency() == false) {

            return false;
        }

        return true;
    }

    /**
     * Helper method for operations(), prints out the current enrollment list, based on their order in the array.
     * @return
     */
    private boolean printEnrollCommand() {

        if(numEnrolledStudents == 0) {

            System.out.println("Enrollment is empty!");
            return false;
        }
        else {

            System.out.println("** Enrollment **");
            enrollList.print();
            System.out.println("* end of enrollment *");

        }
        return true;
    }

    /**
     * Helper method for operations(), prints out the tuition due based on credits enrolled, with the order in the enrollment array.
     * @return
     */
    private boolean printTuitionCommand() {

        if(numEnrolledStudents == 0) {

            System.out.println("Student roster is empty!");

            return false;
        }

        System.out.println("** Tuition due **");

        for (int i = 0; i < numEnrolledStudents - SUB_BY_THREE; i++) {

            enrollStudent = enrollList.returnEnrollStudent(i);

            this.student = new Resident(enrollStudent.getProfile(), Major.CS, 0, 0);

            student = roster.returnStudent(student);

            printTuitionByStudentType();

        }

        System.out.println("* end of tuition due *");
        return true;
    }

    /**
     * Helper method for operations(), adds the enrolled credits to the credit completed in the roster and prints out the students who
     * have already completed 120 credits or more.
     * @return
     */
    private boolean semesterEndCommand() {

        for(int i = 0; i < numEnrolledStudents - SUB_BY_THREE; i++) {

            enrollStudent = enrollList.returnEnrollStudent(i);
            this.student = new Resident(enrollStudent.getProfile(), Major.CS, 0, 0);
            student = roster.returnStudent(student);
            student.setCreditCompleted(student.getCreditCompleted() + enrollStudent.getCreditsEnrolled());

        }
        System.out.println("Credit completed has been updated.");

        System.out.println("** list of students eligible for graduation **");

        for(int i = 0; i < numEnrolledStudents - SUB_BY_THREE; i++) {

            enrollStudent = enrollList.returnEnrollStudent(i);
            this.student = new Resident(enrollStudent.getProfile(), Major.CS, 0, 0);
            student = roster.returnStudent(student);

            if(student.getCreditCompleted() >= NUM_CREDITS_FOR_GRADUATION) {

                System.out.println(student);
            }
        }
        return true;
    }

    /**
     * Helper method for operations(), adds a student to the roster.
     * @return false if date of birth is invalid, major doesn't exist, credits are invalid, or if student exists in
     * the roster already and true otherwise.
     */
    private boolean addCommand() {

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

        createStudentType(stProfile);

        if(opCode.equals("AT") && stateError() == false) {

            return false;
        }

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
        this.student = new Resident(stProfile);

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
        if(!(this.major.equalsIgnoreCase("SAS") || this.major.equalsIgnoreCase("SOE")
                || this.major.equalsIgnoreCase("SC&I") || this.major.equalsIgnoreCase("RBS"))) {

            System.out.println("School doesn't exist: " + this.major);

        }
        else {

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
        this.student = new Resident(stProfile);

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
    //------------------------------------------------------------------------------------------------------------------
    //endregion




    //region Helper methods for the Commands in operations().
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Helper method for addCommand(), calls the isValid() method from Date class and prints whether a student is
     * younger than 16 years old or if the DOB is invalid.
     * @return false if dob is null or student is younger than 16 years old or if DOB is invalid and true otherwise.
     */

    private boolean dateError() {

        if(this.dob == null) {
            System.out.println("Missing data in command line.");
            return false;
        }

        this.date = new Date(this.dob);

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

        if(major == null) {
            System.out.println("Missing data in command line.");
            return false;
        }

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

        if(this.credits == null) {

            System.out.println("Missing data in command line.");
            return false;
        }

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
     * Helper method for enrollCommand(), checks if the credits enrolled are valid.
     * @return false if credits enrolled are null or not an integer and true otherwise.
     */
    private boolean creditsEnrolledError() {

        if(this.creditsEnrolled == null) {

            System.out.println("Missing data in command line.");
            return false;
        }

        try {
            Integer.parseInt(this.creditsEnrolled);
        }
        catch (NumberFormatException e) {
            System.out.println("Credits enrolled is not an integer.");
            return false;
        }

        return true;
    }

    /**
     * Helper method for addCommand(), checks if a Tri-state student's state is valid.
     * @return false if state is null or an invalid state.
     */
    private boolean stateError() {

        if(this.state == null) {

            System.out.println("Missing the state code.");
            return false;
        }

        if(!(state.equalsIgnoreCase("NY") || state.equalsIgnoreCase("CT"))) {

            System.out.println(state + ": Invalid state code.");
            return false;
        }
        return true;
    }


    /**
     * Helper method for scholarshipCommand(), checks if a scholarship is null or valid.
     * @return false if null or not an integer or an invalid amount and true otherwise.
     */
    private boolean scholarshipError() {

        if(this.scholarship == null) {

            System.out.println("Missing data from command line.");
            return false;
        }

        try {
            Integer.parseInt(this.scholarship);
        }
        catch (NumberFormatException e) {
            System.out.println("Amount is not an integer.");
            return false;
        }

        if(Integer.parseInt(this.scholarship) < MIN_SCHOLARSHIP_AMOUNT ||
                Integer.parseInt(this.scholarship) > MAX_SCHOLARSHIP_AMOUNT ) {

            System.out.println(this.scholarship + ": invalid amount.");
            return false;
        }
        return true;
    }

    /**
     * Helper method for scholarshipCommand(), checks if a student is a resident or not and updates their scholarship
     * accordingly.
     * @return false if student is not in the roster or is not a resident or is a part-time student and true otherwise.
     */
    private boolean checkResidency() {

        if(!(roster.contains(student))) {

            System.out.println(this.fname + " " + this.lname + " " + this.dob
                    + " is not in the roster.");
            return false;
        }
        else if(student.isResident() == false) {

            System.out.println(this.fname + " " + this.lname + " " + this.dob
                    + " is not eligible for the scholarship.");
            return false;
        }

        if(student.isResident()) {

            if(enrollStudent.getCreditsEnrolled() < MIN_CREDITS_FULL_TIME) {

                System.out.println(this.fname + " " + this.lname + " " + this.dob
                        + " part time student is not eligible for the scholarship.");
                return false;
            }
            else if(scholarshipError() == false) {

                return false;
            }
            else {
                ((Resident)student).setScholarship(Integer.parseInt(this.scholarship));
                System.out.println(this.fname + " " + this.lname + " " + this.dob
                        + ": scholarship amount updated.");
            }
        }
        return true;
    }

    /**
     * Helper method for addCommand(), checks to see the type of student to be added and creates an instance of that student.
     * @param profile, the profile of the student to be added.
     */
    private void createStudentType(Profile profile) {

        if(opCode.equals("AR") || opCode.equals("R")) {

            this.student = new Resident(profile, stMajor, Integer.parseInt(this.credits),0);

            this.student = new Resident(profile, stMajor, Integer.parseInt(this.credits), 0);

        }
        else if(opCode.equals("AN") || opCode.equals("N")) {

            this.student = new NonResident(profile, stMajor, Integer.parseInt(this.credits));
        }
        else if(opCode.equals("AT") || opCode.equals("T")) {

            this.student = new TriState(profile, stMajor, Integer.parseInt(this.credits), this.state);
        }
        else {

            this.student = new International(profile, stMajor, Integer.parseInt(this.credits), this.studyAbroad);
        }

    }

    /**
     * Helper method that returns a String, indicating a given student's type.
     * @param student, the student to determine what type of student they are.
     * @return a string value indicating what type of student the given student is.
     */
    private String studentType(Student student) {

        if (student instanceof Resident residentStudent) {
            return "(Resident)";
        }
        if (student instanceof NonResident nonResidentStudent) {
            if (nonResidentStudent instanceof TriState triStateStudent) {
                return "(Tri-state)";
            }
            else if(nonResidentStudent instanceof International internationalStudent) {
                if(internationalStudent.getIsStudyAbroad() == true) {
                    return "(International student study abroad)";
                }
                else {
                    return "(International student)";
                }
            }
            else {
                return "(Non-Resident)";
            }
        }
        return "";
    }

    /**
     * Helper method for printTuitionCommand(), prints out the tuition based on the student's type.
     */
    private void printTuitionByStudentType() {

        DecimalFormat df = new DecimalFormat("#,###.00");

        if (student instanceof Resident residentStudent) {
            System.out.println(residentStudent.getProfile() + " (Resident) " + "enrolled "
                    + enrollStudent.getCreditsEnrolled() + " credits: " + "tuition due: $"
                    + df.format(residentStudent.tuitionDue(enrollStudent.getCreditsEnrolled())));

        } else if (student instanceof NonResident nonResidentStudent) {

            if (nonResidentStudent instanceof TriState triStateStudent) {
                System.out.println(triStateStudent.getProfile() + " (Tri-state " + triStateStudent.getState().toUpperCase()
                        + ")" + " enrolled " + enrollStudent.getCreditsEnrolled() + " credits: " + "tuition due: $"
                        + df.format(triStateStudent.tuitionDue(enrollStudent.getCreditsEnrolled())));
            } else if (nonResidentStudent instanceof International internationalStudent) {

                if (internationalStudent.getIsStudyAbroad() == true) {
                    System.out.println(internationalStudent.getProfile() + " (International student study abroad) "
                            + "enrolled " + enrollStudent.getCreditsEnrolled() + " credits: " + "tuition due: $"
                            + df.format(internationalStudent.tuitionDue(enrollStudent.getCreditsEnrolled())));

                } else {
                    System.out.println(internationalStudent.getProfile() + " (International student) " + "enrolled "
                            + enrollStudent.getCreditsEnrolled() + " credits: " + "tuition due: $"
                            + df.format(internationalStudent.tuitionDue(enrollStudent.getCreditsEnrolled())));
                }
            } else {
                System.out.println(nonResidentStudent.getProfile() + " (Non-Resident) " + "enrolled "
                        + enrollStudent.getCreditsEnrolled() + " credits: " + "tuition due: $"
                        + df.format(nonResidentStudent.tuitionDue(enrollStudent.getCreditsEnrolled())));
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //endregion




    //region Helper methods for the run() command
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Helper method for convertToTokens() method, sets the necessary tokens to the instance
     * variables involved with enrolling a student.
     */
    private void enrollmentTokens() {

        if (arrOfTokens.length > 1) {
            this.fname = arrOfTokens[1];
        }
        if (arrOfTokens.length > 2) {
            this.lname = arrOfTokens[2];
        }
        if (arrOfTokens.length > 3) {
            this.dob = arrOfTokens[3];
        }
        else {
            this.dob = null;
        }
        if (arrOfTokens.length > 4) {
            this.creditsEnrolled = arrOfTokens[4];
        }
        else {
            this.creditsEnrolled = null;
        }
    }

    /**
     * Helper method for convertToTokens() method, sets the necessary tokens to the instance
     * variables involved with awarding a scholarship to a resident student.
     */
    private void scholarshipTokens() {

        if (arrOfTokens.length > 1) {
            this.fname = arrOfTokens[1];
        }
        if (arrOfTokens.length > 2) {
            this.lname = arrOfTokens[2];
        }
        if (arrOfTokens.length > 3) {
            this.dob = arrOfTokens[3];
        }
        else {
            this.dob = null;
        }
        if (arrOfTokens.length > 4) {
            this.scholarship = arrOfTokens[4];
        }
        else {
            this.scholarship = null;
        }

    }

    /**
     * Helper method for convertToTokens() method, sets the necessary tokens to the instance variables based on special
     * opCode cases.
     */
    private void convertSpecialTokenCases() {

        if(this.opCode.equals("L")){
            this.major = arrOfTokens[1].toUpperCase();
        }
        else if (this.opCode.equals("LS")) {
            this.file = arrOfTokens[1];
        }
        else if (this.opCode.equals("E")) {
            enrollmentTokens();
        }

        else if(this.opCode.equals("S")) {
            scholarshipTokens();
        }

    }
    /**
     * Helper method for run() command, uses .split() method to grab the operation code and data tokens necessary to
     * manage the student roster.
     */
    private void convertToTokens(String tokens) {

        this.arrOfTokens = tokens.split("\\s+|,");

        this.opCode = arrOfTokens[0];

        convertSpecialTokenCases();

        if (arrOfTokens.length > 1) {
            this.fname = arrOfTokens[1];
        }
        if (arrOfTokens.length > 2) {
            this.lname = arrOfTokens[2];
        }
        if (arrOfTokens.length > 3) {
            this.dob = arrOfTokens[3];
        }
        else {
            this.dob = null;
        }
        if (arrOfTokens.length > 4) {
            this.major = arrOfTokens[4];
        }
        if (arrOfTokens.length > 5) {
            this.credits = arrOfTokens[5];
        }
        else {
            this.credits = null;
        }
        if (arrOfTokens.length > 6 && (opCode.equals("AT") || opCode.equals("T"))) {
            this.state = arrOfTokens[6];
        }
        else {
            this.state = null;
        }
        if (arrOfTokens.length > 6 && (opCode.equals("AI") || opCode.equals("I"))) {

            this.studyAbroad = Boolean.parseBoolean(arrOfTokens[6]);

        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //endregion

    /**
     * Method for parsing input from the command line and continuously reads line commands until the user quits.
     */
    public void run() throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("[\\r\\n]+");
        this.roster = new Roster();
        this.enrollList = new Enrollment();
        System.out.println("Tuition Manager running...");

        while(sc.hasNextLine()) {

            String command = sc.nextLine();

            if(command.length() == 0) {
                continue;
            }

            convertToTokens(command);

            operations();

            if(command.equals("Q")) {

                break;
            }
        }
        sc.close();
    }

}
