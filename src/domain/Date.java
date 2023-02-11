package domain;

import java.util.Calendar;

/**
 Date class that provides methods for creating a Student's DOB and checking if a given date is valid.
 @author Joshua McArdle
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    public static final int JANUARY = 1;
    public static final int FEBRUARY = 2;
    public static final int MARCH = 3;
    public static final int APRIL = 4;
    public static final int MAY = 5;
    public static final int JUNE = 6;
    public static final int JULY = 7;
    public static final int AUGUST = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER = 10;
    public static final int NOVEMBER = 11;
    public static final int DECEMBER = 12;
    public static final int FIRST_DAY_OF_MONTH = 1;
    public static final int LAST_DAY_OF_NON_LEAP_YEAR_FEB = 28;
    public static final int LAST_DAY_OF_LEAP_YEAR_FEB = 29;
    public static final int THIRTIETH_OF_MONTH = 30;
    public static final int THIRTY_FIRST_OF_MONTH = 31;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int MAX_YEAR = 1900;
    public static final int MIN_AGE = 16;



    public Date() {

        Calendar today = Calendar.getInstance();
        this.day = today.get(Calendar.DAY_OF_MONTH);
        this.month = today.get(Calendar.MONTH) + 1;
        this.year = today.get(Calendar.YEAR);


    } //create an object with today’s date (see Calendar class)

    public Date(String date) {

        String strDate = date;
        String [] arrayOfDate = strDate.split("/");
        this.month = Integer.parseInt(arrayOfDate[0]);
        this.day = Integer.parseInt(arrayOfDate[1]);
        this.year = Integer.parseInt(arrayOfDate[2]);

    } //take “mm/dd/yyyy” and create a Date object

    public int getDay() {

        return day;
    }

    public int getMonth() {

        return month;
    }

    public int getYear() {

        return year;
    }

    private boolean checkMonth() {

        if(this.month < JANUARY || this.month > DECEMBER) {
            return false;
        }
        //check the months of January, March, May, July, August, October, and December
        if(this.month == JANUARY || this.month == MARCH || this.month == MAY || this.month == JULY ||
                this.month == AUGUST || this.month == OCTOBER || this.month == DECEMBER) {
            if(this.day < FIRST_DAY_OF_MONTH || this.day > THIRTY_FIRST_OF_MONTH) {
                return false;
            }
        }

        //check the months of April, June, September, and November
        if(this.month == APRIL || this.month == JUNE || this.month == SEPTEMBER || this.month == NOVEMBER) {
            if(this.day < FIRST_DAY_OF_MONTH || this.day > THIRTIETH_OF_MONTH) {
                return false;
            }
        }

        //check the month of February
        if(this.month == FEBRUARY) {
            if (leapYear() == true) {
                if(this.day < FIRST_DAY_OF_MONTH || this.day > LAST_DAY_OF_LEAP_YEAR_FEB) {
                    return false;
                }
            }
            else if(leapYear() == false) {
                if(this.day < FIRST_DAY_OF_MONTH || this.day > LAST_DAY_OF_NON_LEAP_YEAR_FEB) {
                    return false;
                }
            }
        }
        return true;
    } //helper method for isValid()

    private boolean checkYear() {

        Date currentDate = new Date();

        if(this.year < MAX_YEAR) {

            return false;
        }
        else if(this.year == currentDate.year) {

            return false;
        }
        else if(this.year >= currentDate.year - MIN_AGE) {

            if(this.month > currentDate.month) {

                return false;
            }

            if(this.day >= currentDate.day) {

                return false;
            }
        }
        return true;

    } //helper method for isValid()

    private boolean leapYear() {

        if(this.year % QUATERCENTENNIAL == 0) {

            return true;
        }
        else if(this.year % CENTENNIAL == 0) {

            return false;
        }
        else if (this.year % QUADRENNIAL == 0) {

            return true;
        }
        else {

            return false;
        }
    } //helper method for checkMonth()

    public boolean isValid() {


        if(checkMonth() == true && checkYear() == true) {

            return true;
        }
        return false;

    } //check if a date is a valid calendar date

    @Override
    public int compareTo(Date o) {

        if(this.year > o.year) {

            return -1;
        }
        else if(this.year < o.year) {

            return 1;
        }
        else if(this.year == o.year) {

            if (this.month > o.month) {

                return -1;

            } else if (this.month < o.month) {

                return 1;

            } else if (this.month == o.month) {

                if (this.day > o.day) {

                    return -1;

                } else if (this.day < o.day) {

                    return 1;

                } else {

                    return 0;
                }
            }
        }

        /*if(this.month > o.month) {

            return 1;
        }
        else if (this.month < o.month) {

            return -1;
        }
        else if (this.month == o.month) {

            if(this.day > o.day) {

                return 1;
            }
            else if (this.day < o.day) {

                return -1;
            }
            else if (this.day == o.day) {

                if (this.year > o.year) {

                    return 1;
                }
                else if (this.year < o.year) {

                    return -1;
                }
                else {

                    return 0;
                }
            }
        }*/
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Date) {
            Date date = (Date) obj;
            return Integer.compare(month, date.month) == 0
                    && Integer.compare(day, date.day) == 0
                    && Integer.compare(year, date.year) == 0;
        }
        return false;
    }

    @Override
    public String toString() {

        return this.month + "/" + this.day + "/" + this.year;

    }

    public static void main(String[] args) {

        //d1-d3 are my test cases
        Date d1 = new Date("1/13/600");

        Date d2 = new Date("-50/20/1960");

        Date d3 = new Date("2/29/2024");

        //d4-d10 are the provided test cases in Project1TestCases.txt
        Date d4 = new Date("2/29/2019");

        Date d5 = new Date("9/2/2022");

        Date d6 = new Date("2/29/2003");

        Date d7 = new Date("4/31/2003");

        Date d8 = new Date("13/31/2003");

        Date d9 = new Date("3/32/2003");

        Date d10 = new Date("-1/31/2003");

        if(d1.isValid() == true) {

            System.out.println("This is a valid date.");
        }
        else {

            System.out.println("This is an invalid date.");
        }
    }
}
