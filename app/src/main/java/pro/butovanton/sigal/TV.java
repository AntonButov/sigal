package pro.butovanton.sigal;


import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TV extends Fragment implements satselect.OnFragmentInteractionListener {

    RecyclerView recyclerView;
    satselect msatselect;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        msatselect = new satselect();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tv, container, false);
        recyclerView = view.findViewById(R.id.my_recycler_view);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onLongClick(View child, int childPosition) {

            }

            @Override
            public void onClick(View child, int childPosition) {
                Log.d("DEBUG", "Click childPosittion="+childPosition);

                //           if (fragmentManager.getFragments().size() == 0) {
       //         fragmentTransaction = fragmentManager.beginTransaction();
       //         fragmentTransaction.add(R.id.fcontainer, msatselect);
        //        fragmentTransaction.addToBackStack("satfragment");
      //          fragmentTransaction.commit();
                //    }
                Fragment satcelect = new satselect();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fcontainer, satcelect);
                fragmentTransaction.addToBackStack("satfragment");
                fragmentTransaction.commit();
            }
        }));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<satellite> satelittes = new ArrayList<satellite>();
        satelittes.add(new satellite(R.drawable.item_212, "Спутниковый интернет SenSat 2 Вт","Скорость до 45 мб/с Оптимальный вариант для северных широт и коллективного доступа."));
        satelittes.add(new satellite(R.drawable.ht11small1100,"Модем НТ1100 (JUPITER Hughes) Sensat","НТ1100 (JUPITER Hughes) – высокопроизводительный широкополосный спутниковый маршрутизатор нового поколения с функцией передачи данных  в  многолучевых  спутниковых системах  Ка-диапазона со спутника АМ6"));
        satelittes.add(new satellite(R.drawable.internetpossmall, "Спутниковый интернет для посёлков","Спутниковый интернет коллективного пользования для удалённых посёлков.\n" +
                "\n" +
                "Состав:\n" +
                "\n" +
                "1.МССС (малая станция спутниковой связи)\n" +
                "2. WI-FI передатчик с радиусом действия до 3км "));
        satelittes.add(new satellite(R.drawable.ht11small1100, "Модем НТ1100 (JUPITER Hughes) Радуга Интернет","НТ1100 (JUPITER Hughes) – высокопроизводительный широкополосный спутниковый маршрутизатор нового поколения с функцией передачи данных  в  многолучевых  спутниковых системах  Ка-диапазона со спутника АМ6."));
        satadapter msatadapter = new satadapter(satelittes);
        recyclerView.setAdapter(msatadapter);
        return view;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

