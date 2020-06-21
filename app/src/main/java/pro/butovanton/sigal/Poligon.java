package pro.butovanton.sigal;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class Poligon {

    private int power;
    private int lucht;
    private List<Point> points = new ArrayList<>();

    public void setLucht(int lucht) {
        this.lucht = lucht;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getLucht() {
        return lucht;
    }

    public int getPower() {
        return power;
    }

    public List<Point> getPoints() {
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
