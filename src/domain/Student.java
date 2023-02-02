package domain;

public class Student implements Comparable<Student> {
    private Profile profile;
    private Major major; //Major is an enum type
    private int creditCompleted;

    public Student(){

    }
    public Student(Profile profile, Major major, int creditCompleted){
        this.profile = profile;
        this.major = major;
        this.creditCompleted = creditCompleted;
    }
    public String toString(){
        return this.profile + ", " + this.major + ", " + this.creditCompleted;
    }
    public boolean equals(Object obj){
        if(obj instanceof Student){
            Student s = (Student)obj;
            if(s.profile.equals(this.profile)){
                return true;
            }
        }
        return false;
    }
    @Override
    public int compareTo(Student o) {
        return 0;
    }
}