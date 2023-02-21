package project2;

/**
 EnrollStudent is the representation of a student enrollment
 @author Yovanny Moscoso
 */
public class EnrollStudent{

    private Profile profile;
    private int creditsEnrolled;

    /**
     * Empty constructor.
     */
    public EnrollStudent() {

    }

    /**
     *Constructor that initializes an EnrollStudent
     * @param profile
     * @param creditsEnrolled
     */
    public EnrollStudent(Profile profile, int creditsEnrolled){
        this.profile = profile;
        this.creditsEnrolled = creditsEnrolled;
    }

    /**
     *
     * Override method
     * The equals method will compare two enrollments to determine if they are equals based on their profile
     * @param obj appears because we need to override this method which accept an object type as a parameter
     * @return If the current profile is equal to the obj profile
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof EnrollStudent) {
            EnrollStudent enrollStudent = (EnrollStudent) obj;
            return this.profile.equals(enrollStudent.profile);
        }else{
            return false;
        }
    }

    /**
     * Override method
     * toString method will print a string representation of an EnrollStudent
     * @return the string representation of an enrollment
     */
    @Override
    public String toString() {
        return this.profile + "credits Enrolled:" + this.creditsEnrolled ;

    }
}
