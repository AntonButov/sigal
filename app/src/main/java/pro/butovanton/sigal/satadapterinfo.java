package pro.butovanton.sigal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.tan;
import static java.lang.Math.toDegrees;
import static java.lang.Math.toRadians;

public class satadapterinfo extends RecyclerView.Adapter<satadapterinfo.satViewHolder> {
    private static AdapterView.OnItemClickListener onItemClickListener;
    public static interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    private List<satelliteinfo> satelitteinfos;
    private RecyclerView.OnItemTouchListener mListener;

     public satadapterinfo(List<satelliteinfo> satelitteinfos) {
        this.satelitteinfos = satelitteinfos;
    }

    public class satViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView satinfo;
        TextView diametr;
        TextView description;

        public satViewHolder(@NonNull final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            satinfo = (TextView) itemView.findViewById(R.id.satinfivalue);
            diametr = (TextView) itemView.findViewById(R.id.danteny);
            description = (TextView) itemView.findViewById(R.id.description);
            int i = getAdapterPosition();
        }
    }

        //где g1 - долгота спутника, g2 - долгота места приема, v - широта места приема.
        private float conerplacesat(float longitudeplace, float lantitudeplace, float conersat) {

            float g2 = longitudeplace;
            float v = lantitudeplace;
            float g1 = conersat;
            g2 = (float) toRadians(g2);
            g1 = (float) toRadians(g1);
            v = (float) toRadians(v);
            float c1= (float) (cos(g2-g1)*cos(v)-0.151);
            float c2 = (float)(1-(cos(g2-g1)*cos(g2-g1)*cos(v)*cos(v)));
            return (float) toDegrees(Math.atan(c1/sqrt(c2)));
        }

        private float azimuthsat(float longitudeplace, float lantitudeplace, float conersat) {
            float g2 = longitudeplace;
            float v = lantitudeplace;
            float g1 = conersat;
            g2 = (float) toRadians(g2);
            g1 = (float) toRadians(g1);
            v = (float) toRadians(v);
            return (float) (180 + toDegrees(atan(tan(g2-g1)/sin(v))));
        }

    @NonNull
    @Override
    public satViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_sput, parent, false);
        return new satViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull satViewHolder holder, int position) {

    }

    public String getdiametr(int coner) {
        String diametr = "";
       if (coner < 0) diametr = "-";
           else if (coner < 10) diametr = "120";
           else if (coner < 15) diametr = "100";
           else if (coner < 20) diametr = "90";
           else if (coner < 25) diametr = "80";
           else if (coner < 90) diametr = "70";
       return diametr;
    }

    @Override
    public void onViewAttachedToWindow(@NonNull satViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    //    Log.d("DEBUG","attache"+"holder="+holder.toString());
        holder.name.setText(satelitteinfos.get(holder.getAdapterPosition()).getname());
        holder.description.setText(satelitteinfos.get(holder.getAdapterPosition()).getdescription());
        int azimut = (int) azimuthsat((float) MainActivity.longitude, (float) MainActivity.lantitude, (float) satelitteinfos.get(holder.getAdapterPosition()).getConer());
        int coner=  (int) conerplacesat((float) MainActivity.longitude, (float) MainActivity.lantitude, (float) satelitteinfos.get(holder.getAdapterPosition()).getConer());
        getdiametr(coner);
        holder.satinfo.setText("Азимут: " + azimut + " , угол места: " + coner+ "°");
        holder.diametr.setText("Диаметр антенны: " + getdiametr(coner) + " см.");
    }

    @Override
    public void onViewRecycled(@NonNull satViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return satelitteinfos.size();
    }
}

