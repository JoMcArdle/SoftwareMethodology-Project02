package project2;

import org.junit.Test;

import static org.junit.Assert.*;

/**+
 * DateTest will test the isValid method ftom Date class.
 * We will enter test 5 invalid cases and 2 valid cases in order to test isValid method.
 * @author Yovanny Moscoso
 */
public class DateTest {

    /**
     ******************** INVALID DATES *******************
     */
    @Test
    public void test_isValid_less_than_16() {
        Date dob = new Date("12/15/2012");
        assertFalse(dob.isValid());
    }
    @Test
    public void test_isValid_negative_input() {
        Date dob = new Date("-01/15/1995");
        assertFalse(dob.isValid());
    }
    @Test
    public void test_isValid_invalid_year() {
        Date dob = new Date("01/22/2025");
        assertFalse(dob.isValid());
    }
    @Test
    public void test_isValid_invalid_month(){
        Date dob = new Date("13/15/1995");
        assertFalse(dob.isValid());
    }
    @Test
    public void test_isValid_invalid_day() {
        Date dob = new Date("02/35/1995");
        assertFalse(dob.isValid());
    }
    @Test
    public void test_isValid_february29_non_leap_year() {
        Date dob = new Date("02/29/2001");
        assertFalse(dob.isValid());
    }

    /**
     ******************* VALID DATES *******************
     */
    @Test
    public void test_isValid_february29_leap_year() {
        Date dob = new Date("02/29/2000");
        assertTrue(dob.isValid());
    }
    @Test
    public void test_isValid_july31() {
        Date dob = new Date("07/31/1995");
        assertTrue(dob.isValid());
    }
}