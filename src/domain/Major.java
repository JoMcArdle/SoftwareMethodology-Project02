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

    Major(String departmentNumber, String school){
        this.departmentNumber = departmentNumber;
        this.school = school;
    }

    public String getSchool() {
        return school;
    }


    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public String toString(){
        return "(" + this.departmentNumber + " " + this.name() + " " + this.school + ")";
    }
    public static void main(String[] args) {
    Major m = Major.CS;
        System.out.println(m.name());
        String s = m.name();
    }
}
