package pro.butovanton.sigal;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class internet extends Fragment implements satselect.OnFragmentInteractionListener {

    RecyclerView recyclerViewinet;
    satselect msatselect;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        msatselect = new satselect();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tv, container, false);
        recyclerViewinet = view.findViewById(R.id.my_recycler_view);

        recyclerViewinet.addOnItemTouchListener(new RecyclerTouchListenerinet(this,recyclerViewinet, new RecyclerTouchListenerinet.ClickListener() {
            @Override
            public void onLongClick(View child, int childPosition) {

            }

            @Override
            public void onClick(View child, int childPosition) {
                Fragment satcelect = new satselect();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fcontainer, satcelect);
                fragmentTransaction.addToBackStack("satfragment");
                fragmentTransaction.commit();
            }
        }));
        recyclerViewinet.setLayoutManager(new LinearLayoutManager(getContext()));
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
        recyclerViewinet.setAdapter(msatadapter);
        return view;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
