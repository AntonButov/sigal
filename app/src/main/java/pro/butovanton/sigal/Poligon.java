package pro.butovanton.sigal;
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
}
