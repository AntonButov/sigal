package pro.butovanton.sigal;

import android.location.Location;

import java.util.ArrayList;
import java.util.List;

public class Poligons {
    private List<Poligon> poligons;

    public Poligons(List<Poligon> poligons) {
        this.poligons = poligons;
    }

    public List<Poligon> getIncludePoligons(Location C) {
        List<Poligon> poligonsInclude = new ArrayList<>();
        for (Poligon poligon : poligons)
            if (poligon.isInclude(C))
                    poligonsInclude.add(poligon);
        System.out.println("Point lat +" + C.getLatitude() + " lon " + C.getLongitude() + " include " + poligonsInclude.size());
        return poligonsInclude;
    }

    public Poligon getMaxPowePoligon(Location C) {
        Poligon poligonMaxPower = null;
        List<Poligon> poligons = getIncludePoligons(C);
        if (poligons.size() > 0) {
            poligonMaxPower = poligons.get(0);
            for (Poligon poligon : poligons)
                if (poligonMaxPower.getPower() < poligon.getPower())
                    poligonMaxPower = poligon;
        }
        return poligonMaxPower;
        }

    public Poligon getLucht(int lucht) {
        for (Poligon poligon : poligons)
            if (poligon.getLucht() == lucht)
                return poligon;
    return null;
    }

    public int getSize() {
        return poligons.size();
    }

}
