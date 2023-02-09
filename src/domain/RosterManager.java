package domain;
import java.util.Scanner;

/**
 User interface class that handles line commands entered on the console and displays the results on the console.
 @author Joshua McArdle
 */
public class RosterManager {

    private String opCode;
    private String fname;
    private String lname;
    private String dob;
    private String major;
    private String credits;



    public RosterManager() {

    }

    private void operations() {

        switch(opCode) {
            case "A":

                System.out.println("Student added.");
                break;

            case "R":
                System.out.println("Student removed.");
                break;

            case "P":
                System.out.println("Displays roster sorted by last name, first name, and DOB.");
                break;

            case "PS":
                System.out.println("Displays roster sorted by standing.");
                break;

            case "PC":
                System.out.println("Displays the roster sorted by school and major");
                break;

            case "L":
                System.out.println("Lists the students in a specified school, sorted by last name," +
                        "first name, and DOB.");
                break;

            case "C":
                System.out.println("Changes a student's major.");
                break;

            case "Q":
                System.out.println("Roster Manager terminated.");
                return;

            default:
                System.out.println(opCode + " is an invalid command!");
                break;
        }
    }

    private void dataToken() {

        //insert code here
    }

    private void convertToTokens(String elements) {

        String [] arrOfTokens = elements.split(" ");

        this.opCode = arrOfTokens[0];

        if(arrOfTokens.length > 1) {
            this.fname = arrOfTokens[1];
            this.lname = arrOfTokens[2];
            this.dob = arrOfTokens[3];
            this.major = arrOfTokens[4];
            this.credits = arrOfTokens[5];
        }

    }

    public void run() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Roster Manager running...");

        while(sc.hasNextLine()) {

            String command = sc.nextLine();

            convertToTokens(command);

            operations();

            if(command.equals("Q")) {

                return;
            }

        }

    }
}
