package pro.butovanton.sigal;
import android.graphics.Point;
import android.location.Location;

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

    public boolean isInclude(Location C) {
        boolean res = true;
        Location A = null;
        boolean firstPlus = isPositionPlus(points.get(0), points.get(1), C);
        for (Location B : points) {
            if (A != null) {
               Boolean position = isPositionPlus(A, B, C);
            if (firstPlus) {
                if (position) ;
                else return false;
            }
            else if (!position) ;
                 else return false;
            }
            A = B;
        }
        return res;
    }

    public boolean isPositionPlus( Location A, Location B, Location C) {
        Double a1, a2, k1,k2;
        k1 = K(A,B);
        k2 = K(A,C);
        a1 = Math.atan(k1);
        a2 = Math.atan(k2);
            if (a2 - a1 < Math.PI && a2 - a1 > 0) return true;
            else return false;
    }

    public List<Location> getPoints() {
        return points;
    }

    private Double K(Location A, Location B) {
        Double x1,x,y1,y;
        y = A.getLatitude();
        x = A.getLongitude();
        y1= B.getLatitude();
        x1= B.getLongitude();
        return (y1 - y)/(x1 - x);
    }

    @Override
    public String toString() {
        return "Poligon{" +
                "power=" + power +
                ", lucht=" + lucht +
                ", points=" + points +
                '}';
    }
}
