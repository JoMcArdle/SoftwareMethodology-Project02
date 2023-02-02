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
        Date dateObject = new Date(date);
    } //take “mm/dd/yyyy” and create a Date object

    public boolean isValid() {
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