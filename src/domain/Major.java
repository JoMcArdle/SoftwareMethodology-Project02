package domain;

/**
    Major enum will show the different majors in a school by schools
    @author Yovanny Moscoso
 */
public enum Major {
    CS( "CS","SAS", "01:198"),
    MATH("Math","SAS","01:640"),
    EE("EE","SOE", "14:332"),
    ITI("ITI","SC&I", "04:547"),
    BAIT("BAIT","RBS","33:146");

    private final String departmentName;
    private final String school;
    private final String departmentNumber;

    Major(String departmentName, String school, String departmentNumber){
        this.departmentName = departmentName;
        this.school = school;
        this.departmentNumber = departmentNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getSchool() {
        return school;
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

}
