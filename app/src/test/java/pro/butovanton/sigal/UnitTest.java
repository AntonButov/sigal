package pro.butovanton.sigal;

import org.junit.Test;

import static org.junit.Assert.*;

public class UnitTest {
    @Test
    public void testSatelittes() {
        Satelittes satelittes = new Satelittes();
        assertNotNull(satelittes.getSatelites());
        assertTrue(satelittes.getSatelites().size()>0);
        for (satelliteinfo satelliteinfo : satelittes.getSatelites() )
            System.out.print(satelliteinfo.toString());
    }
}