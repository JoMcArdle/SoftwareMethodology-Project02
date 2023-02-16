package project2;

import java.util.Calendar;

/**
 * Date class that provides methods for creating a Student's DOB and checking if a given date is valid.
 * @author Joshua McArdle
 */
public class Date implements Comparable<Date> {

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
    public static final int DATE_IS_GREATER = 1;
    public static final int DATE_IS_LESS = -1;
    public static final int DATE_IS_EQUAL = 0;
    private int year;
    private int month;
    private int day;

    /**
     * Constructor that creates an object with today's date using the Calendar class.
     */
    public Date() {

        Calendar today = Calendar.getInstance();
        this.day = today.get(Calendar.DAY_OF_MONTH);
        this.month = today.get(Calendar.MONTH) + 1;
        this.year = today.get(Calendar.YEAR);

    }

    /**
     * Parameterized Constructor that creates an object with a given date.
     * @param date a String input in "mm/dd/yyyy" format
     */
    public Date(String date) {

        String strDate = date;
        String [] arrayOfDate = strDate.split("/");
        this.month = Integer.parseInt(arrayOfDate[0]);
        this.day = Integer.parseInt(arrayOfDate[1]);
        this.year = Integer.parseInt(arrayOfDate[2]);

    }

    /**
     * Getter method that returns the day.
     * @return day
     */
    public int getDay() {

        return day;
    }

    /**
     * Getter method that returns the month.
     * @return month
     */
    public int getMonth() {

        return month;
    }

    /**
     * Getter method that returns the year.
     * @return year
     */
    public int getYear() {

        return year;
    }

    /**
     * Helper method for isValid(), checks the month and special cases involving the day and leap years.
     * @return true if month is valid and false otherwise.
     */
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
            if (checkLeapYear() == true) {
                if(this.day < FIRST_DAY_OF_MONTH || this.day > LAST_DAY_OF_LEAP_YEAR_FEB) {
                    return false;
                }
            }
            else if(checkLeapYear() == false) {
                if(this.day < FIRST_DAY_OF_MONTH || this.day > LAST_DAY_OF_NON_LEAP_YEAR_FEB) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Helper method for isValid(), checks the year and special cases involving the current date.
     * @return true if year is valid and false otherwise.
     */
    private boolean checkYear() {

        Date currentDate = new Date();

        if(this.year < MAX_YEAR) {

            return false;
        }
        else if(this.year == currentDate.year || this.year > currentDate.year - MIN_AGE) {

            return false;
        }
        else if(this.year == currentDate.year - MIN_AGE) {

            if(this.month > currentDate.month) {

                return false;
            }

            else if(this.month == currentDate.month) {

                if(this.day > currentDate.day) {

                    return false;
                }
            }
        }
        return true;

    }

    /**
     * Helper method for checkMonth(), checks to see if the year is a leap year or not.
     * @return true if year is a leap year and false otherwise.
     */
    private boolean checkLeapYear() {

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
    }

    /**
     * Checks if a date is a valid calendar date.
     * @return true if date is valid and false otherwise.
     */
    public boolean isValid() {


        if(checkMonth() == true && checkYear() == true) {

            return true;
        }
        return false;

    }

    /**
     * Override method that compares two dates' year, month and day.
     * @param o the object to be compared.
     * @return DATE_IS_LESS, DATE_IS_EQUAL, or DATE_IS_LESS if this date is less than, equal to or
     * greater than the other date.
     */
    @Override
    public int compareTo(Date o) {

        if(this.year > o.year) {

            return DATE_IS_GREATER;
        }
        else if(this.year < o.year) {

            return DATE_IS_LESS;
        }
        else if(this.year == o.year) {

            if (this.month > o.month) {

                return DATE_IS_GREATER;

            } else if (this.month < o.month) {

                return DATE_IS_LESS;

            } else if (this.month == o.month) {

                if (this.day > o.day) {

                    return DATE_IS_GREATER;

                } else if (this.day < o.day) {

                    return DATE_IS_LESS;

                } else {

                    return DATE_IS_EQUAL;
                }
            }
        }

        return DATE_IS_EQUAL;
    }

    /**
     * Override method that sees if two dates are equal to each other.
     * @param obj, compared with this date.
     * @return 0 if both dates are equal and false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Date) {
            Date date = (Date) obj;
            return Integer.compare(this.month, date.month) == 0
                    && Integer.compare(this.day, date.day) == 0
                    && Integer.compare(this.year, date.year) == 0;
        }
        return false;
    }

    /**
     * Override method that returns the date in "mm/dd/yyyy" format.
     * @return month, day and year in "mm/dd/yyyy" format.
     */
    @Override
    public String toString() {

        return this.month + "/" + this.day + "/" + this.year;

    }

    /**
     * Testbed main for isValid() method, creates the specified test cases and sees whether a date is valid or not.
     * @param args
     */
    public static void main(String[] args) {

        //d1-d7 are my test cases. d1-d5 are invalid dates, d6-d7 are valid dates.
        Date d1 = new Date("1/13/600");

        Date d2 = new Date("-50/20/1960");

        Date d3 = new Date("2/29/2024");

        Date d4 = new Date("1/20/2011");

        Date d5 = new Date("3/11/2007");

        Date d6 = new Date("2/29/2004");

        Date d7 = new Date("4/11/1996");

        //d4-d10 are the provided test cases in Project1TestCases.txt
        Date d8 = new Date("2/29/2019");

        Date d9 = new Date("9/2/2022");

        Date d10 = new Date("2/29/2003");

        Date d11 = new Date("4/31/2003");

        Date d12 = new Date("13/31/2003");

        Date d13 = new Date("3/32/2003");

        Date d14 = new Date("-1/31/2003");

        if(d1.isValid() == true) {

            System.out.println("This is a valid date.");
        }
        else {

            System.out.println("This is an invalid date.");
        }
    }
}
