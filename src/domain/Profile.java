package domain;
import java.util.Date;
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

    @Override
    public String toString(){
        return this.fname + "," + this.fname +", " + this.dob;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Profile){
            Profile p = (Profile) obj;
            if(this.lname.equals(p.lname) && this.fname.equals(p.fname) && this.dob.equals(p.dob)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Profile o) {
        return 0;
    }
}