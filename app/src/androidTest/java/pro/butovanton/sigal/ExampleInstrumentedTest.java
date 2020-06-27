package pro.butovanton.sigal;

import android.content.Context;
import android.location.Location;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("pro.butovanton.sigal", appContext.getPackageName());
    }

    public Parser parser;
    Poligons poligons;
    Location Rostov = new Location("GPS");
    Location Seul = new Location("GPS");

    @Before
    public void initParser() throws IOException, XmlPullParserException {
    parser = new Parser(InstrumentationRegistry.getInstrumentation().getTargetContext());
    poligons = new Poligons(parser.getPoligons());
    Rostov.setLatitude(47.2357137);
    Rostov.setLongitude(39.701505);
    Seul.setLatitude(130.60691);
    Seul.setLongitude(34.280533);
    }

    @Test
    public void yamalParseKml() throws IOException, XmlPullParserException {
        assertTrue(poligons.getSize() > 0);
        System.out.println(poligons.toString());
    }

    @Test
    public void isIncludeTest() {
        Poligon poligon = poligons.getLucht(4);
        Location C = new Location("GPS");
        C = Seul;
        assertFalse(poligon.isInclude(C));
        assertTrue(poligons.getIncludePoligons(C).size() == 0);
        C = Rostov;
        assertTrue(poligon.isInclude(C));
        assertTrue(poligons.getIncludePoligons(C).size() > 0);
    }

    @Test
    public void getPowerMax() {
    Location C = Rostov;
    assertNotNull(poligons.getMaxPowePoligon(C).getPower());
    }

}
