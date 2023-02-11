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
        return this.profile;
    }

    public Major getMajor() {
        return this.major;
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
        return this.profile + ", " + this.major.name() + ", " + this.creditCompleted;
    }

    @Override
    public int compareTo(Student student) {
        return this.profile.compareTo(student.profile);
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
            System.out.println(student3.getMajor());
            //System.out.println(s.compareTo(t));
            // System.out.println(student);


    }
}
