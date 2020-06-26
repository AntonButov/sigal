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
        List<Poligon> poligons = new ArrayList<>();
        for (Poligon poligon : poligons)
            if (poligon.isInclude(C))
                    poligons.add(poligon);
        return poligons;
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
