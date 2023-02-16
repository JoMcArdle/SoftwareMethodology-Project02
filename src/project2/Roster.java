package domain;

/**
 Roster class is a student array based class.
 @author Yovanny Moscoso
 */
public class Roster {
    private Student[] roster;
    private int size = 100;
    public int numStudents = 0;
    private int location;
    private static final int INCREASE_CAPACITY = 4;
    private static final int NOT_FOUND= -1;
    /**
     * Empty roster constructor
     */
    public Roster(){

        roster = new Student[size];
    }

    /**
     * The find method will receive a student and will try to find a student in the roster.
     * @param student
     * @return If the student is in the roster, the method will return the index where the student is located. If not, it will return -1.
     */
    public int find(Student student) {
        location = 0;
        int result = NOT_FOUND;
        for (int i = location; i< numStudents; i++) {
            if (roster[location].equals(student)) {
                result = location;
                return result;
            }else{
                location++;
            }
        }
        return result;
    } //search the given student in roster

    /**
     * The grow method will increase the capacity of the array by 4;
     */
    private void grow() {
        Student[] newArray = new Student[size + INCREASE_CAPACITY];
        for(int i=0; i< numStudents; i++){
            newArray[i]= roster[i];
        }
        roster = newArray;
    } //increase the array capacity by 4

    /**
     * This method will add a student to the roster
     * @param student
     * @return it returns true if we add an student
     */
    public boolean add(Student student){
        if(numStudents == roster.length) {
            grow();
        }
        roster[numStudents] = student;
        numStudents++;
        return true;

    } //add student to end of array
    /**
     * This method will attempt to find an student in the roster and remove it.
     * @param student
     * @return it returns true if the student was removed. If not it will return false.
     */
    public boolean remove(Student student){
        if(find(student) >= 0){
            for (int i = location; i <= numStudents -2; i++){
                roster[i] = roster[i+1];
            }
            roster[numStudents-1] = null;
            numStudents--;
            return true;
        }else {
            return false;
        }
    }//maintain the order after remove

    /**
     * The contains method will check if a student is in the roster
     * @param student
     * @return it returns true if the student was found. If not, it will return false
     */
    public boolean contains(Student student){
        if(find(student) >= 0){
            return true;
        }else {
            return false;
        }
    } //if the student is in roster

    /**
     * Helper method. This method will print all the contents in any the array of Students
     * @param students
     */
    private void iterate(Student[] students){
        for(int i = 0; i < numStudents; i++){
            System.out.println(students[i]);
        }// This method will iterate the array of students without iterate de null values
    }

    /**
     * Print method prints the contents of the roster sorted by profile (lname, fname, dob)
     */
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

    /**
     * printBySchoolMajor method will print the students by major and school using alphabetical order
     */
    public void printBySchoolMajor() {
        boolean needToSwap = true;
        while (needToSwap) {
            needToSwap = false;
            for (int i = 0; i < numStudents - 1; i++) {
                if (roster[i].compareByMajor(roster[i + 1]) > 0) {
                    needToSwap = true;
                    Student temp = roster[i];
                    roster[i] = roster[i + 1];
                    roster[i + 1] = temp;
                }
            }
        }
        iterate(roster);
    }

    /**
     * printByStanding method will print the roster sorted by standing from lower to highest
     */
    public void printByStanding() {
        boolean needToSwap = true;
        while (needToSwap) {
            needToSwap = false;
            for (int i = 0; i < numStudents - 1; i++) {
                if (roster[i].compareByStanding(roster[i + 1]) > 0) {
                    needToSwap = true;
                    Student temp = roster[i];
                    roster[i] = roster[i + 1];
                    roster[i + 1] = temp;
                }
            }
        }
        iterate(roster);
    }//print roster sorted by standing

    /**
     * This method will receive the schoolName as a parameter and print all the students in that school ordered alphabetically
     * @param schoolName
     */
    public void printMajor(String schoolName){
        for(int i = 0; i < numStudents; i++){
            if(schoolName.equals(roster[i].getMajor().getSchool())){
                System.out.println(roster[i]);
            }
        }
    }

    /**
     * The updateMajor will update the major of n student in the roster
     * @param s
     * @return if the student is in the roster, it will update it and return true. If not, it will return false;
     */
    public boolean updateMajor(Student s){
        if (find(s) >= 0){
             roster[find(s)].setMajor(s.getMajor());
        return true;
        }else{
            return false;
        }

    }
}
