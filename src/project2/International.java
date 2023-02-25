package project2;

public class International extends NonResident{

    private boolean isStudyAbroad; //only instance variable, do not add more.
    private static final int HEALTH_INSURANCE_FEE = 2650;


    /**
     * Empty constructor.
     */
    public International() {

    }

    /**
     * Parameterized constructor, creates new International student by calling super method to pass parameters to Student class.
     * @param profile, last name, first name and DOB of International student.
     * @param major, the major of the International student.
     * @param creditCompleted, the number of credits a International student has completed.
     * @param isStudyAbroad, whether student is in the study abroad program or not.
     */
    public International(Profile profile, Major major, int creditCompleted, boolean isStudyAbroad) {
        super(profile, major, creditCompleted);
        this.isStudyAbroad = isStudyAbroad;
    }

    /**
     * Checks if the number of credits are valid.
     * @param creditEnrolled credits that are to be checked whether they are valid or not.
     * @return false if credits are less than the minimum amount of credits or greater than the maximum amount of
     * credits and true otherwise.
     */
    @Override
    public boolean isValid(int creditEnrolled) {

        if(isStudyAbroad == true) {

            if(creditEnrolled > MIN_CREDITS_FULL_TIME) {
                return false;
            }
        }
        else if(isStudyAbroad == false && creditEnrolled < MIN_CREDITS_FULL_TIME) {

            return false;
        }

        else {

        }
        return super.isValid(creditEnrolled);

    }

    /**
     * Calculates the amount of tuition due for an International student.
     * @param creditsEnrolled, the number of credits an International student has enrolled.
     * @return tuition amount due after all calculations have been made.
     */
    @Override
    public double tuitionDue(int creditsEnrolled) {
        //International students must enroll at least 12 credits, except for the international students who are participating in the study abroad
        //program. The maximum number of credits enrolled for the international students in the study abroad program is 12.

        double tuition = -1;;

        if (this.isStudyAbroad) {
            if (creditsEnrolled == MIN_CREDITS_FULL_TIME) { // Max credits for international students who participate in study abroad is 12.
                tuition = UNIVERSITY_FEE + HEALTH_INSURANCE_FEE;
            }else if(creditsEnrolled >= MIN_CREDITS && creditsEnrolled < MIN_CREDITS_FULL_TIME){// Part time
                tuition = (UNIVERSITY_FEE_PART_TIME_RATE) + (creditsEnrolled* CREDIT_HOUR_RATE);
            }
        }else{
            //International Students that are not in the study abroad must be at least Full time
                if (creditsEnrolled >= MIN_CREDITS_FULL_TIME && creditsEnrolled <= CREDITS_FULL_TIME) { // Full time
                    tuition = TUITION_FEE + UNIVERSITY_FEE + HEALTH_INSURANCE_FEE;
                } else if (creditsEnrolled > CREDITS_FULL_TIME && creditsEnrolled <= MAX_CREDITS) {// Beyond full time
                    tuition = TUITION_FEE + UNIVERSITY_FEE + HEALTH_INSURANCE_FEE + (creditsEnrolled - CREDITS_FULL_TIME) * CREDIT_HOUR_RATE;
                }
            }
            return tuition;

    }

    /**
     * Checks if student is a resident of NJ.
     * @return false since international students are not residents of NJ.
     */
    public boolean isResident() {

        return false;
    }

    /**
     * The toString method will print a string representation of an international student object
     * @return the string representation of the international student
     */
    @Override
    public String toString() {

        //return getProfile() + " " + getMajor().toString() + " credits completed: " + getCreditCompleted()
        // + " (" + returnStanding(this) + ")" + " (" + "non-resident" + ")";

        return super.toString() + " (" + "international" + ")" + isStudyAbroad; //added variable
    }

    public static void main(String[] args) {

        Profile profile = new Profile("Lopez", "Juan", new Date("10/12/1992"));

        International international = new International(profile, Major.BAIT, 0, true);

        System.out.println(international);

        System.out.println(international.tuitionDue(12));


    }
}
