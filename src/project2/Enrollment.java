package project2;

/**
 Enrollment class will represent a container of all the students enrolled in the current semester
 @author Yovanny Moscoso
 */
public class Enrollment {
        public int numEnrollments = 0;
        private int location;
        private static final int INCREASE_CAPACITY = 4;
        private static final int NOT_FOUND= -1;
        private EnrollStudent[] enrollStudents;
        private static final int size = 100;


        /**
         * Empty constructor.
         */
        public Enrollment () {
                enrollStudents = new EnrollStudent[size];
        }

        /**
         * grow method will automatically increase the size of the EnrollStudent array
         * Helper method
         */
        private void grow(){
                EnrollStudent[] newArray = new EnrollStudent[size + INCREASE_CAPACITY];
                for(int i =0; i < enrollStudents.length; i++){
                        newArray[i] = enrollStudents[i];
                }
                enrollStudents = newArray;
        }
        /**
         * Helper method
         * The find method will receive a enrollStudent and will try to find an equal enrollStudent in the Enrollment.
         * @param enrollStudent
         * @return If the enrollStudent is in the Enrollment array, the method will return the index where the enrollStudent is located. If not, it will return -1.
         */
        private int find(EnrollStudent enrollStudent){
                location = 0;
                int result = NOT_FOUND;
                for (int i = location; i< numEnrollments; i++) {
                        if (this.enrollStudents[location].equals(enrollStudent)) {
                                result = location;
                                return result;
                        }
                }
                return result;
        }
        /**
         * This method will add a enrollStudent to the end of Enrollment array. If the profile of the student is in the Enrollment,
         * it will update the enrollStudent in the array to the one inserted as a parameter (the credits enrolled might change)
         * @param enrollStudent
         * @return it returns true if we add a enrollStudent to the
         */
        public boolean add(EnrollStudent enrollStudent){
                if(numEnrollments == enrollStudents.length){
                        grow();
                }else if(contains(enrollStudent)){
                        enrollStudents[find(enrollStudent)] = enrollStudent;
                        return false;
                }else{
                        enrollStudents[numEnrollments] = enrollStudent;
                        numEnrollments++;
                        return true;
                }
                return false;

        } //add to the end of array
        //move the last one in the array to replace the deleting index position

        /**
         * This method will attempt to find an enrolled student in the Enrollment and remove it.
         * If the enrollStudent is removed, the elements after the element removed will be moved one index to the left
         * @param enrollStudent
         * @return it returns true if the enrollStudent was removed. If not it will return false.
         */
        public boolean remove(EnrollStudent enrollStudent){
                if(contains(enrollStudent)){
                        for(int i= find(enrollStudent); i < numEnrollments -2; i++) {
                                this.enrollStudents[find(enrollStudent)] = enrollStudents[i+1];
                        }
                        enrollStudents[numEnrollments-1] = null;
                        numEnrollments--;
                        return true;
                }else{
                        return false;
                }
        }

        /**
         * The contains method will check if a enrollStudent is in the Enrollment array.
         * @param enrollStudent
         * @return it returns true if the enrollStudent was found. If not, it will return false
         */

        public boolean contains(EnrollStudent enrollStudent){
              if(find(enrollStudent) >= 0){
                      return true;
              }else{
                      return false;
              }
        }

        /**
         * This method will print all the students enrolled as they were inserted
         */
        public void print() {
                for(EnrollStudent e: enrollStudents){
                        System.out.println(e);
                }
        } //print the array as is without sorting

        public static void main(String[] args) {

                Enrollment e = new Enrollment();
                Profile p1= new Profile("Lara", "Juan", new Date("12/14/1990"));
                EnrollStudent es = new EnrollStudent(p1, 25);

        }
}
