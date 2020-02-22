package pro.butovanton.sigal;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

public class viewsat {
    public ImageView imageSat;
    public float azimutsat;
    public float conerplacesat;

    public viewsat(Context context, ViewGroup parrent, float azimutsat, float conerplacesat) {
        imageSat = new ImageView(context);
        imageSat.setImageResource(R.drawable.satellitsmart);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200, 200);
        imageSat.setLayoutParams(layoutParams);
        parrent.addView(imageSat);
        this.azimutsat = azimutsat;
        this.conerplacesat = conerplacesat;
    }

}
