package domain;

public class Roster {
    private Student[] roster;
    private int size = 2;
    private int numStudents = 0;
    private int location = 0;
    public Roster(){
        roster = new Student[size];
    }
    private int find(Student student) {
        int result = -1;
        for (int i = location; i< numStudents; i++){
            if(student.equals(roster[i])){
                result = 1;
                return result;
            }else{
                location++;
            }
        }
        return result;
    } //search the given student in roster
    private void grow() {
        Student[] newArray = new Student[size + 4];
        for(int i=0; i< roster.length; i++){
            newArray[i]= roster[i];
        }
        roster = newArray;
    } //increase the array capacity by 4

    public boolean add(Student student){
        if(numStudents == roster.length)
            grow();
        roster[numStudents] = student;
        numStudents++;
        return true;
    } //add student to end of array
    public boolean remove(Student student){
        if(find(student) == 1){
            for (int i = location; i<= numStudents -2; i++){
                roster[i] = roster[i+1];
            }
            roster[numStudents-1] = null;
            return true;
        }
        return false;
    }//maintain the order after remove
    public boolean contains(Student student){
        if(find(student) == 1){
            return true;
        }
        return false;
    } //if the student is in roster
    public void print () {

        for(int i=0; i < numStudents; i++) {
            System.out.println("Student" + (i+1) + "= " + roster[i]);
        }
    } //print roster sorted by profiles
    public void printBySchoolMajor() {} //print roster sorted by school major
    public void printByStanding() {} //print roster sorted by standing

        public static void main(String[] args) {
            Roster myRoster = new Roster();
            Profile p1 = new Profile("Laura", "Smith", new Date());
            Profile p2 = new Profile("Lucas", "Taylor", new Date());
            Profile p3 = new Profile("Marie", "Johnson", new Date());
            Profile p4 = new Profile("Thomas", "Edison", new Date());
            Student s1 = new Student(p1, Major.EE, 15);
            Student s2 = new Student(p2, Major.EE, 60);
            Student s3 = new Student(p3, Major.ITI, 75);
            Student s4 = new Student(p4, Major.ITI, 75);
            myRoster.add(s1);
            myRoster.add(s2);
            myRoster.add(s3);
            myRoster.add(s4);
           myRoster.print();

    }
}