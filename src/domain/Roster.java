package domain;

public class Roster {
    private Student[] roster;
    private int size = 5;
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

        }
    }//maintain the order after remove


    public boolean contains(Student student){} //if the student is in roster
    public void print () {} //print roster sorted by profiles
    public void printBySchoolMajor() {} //print roster sorted by school major
    public void printByStanding() {} //print roster sorted by standing

    class Test{
        public static void main(String[] args) {
            Date myDate = new Date();
            Roster myRoster = new Roster();
           // Profile newProfile = new Profile("Laura", "Smith", myDate);
            Student st = new Student();
           // myRoster.add()
        }
    }
}