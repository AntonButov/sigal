package pro.butovanton.sigal;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static androidx.camera.core.CameraX.getActiveUseCases;
import static androidx.camera.core.CameraX.getContext;
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

    private ArrayList<satelliteinfo> satelitteinfos;
    private RecyclerView.OnItemTouchListener mListener;

     public satadapterinfo(ArrayList<satelliteinfo> satelitteinfos) {
        this.satelitteinfos = satelitteinfos;
    }

    public class satViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView satinfo;
        TextView diametr;
        TextView description;
        Button satelittefinder;


        public satViewHolder(@NonNull final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            satinfo = (TextView) itemView.findViewById(R.id.satinfivalue);
            diametr = (TextView) itemView.findViewById(R.id.danteny);
            description = (TextView) itemView.findViewById(R.id.description);
            satelittefinder = (Button) itemView.findViewById(R.id.dost);
            int i = getAdapterPosition();
         //   Log.d("DEBUG", "getadapterposition= " + i);
            satelittefinder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), camera.class);
                    intent.putExtra("name", satelitteinfos.get(getAdapterPosition()).getname());
                    intent.putExtra("azimut", (int) azimuthsat((float) MainActivity.longitude, (float) MainActivity.lantitude, (float) satelitteinfos.get(getAdapterPosition()).getConer()));
                    intent.putExtra("coner", (int) conerplace((float) MainActivity.longitude, (float) MainActivity.lantitude, (float) satelitteinfos.get(getAdapterPosition()).getConer()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    v.getContext().startActivity(intent);

                }
            });
        }
    }

        //где g1 - долгота спутника, g2 - долгота места приема, v - широта места приема.
        private float conerplace(float g2, float v, float g1) {
        //    g1 = 36;
       //     g2 = 37;
       //     v =56;
            g2 = (float) toRadians(g2);
            g1 = (float) toRadians(g1);
            v = (float) toRadians(v);
            float c1= (float) (cos(g2-g1)*cos(v)-0.151);
            float c2 = (float)(1-(cos(g2-g1)*cos(g2-g1)*cos(v)*cos(v)));
            return (float) toDegrees(Math.atan(c1/sqrt(c2)));
        }

        private float azimuthsat(float g2, float v, float g1) {
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
    //    Log.d("DEBUG", "position: "+position);
  //      int coner=  (int) conerplace((float) MainActivity.longitude, (float) MainActivity.lantitude, (float) satelitteinfos.get(holder.getAdapterPosition()).getConer());
   //     if (coner < 0) holder.satelittefinder.setVisibility(View.GONE);
  //      else holder.satelittefinder.setVisibility(View.VISIBLE);
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
        int coner=  (int) conerplace((float) MainActivity.longitude, (float) MainActivity.lantitude, (float) satelitteinfos.get(holder.getAdapterPosition()).getConer());
        getdiametr(coner);
        holder.satinfo.setText("Азимут: " + azimut + " , угол места: " + coner+ "°");
        holder.diametr.setText("Диаметр антены: " + getdiametr(coner) + " см.");
        if (coner < 0) holder.satelittefinder.setVisibility(View.GONE);
        else holder.satelittefinder.setVisibility(View.VISIBLE);
    }

    @Override
    public void onViewRecycled(@NonNull satViewHolder holder) {
        super.onViewRecycled(holder);
    //    Log.d("DEBUG","onViewRecicled");
//        if (holder.getAdapterPosition() != -1) {
//            int coner = (int) conerplace((float) MainActivity.longitude, (float) MainActivity.lantitude, (float) satelitteinfos.get(holder.getAdapterPosition()).getConer());
         //   satelittefinder.setVisibility(View.GONE);

        //}
    }

    @Override
    public int getItemCount() {
        return satelitteinfos.size();
    }
}

