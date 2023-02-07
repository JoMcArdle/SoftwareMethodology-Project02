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

    public Student() {
    } // Empty constructor

    public Student(Profile profile, Major major, int creditCompleted) {
        this.profile = profile;
        this.major = major;
        this.creditCompleted = creditCompleted;
    }
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }
    public int getCreditCompleted() {
        return creditCompleted;
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
        return this.profile + ", " + this.major + ", " + this.creditCompleted;
    }

    @Override
    public int compareTo(Student student) {
        return this.profile.compareTo(student.profile);
    }
    public int compareByMajor(Student s){
        /*
        if(this.getMajor().getDepartmentName().compareTo(s.getMajor().getDepartmentName()) > 0){
            return 1;
        }else if(this.getMajor().getDepartmentName().compareTo(s.getMajor().getDepartmentName()) < 0){
            return -1;
        }else{
            return 0;
        }*/
        return this.getMajor().getDepartmentName().compareTo(s.getMajor().getDepartmentName());
    }
    public int compareByNumberOfCredits(Student s){
        return this.creditCompleted - s.creditCompleted;
    }
    public static void main(String[] args) {
            Profile p1 = new Profile("Mia", "Carlos", new Date());
            Profile p2 = new Profile("Maria", "Carlos", new Date());
            Profile p3 = new Profile("Lucas", "Taylor", new Date());
            System.out.println(new Date());
            Student student1= new Student(p1, Major.BAIT , 60);
            Student student2= new Student(p2, Major.CS, 60);
            Student student3= new Student(p3, Major.CS, 60);
            System.out.println(student1.compareTo(student2));
            String s= "Hello";
            String t = "Hullo";
            //System.out.println(s.compareTo(t));
            // System.out.println(student);
        System.out.println(student1.compareByMajor(student2));
        System.out.println(student1.getMajor().getDepartmentName());

    }
}
