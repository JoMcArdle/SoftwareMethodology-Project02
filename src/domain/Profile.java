package domain;
/**
 Profile represents a student profile in our program
 @author Yovanny Moscoso
 */
public class Profile implements Comparable<Profile> {
    private String lname;
    private String fname;
    private Date dob;

    public Profile(){

    }
    public Profile(String lname, String fname, Date dob ){
        this.lname = lname;
        this.fname = fname;
        this.dob = dob;
    }

    public String getLname() {
        return lname;
    }

    public String getFname() {
        return fname;
    }

    public Date getDob() {
        return dob;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Profile){
            Profile p = (Profile) obj;
            if(this.lname.equalsIgnoreCase(p.lname) && this.fname.equalsIgnoreCase(p.fname)
                    && this.dob.equals(p.dob)) {
                return true;
            }
        }
        return false;
    }

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

    @Override
    public String toString(){
        return this.lname + " " + this.fname +", " + this.dob.toString();
    }
}