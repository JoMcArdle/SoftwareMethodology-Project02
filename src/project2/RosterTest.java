package project2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * RosterTest will test the add method from Roster class. We will test the add method with
 * International students and TriState students
 * @author Yovanny Moscoso
 */
public class RosterTest {
    Roster r = new Roster();
    /**
     * Creating an International student
     */
    Profile pInternational = new Profile("Lara", "Juan", new Date("08/12/1993"));
    Student sInternational = new International(pInternational, Major.CS, 10, true);

    /**
     * Creating a TriState student
     */
    Profile pTriState = new Profile("Smith", "Anna", new Date("02/16/2000"));
    Student sTriState = new TriState(pTriState, Major.CS, 10, "New Jersey");

    @Test
    public void test_add_international() {
        assertTrue(r.add(sInternational));
        assertFalse(r.add(sInternational));
    }
    @Test
    public void test_add_tri_state(){
        assertTrue(r.add(sTriState));
        assertFalse(r.add(sTriState));
    }


}
