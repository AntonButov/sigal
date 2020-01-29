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

public class satelites extends Fragment implements satselect.OnFragmentInteractionListener {

    RecyclerView recyclerViewSat;
    private static int s;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.satelites, container, false);
        recyclerViewSat = view.findViewById(R.id.my_recycler_view);

        recyclerViewSat.addOnItemTouchListener(new RecyclerTouchListenerSat(this,recyclerViewSat, new RecyclerTouchListenerSat.ClickListener() {

            @Override
            public void onLongClick(View child, int childPosition) {

            }

            @Override
            public void onClick(View child, int childPosition) {

            }
        }));
            recyclerViewSat.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<satelliteinfo> satelitteinfos = new ArrayList<satelliteinfo>();
        satelitteinfos.add(new satelliteinfo("Express-AM3 140°E","На этой позиции стоит спутник предназначений для покрытия дальнего востока России. Имеет Ku и C диапазоны. Идет вещание федеральных и региональных российских каналов, а также платные пакеты VIVA - Восточный Экспресс и канал Наш Футбол.",140));
        satelitteinfos.add(new satelliteinfo("Yamal 402, 55° в.д.","Российский спутник на борту которого только открытые каналы из России в разных часовых поясах такие как НТВ, Рен ТВ, ТНТ, Ю, Дисней и тд. Спутник вещает только в Ku диапазоне.",55));
        satelitteinfos.add(new satelliteinfo("Horizons-2 / IS-15, 85.2° в.д.","Данный спутник транслирует в основном каналы платного российского пакета \"Континент ТВ\" и \"Телекарта ТВ\" и почти 20 российских каналов в открытом виде. Вещает только в Ku диапазоне в форматах MPEG4/MPEG2. Так же на данном спутнике присутствуют каналы \"СТС медиа\" в условной кодировке \"BISS\".",85));
        satelitteinfos.add(new satelliteinfo("ABS 1 /1A /1B, 75° в.д.","Популярная среди русскоговорящих зрителей спутниковая позиция. Много российских каналов в открытом виде, а также каналы платного российского оператора \"МТС-ТВ\". Зона покрытия почти вся территория Евразии. Вещает только в Ku диапазоне.",75));
        satelitteinfos.add(new satelliteinfo("ЯМАЛ 201 / 300K, 90° в.д.","Один и популярных российских спутников который вещает довольно хороший пакет российских федеральных телеканалов в открытом виде (ТНТ, Перец, РЕН-ТВ, Домашний, Россия2, Россия24, НТВ, Звезда, ТВ3, СТС и тд...). Несколько каналов из Туркмении и Казахстана. Охватывает почти всю территорию России и страны СНГ. Диапазоны С и Ku.",90));
        satadapterinfo msatadapter = new satadapterinfo(satelitteinfos);
        recyclerViewSat.setAdapter(msatadapter);
        return view;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
