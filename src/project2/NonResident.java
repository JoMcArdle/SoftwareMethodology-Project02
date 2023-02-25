package project2;

public class NonResident extends Student{

    //no additional instance variables
    public static final int MIN_CREDITS = 3;
    public static final int MIN_CREDITS_FULL_TIME = 12;
    public static final int CREDITS_FULL_TIME = 16;
    public static final int MAX_CREDITS = 24;
    public static final int CREDIT_HOUR_RATE = 966;
    public static final int TUITION_FEE = 29737;
    public static final int UNIVERSITY_FEE = 3268;
    public static final double UNIVERSITY_FEE_PART_TIME_RATE = 2614.4;

    /**
     * Empty constructor.
     */
    public NonResident() {

    }

    /**
     * Parameterized constructor, creates new NonResident student by calling super method to pass parameters to Student class.
     * @param profile, last name, first name and DOB of NonResident student.
     * @param major, the major of the NonResident student.
     * @param creditCompleted, the number of credits a NonResident student has completed.
     */
    public NonResident(Profile profile, Major major, int creditCompleted) {

        super(profile, major, creditCompleted);

    }

    /**
     * Checks if the number of credits are valid.
     * @param creditEnrolled credits that are to be checked whether they are valid or not.
     * @return false if credits are less than the minimum amount of credits or greater than the maximum amount of
     * credits and true otherwise.
     */

    public boolean isValid(int creditEnrolled) {

        return super.isValid(creditEnrolled);
    }

    /**
     * Calculates the amount of tuition due for a non-resident student.
     * @param creditsEnrolled, the number of credits a non-resident student has enrolled.
     * @return tuition amount due after all calculations have been made.
     */
    @Override
    public double tuitionDue(int creditsEnrolled) {

        double tuition = -1;

        if(creditsEnrolled >= MIN_CREDITS && creditsEnrolled < MIN_CREDITS_FULL_TIME) { // Part time

            tuition = (CREDIT_HOUR_RATE * creditsEnrolled) + (UNIVERSITY_FEE_PART_TIME_RATE);
        }
        else if(creditsEnrolled >= MIN_CREDITS_FULL_TIME && creditsEnrolled <= CREDITS_FULL_TIME) { // full time

            tuition = TUITION_FEE + UNIVERSITY_FEE;

        }
        else if(creditsEnrolled > CREDITS_FULL_TIME && creditsEnrolled <= MAX_CREDITS){ // Beyond full time

            tuition = (TUITION_FEE) + (UNIVERSITY_FEE) + (CREDIT_HOUR_RATE * (creditsEnrolled - CREDITS_FULL_TIME));
        }

        return tuition;
    }

    /**
     * Checks if resident student is a resident of NJ.
     * @return false since non-resident students are not residents of NJ.
     */

    public boolean isResident() {

        return false;
    }

    /**
     * The toString method will print a string representation of a non-resident student object
     * @return the string representation of the non-resident student
     */
    @Override
    public String toString() {

        //return getProfile() + " " + getMajor().toString() + " credits completed: " + getCreditCompleted()
               // + " (" + returnStanding(this) + ")" + " (" + "non-resident" + ")";

        return super.toString() + " (" + "non-resident" + ")";
    }

    public static void main(String[] args) {

        Profile profile = new Profile("Lopez", "Juan", new Date("10/12/1992"));

        NonResident nonResident = new NonResident(profile, Major.BAIT, 0);

        System.out.println(nonResident);

        System.out.println(nonResident.tuitionDue(12));


    }

}
