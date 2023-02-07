package domain;

public enum Major {
    CS("SAS", "01:198"),
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
}
