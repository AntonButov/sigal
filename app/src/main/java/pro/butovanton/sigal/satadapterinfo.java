package pro.butovanton.sigal;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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

        public satViewHolder(@NonNull final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("DEBUG", "OnClickListener, Posittion="+getAdapterPosition());
                }
            });
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

