package project2;

public class Resident extends Student{

    private int scholarship; //this is the only instance variable, do not add more.

    /**
     * Empty constructor
     */
    public Resident() {

    }

    @Override
    public double tuitionDue(int creditsEnrolled) {
        return 0;
    }

    @Override
    public boolean isResident() {
        return false;
    }

}
