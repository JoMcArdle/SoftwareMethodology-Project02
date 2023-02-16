package project2;
/**
 Profile represents a student profile in our program
 @author Yovanny Moscoso
 */
public class Profile implements Comparable<Profile> {
    private String lname;
    private String fname;
    private Date dob;

    /**
     * Empty constructor
     */
    public Profile(){

    }

    /**
     * Constructor that initializes all the values
     * @param lname
     * @param fname
     * @param dob
     */
    public Profile(String lname, String fname, Date dob ){
        this.lname = lname;
        this.fname = fname;
        this.dob = dob;
    }

    /**
     * getLname will return the last name
     * @return
     */
    public String getLname() {
        return lname;
    }
    /**
     * getFname will return the first name
     * @return
     */
    public String getFname() {
        return fname;
    }
    /**
     * getDob will return the date of birth
     * @return
     */
    public Date getDob() {
        return dob;
    }

    /**
     * The equals method will compare two profiles and see if they are equal
     * @param obj the param obj appears because we need to override this method which appears as an object type
     *  @return it will return true if the profiles are equal. If not, it will return false
     *
     */

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Profile){
            Profile p = (Profile) obj;
            return this.lname.equalsIgnoreCase(p.lname) && this.fname.equalsIgnoreCase(p.fname)
                    && this.dob.equals(p.dob);
        }else {
            return false;
        }
    }

    /**
     * The compareTo method will compare 2 profiles.
     * @param profile the object to be compared.
     * @return If the current object profile is greater than the other object, it will return 1. if the current object is lower, it will return -1. If they are the same it will return 0.
     */
    @Override
    public int compareTo(Profile profile) {

        if(this.lname.compareTo(profile.lname) > 0){
            return 1;
        }else if(this.lname.compareTo(profile.lname) < 0){
            return -1;
        }else if(this.fname.compareTo(profile.fname) > 0){
            return 1;
        }else if(this.fname.compareTo(profile.fname) < 0){
            return -1;
        }else if(this.dob.compareTo(profile.dob) > 0){
            return 1;
        }else if(this.dob.compareTo(profile.dob) < 0){
            return -1;
        }else {
            return 0;
        }
    }
    /**
     * The toString method will print a string representation of a profile object
     * @return the string representation of the profile
     */
    @Override
    public String toString(){
        return this.fname + " " + this.lname + " " + this.dob.toString();

    }
}