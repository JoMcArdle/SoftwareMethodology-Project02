package project2;

public class Resident extends Student{

    private int scholarship; //this is the only instance variable, do not add more.
    public static final int MIN_CREDITS = 3;
    public static final int MIN_CREDITS_FULL_TIME = 12;
    public static final int CREDITS_FULL_TIME = 16;
    public static final int MAX_CREDITS = 24;
    public static final int CREDIT_HOUR_RATE = 404;
    public static final int TUITION_FEE = 12536;
    public static final int UNIVERSITY_FEE = 3268;
    public static final double UNIVERSITY_FEE_PART_TIME_RATE = 2614.4;

    /**
     * Empty constructor
     */
    public Resident() {

    }

    /**
     * Parameterized constructor, creates new Resident student by calling super method to pass parameters to Student class.
     * @param profile, last name, first name and DOB of resident student.
     * @param major, the major of the resident student.
     * @param creditCompleted, the number of credits a Resident student has completed.
     */
    public Resident(Profile profile, Major major, int creditCompleted) {

        super(profile, major, creditCompleted);

    }

    /**
     * Constructor that creates new Resident student with only their profile.
     * @param profile, last name, first name and DOB of resident student.
     */
    public Resident(Profile profile) {

        super(profile);
    }


    /**
     * Getter method that returns the scholarship amount.
     * @return scholarship amount.
     */
    public int getScholarship() {

        return this.scholarship;
    }

    /**
     * Setter method that changes a Resident student's scholarship amount to a specified value.
     * @param scholarship the amount that is to be set to the instance variable.
     */
    public void setScholarship(int scholarship) {

        this.scholarship = scholarship;
    }


    /**
     * Calculates the amount of tuition due for a resident student, also factors in scholarship if applicable.
     * @param creditsEnrolled, the number of credits a resident student has enrolled.
     * @return tuition amount due after all calculations have been made.
     */
    @Override
    public double tuitionDue(int creditsEnrolled) {

        double tuition = -1;

        if(creditsEnrolled >= MIN_CREDITS && creditsEnrolled < MIN_CREDITS_FULL_TIME) { // Part time

            tuition = (CREDIT_HOUR_RATE * creditsEnrolled) + (UNIVERSITY_FEE_PART_TIME_RATE);

        }
        else if(creditsEnrolled >= MIN_CREDITS_FULL_TIME && creditsEnrolled <= CREDITS_FULL_TIME){ // Full time

            tuition = TUITION_FEE + UNIVERSITY_FEE;

            if(scholarship >0) {

                tuition = tuition - scholarship;
            }

        }
        else if(creditsEnrolled > CREDITS_FULL_TIME && creditsEnrolled <= MAX_CREDITS) { //Beyond full time

            tuition = (TUITION_FEE) + (UNIVERSITY_FEE) + (CREDIT_HOUR_RATE * (creditsEnrolled - CREDITS_FULL_TIME));

            if(scholarship > 0) {

                tuition = tuition - scholarship;
            }
        }

        return tuition;
    }

    /**
     * Checks if resident student is a resident of NJ.
     * @return true since Resident students are a resident of NJ.
     */
    @Override
    public boolean isResident() {

        return true;
    }

    /**
     * The toString method will print a string representation of a resident student object
     * @return the string representation of the resident student
     */
    @Override
    public String toString() {

        //return getProfile() + " " + getMajor().toString() + " credits completed: " + getCreditCompleted()
                //+ " (" + returnStanding(this) + ")" + " (" + "resident" + ")";

        return super.toString() + " (" + "resident" + ")";
    }

    public static void main(String[] args) {

        Profile profile = new Profile("Lopez", "Juan", new Date("10/12/1992"));

        Resident resident = new Resident(profile, Major.BAIT, 90);

        System.out.println(resident);

        System.out.println(resident.tuitionDue(20));

    }

}
