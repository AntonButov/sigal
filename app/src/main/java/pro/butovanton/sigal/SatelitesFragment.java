package pro.butovanton.sigal;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SatelitesFragment extends Fragment {

 //  JSONPlaceHolderApi jsonPlaceHolderApi;
 //  NetworkService networkService;

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
        satelittes = new Satelittes();
        msatadapter = new satadapterinfo(satelittes.getSatelites());
        recyclerViewSat.setAdapter(msatadapter);

    /*    networkService = NetworkService.getInstance();
        jsonPlaceHolderApi = networkService.getJSONApi();
            jsonPlaceHolderApi.getShops().enqueue(new Callback<JsonArray>() {
                @Override
                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                    Log.d("DEBUG", "respons ok");
                }

                @Override
                public void onFailure(Call<JsonArray> call, Throwable t) {
                    Log.d("DEBUG", t.toString());

                }
            });

        jsonPlaceHolderApi.getShop().enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Log.d("DEBUG", "respons ok");
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.d("DEBUG", t.toString());

            }
        });*/
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
     //   Log.d("DEBUG", "Resume");
        msatadapter.notifyDataSetChanged();
    }
}

