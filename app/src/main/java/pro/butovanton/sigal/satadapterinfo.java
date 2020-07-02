package pro.butovanton.sigal;

import android.location.Location;
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
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.tan;
import static java.lang.Math.toDegrees;
import static java.lang.Math.toRadians;

public class satadapterinfo extends RecyclerView.Adapter<satadapterinfo.satViewHolder> {

    private List<satelliteinfo> satelitteinfos;
    private ItemClickListener itemClickListener;

     public satadapterinfo(List<satelliteinfo> satelitteinfos, ItemClickListener itemClickListener) {
        this.satelitteinfos = satelitteinfos;
        this.itemClickListener = itemClickListener;
    }

    public class satViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView satinfo;
        TextView diametr;
        TextView description;
        TextView lucht;
        TextView power;

        public satViewHolder(@NonNull final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            satinfo = (TextView) itemView.findViewById(R.id.satinfivalue);
            diametr = (TextView) itemView.findViewById(R.id.danteny);
            description = (TextView) itemView.findViewById(R.id.description);
            lucht = itemView.findViewById(R.id.lucht);
            power = itemView.findViewById(R.id.power);
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.OnClick(position);
            }
       });
    }

    @Override
    public void onViewAttachedToWindow(@NonNull satViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.name.setText(satelitteinfos.get(holder.getAdapterPosition()).getname());
        holder.description.setText(satelitteinfos.get(holder.getAdapterPosition()).getdescription());
        int azimut = (int) azimuthsat((float) MainActivity.longitude, (float) MainActivity.lantitude, (float) satelitteinfos.get(holder.getAdapterPosition()).getConer());
        int coner=  (int) conerplacesat((float) MainActivity.longitude, (float) MainActivity.lantitude, (float) satelitteinfos.get(holder.getAdapterPosition()).getConer());
        holder.satinfo.setText("Азимут: " + azimut + " , угол места: " + coner+ "°");
        String  diametr = getdiametr(coner) + " см.";
        Poligons poligons = satelitteinfos.get(holder.getAdapterPosition()).getPoligons();
        if (poligons != null) {
            Location location = new Location("GPS");

            location.setLatitude(MainActivity.lantitude);
            location.setLongitude(MainActivity.longitude);
            Poligon poligonMaxPower = satelitteinfos.get(holder.getAdapterPosition()).getPoligons().getMaxPowePoligon(location);
            if (poligonMaxPower != null) {
                holder.lucht.setVisibility(View.VISIBLE);
                holder.lucht.setText("Луч: " + poligonMaxPower.getLucht());
                holder.power.setVisibility(View.VISIBLE);
                holder.power.setText("Мощность: " + poligonMaxPower.getPower());
                diametr = getdiametr(poligonMaxPower.getPower());
            } else {
                diametr = "> 2м";
                holder.lucht.setText("Уточняйте у менеджера");
                holder.power.setVisibility(View.INVISIBLE);
            }
        }
        holder.diametr.setText("Диаметр антенны: " + diametr);
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

    public String getdiametr(float power) {
        String diametr = "";
        if (power > 62) diametr = "50";
        else if (power > 61.5) diametr = "50-60";
        else if (power > 61) diametr = "55-65";
        else if (power > 60.5) diametr = "60-67";
        else if (power > 60) diametr = "65-85";
        else if (power > 59.5) diametr = "75-95";
        else if (power > 59) diametr = "85-105";
        else if (power > 58) diametr = "95-120";
        else if (power > 57) diametr = "105-135";
        else if (power > 56) diametr = "120-150";
        else if (power > 55) diametr = "135-170";
        return diametr;
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

