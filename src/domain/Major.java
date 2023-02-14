package domain;

/**
    Major enum will show the different majors in a school by schools
    @author Yovanny Moscoso
 */
public enum Major{
    CS("01:198",  "SAS"),
    MATH("01:640", "SAS"),
    EE( "14:332", "SOE"),
    ITI( "04:547", "SC&I"),
    BAIT("33:146", "RBS");
    private final String school;
    private final String departmentNumber;

    /**
     * Contructor that initializes the values
     * @param departmentNumber
     * @param school
     */
    Major(String departmentNumber, String school){
        this.departmentNumber = departmentNumber;
        this.school = school;
    }

    /**
     * getSchool will return the school name of a major
     * @return
     */
    public String getSchool() {
        return this.school;
    }

    /**
     * getDepartmentNumber will return the department number
     * @return
     */
    public String getDepartmentNumber() {
        return departmentNumber;
    }

    /**
     * The toString method will print a string representation of a major
     * @return the string representation of the major
     */
    public String toString(){
        return "(" + this.departmentNumber + " " + this.name() + " " + this.school + ")";
    }

}
