package pro.butovanton.sigal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class viewsat {
    public ImageView imageSat;
    private TextView textView;

    public float azimutsat;
    public float conerplacesat;
    public boolean side;

    public viewsat(Context context, ViewGroup parrent, float azimutsat, float conerplacesat, String name) {

        imageSat = new ImageView(context);
        imageSat.setImageResource(R.drawable.satellitsmart);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
        imageSat.setLayoutParams(layoutParams);
        parrent.addView(imageSat);
        textView = new TextView(context);
        textView.setText(name);
        parrent.addView(textView);
        this.azimutsat = azimutsat;
        this.conerplacesat = conerplacesat;
    }


    public int getWight() {
        return imageSat.getWidth();
    }

    public int getHeight() {
        return imageSat.getHeight();
    }

    public float getAzimut() {
        return this.azimutsat;
    }

    public float getConerplace() {
        return this.conerplacesat;
    }

    public boolean getside() { return this.side; }

    public void setX(float x) {
        imageSat.setX(x);
        if (this.side == true) textView.setX(x+imageSat.getWidth());
        else textView.setX(x-textView.getWidth());
    }

    public void setY(float y) {
        imageSat.setY(y);
        textView.setY(y+imageSat.getWidth()/2);
    }

    public void setside(boolean side) { this.side = side; }
}
