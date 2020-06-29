package pro.butovanton.sigal;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class SatelitesFragment extends Fragment implements ItemClickListener {

    RecyclerView recyclerViewSat;
    satadapterinfo msatadapter;
    public static Satelittes satelittes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.satelites, container, false);
        recyclerViewSat = view.findViewById(R.id.my_recycler_view);

        recyclerViewSat.setLayoutManager(new LinearLayoutManager(getContext()));
        try {
            satelittes = new Satelittes(getContext());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        msatadapter = new satadapterinfo(satelittes.getSatelites(), this::OnClick);
        recyclerViewSat.setAdapter(msatadapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        msatadapter.notifyDataSetChanged();
    }


    @Override
    public void OnClick(int id) {
        Intent intent = new Intent(getContext(), camera.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("sat", id);
        getActivity().startActivity(intent);
    }
}

