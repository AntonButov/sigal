package pro.butovanton.sigal;
import android.graphics.Point;
import android.location.Location;

import org.w3c.dom.DOMImplementation;

import java.util.ArrayList;
import java.util.List;

public class Poligon {

    private float power;
    private int lucht;
    private List<Location> points = new ArrayList<>();

    public void setLucht(int lucht) {
        this.lucht = lucht;
    }

    public void setPoints(List<Location> points) {
        this.points = points;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public int getLucht() {
        return lucht;
    }

    public float getPower() {
        return power;
    }

    public List<Location> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Poligon{" +
                "power=" + power +
                ", lucht=" + lucht +
                ", points=" + points +
                '}';
    }

    public boolean isInclude(Location point) {
        boolean res = false;
        Double pointX = point.getLongitude();
        Double pointY = point.getLatitude();
        int resCount = 0;
        Location pointPrev = points.get(0);
        for (int i = 1 ; i < points.size(); i++) {
            Location pointThis = points.get(i);
            if (isCross(point, pointPrev,pointThis))
                resCount ++ ;
            pointPrev = pointThis;
        }
        Location pointBegin = points.get(0);
        Location pointEnd = points.get(points.size()-1);
        if (isCross(point, pointBegin,pointEnd))
            resCount ++ ;
        if (resCount == 1)
            res = true;
        return res;
    }

    public boolean isCross(Location point, Location pointPrev, Location pointThis) {
        boolean res = false;
        Double x0 = point.getLongitude();
        Double y0 = point.getLatitude();
        Double xA = pointPrev.getLongitude();
        Double yA = pointPrev.getLatitude();
        Double xB = pointThis.getLongitude();
        Double yB = pointThis.getLatitude();
        if (x0 < xA && x0 <= xB)
            res = (yA < y0 && yB >= y0) || (yB < y0 && yA >=y0);
        if (res == true)
                if (lucht == 30)
                    if (res == true) ;
        return res;
    }
}
