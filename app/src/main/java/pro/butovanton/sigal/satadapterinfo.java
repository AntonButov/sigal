package pro.butovanton.sigal;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
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

    Button satelittefinder, consultantcall;

    public satadapterinfo(ArrayList<satelliteinfo> satelitteinfos) {
        this.satelitteinfos = satelitteinfos;
    }

    public class satViewHolder extends RecyclerView.ViewHolder  {
        TextView name;
        TextView description;


        public satViewHolder(@NonNull final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
            satelittefinder = (Button) itemView.findViewById(R.id.dost);
            int i = getAdapterPosition();
            Log.d("DEBUG","getadapterposition= "+i);
            satelittefinder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                                  Intent intent = new Intent(v.getContext(),camera.class);
                                  intent.putExtra("name",satelitteinfos.get(getAdapterPosition()).getname());
                                  intent.putExtra("azimut", (int)azimuthsat((float) MainActivity.longitude, (float) MainActivity.land, (float) satelitteinfos.get(getAdapterPosition()).getConer()));
                                  intent.putExtra("coner", (int)conerplace((float) MainActivity.longitude, (float) MainActivity.land, (float) satelitteinfos.get(getAdapterPosition()).getConer()));
                                  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                  v.getContext().startActivity(intent);

               }
               });
            consultantcall = (Button) itemView.findViewById(R.id.call);
            consultantcall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:89632667744"));
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
        Log.d("DEBUG", "position: "+position);
             if (conerplace((float) MainActivity.longitude, (float) MainActivity.land, (float) satelitteinfos.get(position).getConer()) < 0) satelittefinder.setVisibility(View.GONE);
        holder.name.setText(satelitteinfos.get(position).getname());
        holder.description.setText(satelitteinfos.get(position).getdescription());
    }



    @Override
    public int getItemCount() {
        return satelitteinfos.size();
    }
}

