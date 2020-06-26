package pro.butovanton.sigal;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.location.Location;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private Context context;

    private List<Poligon> poligons = new ArrayList<>();

    public Parser(Context context) throws IOException, XmlPullParserException {
        this.context = context;
        poligons = parse();
    }

    public List<Poligon> getPoligons() {
        return poligons;
    }

    public List<Poligon> parse() throws XmlPullParserException, IOException {

        XmlResourceParser parser = context.getResources().getXml(R.xml.yamal);

        List<Poligon> poligons = new ArrayList();

        while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() == XmlPullParser.START_TAG) {
                if (parser.getName().equals("Placemark")) {
                    parser.next();
                    parser.next();
                    String haracteristic = parser.getText();
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
                 poligons.add(poligon);
                }
            }
            parser.next();
        }
        return poligons;
    }

    private float getPower(String string) {
       char[] chars = string.toCharArray();
       String returnString = "";
        for (int i = 0; i < chars.length && chars[i] != ' '; i++)
            returnString = returnString + chars[i];
        returnString = returnString.replace(",", ".");
       return Float.parseFloat(returnString);
    }

    private int getLuch(String string) {
        char[] chars = string.toCharArray();
        String returnString = "";
        int i = 0;
        for ( ; i < chars.length && chars[i] != 'ч'; i++) ;
        i ++;
        for ( ; i < chars.length && chars[i] != ')'; i++)
            returnString = returnString + chars[i];
        returnString = returnString.replace(",", ".");
        return Integer.parseInt(returnString);
    }

    private List<Location> getPoints(String points) {
        List<Location> pointsList = new ArrayList<>();
        char[] chars = points.toCharArray();
        int i = 0;
        while (i < points.length() -1) {
            String point = "";
            for ( ; i < points.length() && chars[i] != '\n' ; i++)
                point = point + chars[i];
            if (i < points.length()) i++;
            pointsList.add(parsePoint(point));
        }
        return pointsList;
    }

    private Location parsePoint(String pointS) {
        Location point = new Location("GPS");
        point.setLongitude(parseLatitude(pointS));//переворчиваем лат и лон
        point.setLatitude(parseLongitude(pointS));
        return point;
    }

    private double parseLatitude(String pointS) {
        char[] chars = pointS.toCharArray();
        String lat = "";
        int i = 0;
        for (; i < chars.length && chars[i] != ','; i++)
            lat = lat + chars[i];
        return Double.parseDouble(lat);
    }

    private double parseLongitude(String pointS) {
        char[] chars = pointS.toCharArray();
        int i = 0;
        for (; i < chars.length && chars[i] != ','; i++) ;
        i++;
        return parseLatitude(pointS.substring(i));
    }
}









