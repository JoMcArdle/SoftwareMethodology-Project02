package domain;

import java.util.Comparator;

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
                result = location;
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

    public boolean add(Student students){
        if(numStudents == roster.length)
            grow();
        roster[numStudents] = students;
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
    private void iterate(Student[] students){
        for(int i = 0; i<numStudents; i++){
            System.out.println(students[i]);
        }// This method will iterate the array of students without iterate de null values
    }
    public void print() {
        boolean needToSwap = true;
        while(needToSwap){
            needToSwap = false;
            for (int i = 0; i < numStudents - 1; i++) {
                if (roster[i].compareTo(roster[i + 1]) > 0) {
                    needToSwap = true;
                    Student temp = roster[i];
                    roster[i] = roster[i + 1];
                    roster[i + 1] = temp;
                }
            }
        }
        iterate(roster);
    } //print roster sorted by profiles
    public void printBySchoolMajor(){
        boolean needToSwap = false;
        for (int i = 0; i < numStudents-1; i++) {
            if (roster[i].getMajor().name().compareTo(roster[i + 1].getMajor().name()) > 0){
                needToSwap = true;
                Student temp = roster[i];
                roster[i] = roster[i + 1];
                roster[i + 1] = temp;
            }
        }
        iterate(roster);
    }
    public void printByStanding(){
        boolean needToSwap = false;
        for (int i = 0; i < numStudents-1; i++) {
            if (roster[i].compareByNumberOfCredits(roster[i + 1]) > 0){
                needToSwap = true;
                Student temp = roster[i];
                roster[i] = roster[i + 1];
                roster[i + 1] = temp;
            }
        }
        iterate(roster);
    } //print roster sorted by standing


        public static void main(String[] args) {

            Roster myRoster = new Roster();
            Profile p1 = new Profile("Laura", "Smith", new Date("12/14/1993"));
            Profile p2 = new Profile("Lucas", "Taylor", new Date("12/15/1993"));
            Profile p3 = new Profile("Marie", "Johnson", new Date("11/18/1985"));
            Profile p4 = new Profile("Thomas", "Edison", new Date("02/25/1190"));
            Profile p5 = new Profile("Ana", "Fernandez", new Date("05/13/2004"));
            Profile p6 = new Profile("Ana", "Gonzales", new Date("05/13/2004"));
            Student s1 = new Student(p1, Major.EE, 15);
            Student s2 = new Student(p2, Major.EE, 60);
            Student s3 = new Student(p3, Major.ITI, 75);
            Student s4 = new Student(p4, Major.ITI, 75);
            Student s5 = new Student(p5, Major.BAIT, 80);
            Student s6 = new Student(p6, Major.CS, 90);
            myRoster.add(s1);
            myRoster.add(s2);
            myRoster.add(s3);
            myRoster.add(s4);
            myRoster.add(s5);
            myRoster.add(s6);
           // myRoster.print();
            //int i = myRoster.find(s3);
            //System.out.println(i);
            //myRoster.printBySchoolMajor();
            myRoster.printByStanding();

    }


}