package domain;

import java.util.Comparator;

/**
 Student class will represent a student entity in our program
 @author Yovanny Moscoso
 */
public class Student implements Comparable<Student>{

    private Profile profile;
    private Major major; //Major is an enum type
    private int creditCompleted;
    private Standing standing;

    public Student() {
    } // Empty constructor

    public Student(Profile profile, Major major, int creditCompleted) {
        this.profile = profile;
        this.major = major;
        this.creditCompleted = creditCompleted;
    }
    public Profile getProfile() {
        return this.profile;
    }

    public Major getMajor() {
        return this.major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public int getCreditCompleted() {
        return this.creditCompleted;
    }

    public void setCreditCompleted(int creditCompleted) {
        this.creditCompleted = creditCompleted;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            Student s = (Student) obj;
            if (s.profile.equals(this.profile)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        return this.profile + ", " + this.major.toString() + ", credits completed: " + this.creditCompleted + " (" + returnStanding(this) + ")";
    }

    @Override
    public int compareTo(Student student) {
        return this.profile.compareTo(student.profile);
    }
    public int compareByStanding(Student student){
        if(this.returnStanding(this).compareTo(student.returnStanding(student)) > 0){
            return 1;
        }else if(this.returnStanding(this).compareTo(student.returnStanding(student)) < 0){
            return -1;
        }else{
            return this.profile.compareTo(student.profile);
        }
    }
    public int compareByMajor(Student student){
        if(this.major.getSchool().compareTo(student.major.getSchool()) > 0){
            return 1;
        } else if (this.major.getSchool().compareTo(student.major.getSchool()) < 0) {
            return -1;
        }else if(this.major.name().compareTo(student.major.name()) > 0){
            return 1;
        }else if(this.major.name().compareTo(student.major.name()) < 0){
            return -1;
        }else {
            return this.profile.compareTo(student.profile);
        }
    }
    private String returnStanding(Student s){
        if(s.getCreditCompleted() > 0 && s.getCreditCompleted() < standing.FRESHMAN.getNumberOfCredits()){
            return standing.FRESHMAN.name();
        } else if(s.getCreditCompleted() >= standing.FRESHMAN.getNumberOfCredits() && s.getCreditCompleted() < standing.SOPHOMORE.getNumberOfCredits()){
            return standing.SOPHOMORE.name();
        } else if(s.getCreditCompleted() >= standing.SOPHOMORE.getNumberOfCredits() && s.getCreditCompleted() < standing.JUNIOR.getNumberOfCredits()){
            return standing.JUNIOR.name();
        } else if(s.getCreditCompleted() >= standing.JUNIOR.getNumberOfCredits()){
            return standing.SENIOR.name();
        }else{
            return "not a valid number of credits";
        }

    }

    public static void main(String[] args) {
        Profile p1 = new Profile("Mia", "Carlos", new Date());
        Profile p2 = new Profile("Maria", "Carlos", new Date());
        Profile p3 = new Profile("Lucas", "Taylor", new Date());
        System.out.println(new Date());
        Student student1 = new Student(p1, Major.BAIT, 30);
        Student student2 = new Student(p2, Major.CS, 45);
        Student student3 = new Student(p3, Major.CS, 98);
        System.out.println(student1);
        System.out.println(student2);
        System.out.println(student3);
        //System.out.println(s.compareTo(t));
        // System.out.println(student);
        String one = "hello";
        String two = "hellu";
        System.out.println(one.compareTo(two));


    }
}
