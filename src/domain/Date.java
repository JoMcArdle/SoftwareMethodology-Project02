package domain;

import java.util.Calendar;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public Date() {
        Calendar cal = Calendar.getInstance();
    } //create an object with today’s date (see Calendar class)

    public Date(String date) {
        String[] myDate = date.split("/");
        int month = Integer.parseInt(myDate[0]);
        int day = Integer.parseInt(myDate[1]);
        int year = Integer.parseInt(myDate[2]);
    } //take “mm/dd/yyyy” and create a Date object

    public boolean isValid() {
        return true;
    } //check if a date is a valid calendar date

    @Override
    public int compareTo(Date o) {
        return 0;
    }
    class Test{
        public static void main(String[] args) {

        }
    }
}
