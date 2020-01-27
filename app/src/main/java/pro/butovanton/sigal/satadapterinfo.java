package pro.butovanton.sigal;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
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

    public class satViewHolder extends RecyclerView.ViewHolder  {
        TextView name;
        TextView description;
        Button satelittefinder;

        public satViewHolder(@NonNull final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
            satelittefinder = (Button) itemView.findViewById(R.id.dost);
            satelittefinder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                                  Intent intent = new Intent(v.getContext(),camera.class);
                                  intent.putExtra("name",satelitteinfos.get(getAdapterPosition()).getname());
                                  intent.putExtra("azimut", azimuthsat((float) MainActivity.longitude, (float) MainActivity.land, (float) satelitteinfos.get(getAdapterPosition()).getConer()));
                                  intent.putExtra("coner", conerplace((float) MainActivity.longitude, (float) MainActivity.land, (float) satelitteinfos.get(getAdapterPosition()).getConer()));
                                  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                  v.getContext().startActivity(intent);

                }
            });
      //      itemView.setOnClickListener(new View.OnClickListener() {
    //            @Override
   //             public void onClick(View v) {
    //                Log.d("DEBUG", "OnClickListener, Posittion="+getAdapterPosition());
    //            }
   //         });
        }

        //где g1 - долгота спутника, g2 - долгота места приема, v - широта места приема.
        private float conerplace(float g2, float v, float g1) {
            g2 = (float) toRadians(g2);
            g1 = (float) toRadians(g1);
            v = (float) toRadians(v);
            return (float) toDegrees(Math.atan((cos(g2-g1)*cos(v)-0.151)/sqrt(1-pow(cos(g2-g1),2)*pow(cos(v),2))));
        }

        private float azimuthsat(float g2, float v, float g1) {
            g2 = (float) toRadians(g2);
            g1 = (float) toRadians(g1);
            v = (float) toRadians(v);
            return (float) (180 + toDegrees(atan(tan(g2-g1)/sin(v))));
        }
    }

    @NonNull
    @Override
    public satViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_sput, parent, false);
        return new satViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull satViewHolder holder, int position) {
        holder.name.setText(satelitteinfos.get(position).getname());
        holder.description.setText(satelitteinfos.get(position).getdescription());
    }



    @Override
    public int getItemCount() {
        return satelitteinfos.size();
    }




}

