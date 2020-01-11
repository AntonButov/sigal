package pro.butovanton.sigal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class satadapter extends RecyclerView.Adapter<satadapter.satViewHolder> {
    ArrayList<satellite> satelittes;

    public  satadapter (ArrayList<satellite> satelittes) {
        this.satelittes = satelittes;
    }

    public class satViewHolder extends RecyclerView.ViewHolder {
        ImageView imgsat;
        TextView name;
        TextView description;

        public satViewHolder(@NonNull View itemView) {
            super(itemView);
            imgsat = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }

    @NonNull
    @Override
    public satViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new satViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull satViewHolder holder, int position) {
        holder.imgsat.setImageResource(satelittes.get(position).getImage());
        holder.name.setText(satelittes.get(position).getname());
        holder.description.setText(satelittes.get(position).getdescription());

    }

    @Override
    public int getItemCount() {
        return satelittes.size();
    }


}

