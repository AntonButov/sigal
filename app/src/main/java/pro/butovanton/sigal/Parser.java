package pro.butovanton.sigal;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.Point;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Parser {

    private Context context;

    public Parser(Context context) {
        this.context = context;
    }

    public List<Poligon> parse() throws XmlPullParserException, IOException {

        XmlResourceParser parser = context.getResources().getXml(R.xml.yamal601);

        List<Poligon> poligons = new ArrayList();

        while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() == XmlPullParser.START_TAG) {
                if (parser.getName().equals("Placemark")) {
                    parser.next();
                    parser.next();
                    String haracteristic = parser.getAttributeValue(null, "name");
                    Poligon poligon = new Poligon();
                    poligon.setPower(getPower(haracteristic));
                    poligon.setLucht(getLuch(haracteristic));
                 parser.next();
                 while (!parser.getName().equals("coordinates"))  {
                     parser.next();
                     if (parser.getName() == null)
                         parser.next();
                 }
                 parser.next();
                 String points = parser.getText();
                 poligon.setPoints(getPoints(points));
                }
            }
            parser.next();
        }
        return poligons;
    }

    private int getPower(String string) {

        return 0;
    }

    private int getLuch(String string) {

        return 1;
    }

    private List<Point> getPoints(String points) {
        List<Point> pointsList = new ArrayList<>();

        return pointsList;
    }

}









