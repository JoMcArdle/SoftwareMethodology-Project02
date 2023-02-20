package project2;

public class TriState extends NonResident{

    private String state; //only instance variable, do not add more.
    private static final int NY_TUITION_DISCOUNT = 4000;
    private static final int CT_TUITION_DISCOUNT = 5000;

    /**
     * Empty constructor.
     */
    public TriState() {

    }

    /**
     * Parameterized constructor, creates new Tristate student by calling super method to pass parameters to Student class.
     * @param profile, last name, first name and DOB of TriState student.
     * @param major, the major of the TriState student.
     * @param creditCompleted, the number of credits a TriState student has completed.
     * @param state, the state where the student is from.
     */
    public TriState(Profile profile, Major major, int creditCompleted, String state) {

        super(profile, major, creditCompleted);
        this.state = state;

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
     * Calculates the amount of tuition due for a tri-state student.
     * @param creditsEnrolled, the number of credits a tri-state student has enrolled.
     * @return tuition amount due after all calculations have been made.
     */
    @Override
    public double tuitionDue(int creditsEnrolled) {

        double tuition;

        if(creditsEnrolled >= MIN_CREDITS && creditsEnrolled < MIN_CREDITS_FULL_TIME) {

            tuition = (CREDIT_HOUR_RATE * creditsEnrolled) + (UNIVERSITY_FEE_PART_TIME_RATE);
        }
        else if(creditsEnrolled > CREDITS_FULL_TIME) {

            tuition = (TUITION_FEE) + (UNIVERSITY_FEE) + (CREDIT_HOUR_RATE * (creditsEnrolled - CREDITS_FULL_TIME));
        }
        else {

            tuition = TUITION_FEE + UNIVERSITY_FEE;
        }

        if(creditsEnrolled >= MIN_CREDITS_FULL_TIME) {

            if(state.equalsIgnoreCase("NY")) {

                tuition = tuition - NY_TUITION_DISCOUNT;
            }
            else if(state.equalsIgnoreCase("CT")) {

                tuition = tuition - CT_TUITION_DISCOUNT;

            }
        }

        return tuition;
    }

    /**
     * Checks if student is a resident of NJ.
     * @return false since tri-state students are not residents of NJ.
     */
    public boolean isResident() {

        return false;
    }

    /**
     * The toString method will print a string representation of a tri-state student object
     * @return the string representation of the tri-state student
     */
    @Override
    public String toString() {

        //return getProfile() + " " + getMajor().toString() + " credits completed: " + getCreditCompleted()
               // + " (" + returnStanding(this) + ")" + " (" + "non-resident" + ")";

        return super.toString() + " (" + "tri-state:" + state.toUpperCase() + ")";
    }

    public static void main(String[] args) {

        Profile profile = new Profile("Lopez", "Juan", new Date("10/12/1992"));

        TriState triState = new TriState(profile, Major.BAIT, 0, "ny");

        System.out.println(triState);

        System.out.println(triState.tuitionDue(12));

    }
}
