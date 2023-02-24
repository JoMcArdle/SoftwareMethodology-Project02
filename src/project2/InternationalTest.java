package project2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 International test will test the tuitionDue for international students.
 If the international student falls in the isStudyAbroad, they do not pay tuition.
 If student does NOT fall int the isStudyAbroad, they will have to pay for tuition.
 @author Yovanny Moscoso
 */
public class InternationalTest {

    @Test
    public void test_tuitionDue_for_isStudyAbroad() {
         Profile p = new Profile("Tylor", "Rose", new Date("01/30/1999"));
         Student sInternational = new International(p, Major.CS, 10, true);
         assertEquals(5918, sInternational.tuitionDue(12),0.000001);
    }
    @Test
    public void test_tuitionDue_for_nonStudyAbroad() {
        Profile p = new Profile("Tylor", "Rose", new Date("01/30/1999"));
        Student sInternational = new International(p, Major.CS, 10, false);
        assertEquals(35655, sInternational.tuitionDue(12),0.000001);
    }
}