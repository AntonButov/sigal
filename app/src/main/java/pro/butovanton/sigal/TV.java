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

public class TV extends Fragment {

    RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

            }
        }));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<satellite> satelittes = new ArrayList<satellite>();
        satelittes.add(new satellite(R.drawable.tricolor532, "Триколор двойной GS B532M (Сибирь)","Комплект оборудования с цифровым приёмником GS B532M предназначен для просмотра спутниковых и онлайн каналов Триколор в формате стандартной и высокой (HD)(1) четкости."));
        satelittes.add(new satellite(R.drawable.telekarta,"комплект спутникового ТВ Телекарта ( подписка на три года)","HD-комплект спутникового телевидения «Телекарта» — это выбор прагматичных."));
        satelittes.add(new satellite(R.drawable.tricolor532, "Триколор с приёмниками GS B532M и C592 (Сибирь)","Полностью готовое решение для просмотра спутниковых и онлайн каналов Триколор в формате стандартной и высокой (HD)(1) четкости на двух телевизорах."));
        satelittes.add(new satellite(R.drawable.mts, "Спутниковое ТВ от МТС","Даже в самых дальних уголках России. Свыше 190 каналов, включая 35 в HD Легко подключить где угодно: в загородном доме, на даче, в квартир"));
        satadapter msatadapter = new satadapter(satelittes);
        recyclerView.setAdapter(msatadapter);
        return view;
    }

}

