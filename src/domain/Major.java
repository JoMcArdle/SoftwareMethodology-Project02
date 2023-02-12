package domain;

/**
    Major enum will show the different majors in a school by schools
    @author Yovanny Moscoso
 */
public enum Major{
    CS( "SAS", "01:198"),
    MATH("SAS","01:640"),
    EE("SOE", "14:332"),
    ITI("SC&I", "04:547"),
    BAIT("RBS","33:146");

    private final String school;
    private final String departmentNumber;

    Major(String school, String departmentNumber){
        this.school = school;
        this.departmentNumber = departmentNumber;
    }

    public String getSchool() {
        return school;
    }


    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public String toString(){
        return this.school + "(" + this.name() +  this.departmentNumber + ")";
    }
    public static void main(String[] args) {
    Major m = Major.CS;
        System.out.println(m.name());
        String s = m.name();
        System.out.println(s+"itwas");
    }
}
