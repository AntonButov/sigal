package pro.butovanton.sigal;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

public class viewsat {
    public ImageView imageSat;

    public viewsat(Context context) {
        imageSat = new ImageView(context);
        imageSat.setImageResource(R.drawable.satellitsmart);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200, 200);
        imageSat.setLayoutParams(layoutParams);
    }

}
