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

        return super.isValid(creditEnrolled);
    }

    /**
     * Calculates the amount of tuition due for an International student.
     * @param creditsEnrolled, the number of credits an International student has enrolled.
     * @return tuition amount due after all calculations have been made.
     */
    @Override
    public double tuitionDue(int creditsEnrolled) {

        double tuition;

        if(isStudyAbroad) {

            tuition = UNIVERSITY_FEE + HEALTH_INSURANCE_FEE;
            return tuition;
        }
        else if(creditsEnrolled >= MIN_CREDITS_FULL_TIME && creditsEnrolled <= CREDITS_FULL_TIME) {

            tuition = TUITION_FEE + UNIVERSITY_FEE + HEALTH_INSURANCE_FEE;
        }
        else {

            tuition = (TUITION_FEE) + (UNIVERSITY_FEE) + (HEALTH_INSURANCE_FEE) +
                    (CREDIT_HOUR_RATE * (creditsEnrolled - CREDITS_FULL_TIME)); //credits enrolled > 16
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

        System.out.println(international.tuitionDue(10));


    }
}
