package domain;

public class Student implements Comparable<Student> {

    private Profile profile;
    private Major major; //Major is an enum type
    private int creditCompleted;

    public Student() {

    }

    public Student(Profile profile, Major major, int creditCompleted) {
        this.profile = profile;
        this.major = major;
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
    public int compareTo(Student student) {
        return this.profile.compareTo(student.profile);
    }

    public String toString() {
        return this.profile + ", " + this.major + ", " + this.creditCompleted;
    }
        public static void main(String[] args) {
            Profile p1 = new Profile("Juan", "Carlos", new Date());
            Profile p2 = new Profile("Juan", "Carlos", new Date());
            Profile p3 = new Profile("Lucas", "Taylor", new Date());

            Student student1= new Student(p1, Major.CS, 60);
            Student student2= new Student(p2, Major.CS, 60);
            Student student3= new Student(p3, Major.CS, 60);
            System.out.println(student1.compareTo(student2));
            String s= "Hello";
            String t = "Hullo";
            //System.out.println(s.compareTo(t));
            // System.out.println(student);

    }
}
