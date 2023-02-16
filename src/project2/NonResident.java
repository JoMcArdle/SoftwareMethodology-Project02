package project2;

public class NonResident extends Student{

    //no additional instance variables

    /**
     * Empty constructor.
     */
    public NonResident() {

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
