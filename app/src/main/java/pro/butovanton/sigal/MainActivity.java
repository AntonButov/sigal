package pro.butovanton.sigal;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements satselect.OnFragmentInteractionListener {

    RecyclerView recyclerView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    satselect msatselect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        msatselect = new satselect();
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onLongClick(View child, int childPosition) {

            }

            @Override
            public void onClick(View child, int childPosition) {
                Log.d("DEBUG", "Click childPosittion="+childPosition);
                if (fragmentManager.getFragments().size() == 0) {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.fcontainer, msatselect);
                    fragmentTransaction.commit();
                }
            }
        }));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<satellite> satelittes = new ArrayList<satellite>();
        satelittes.add(new satellite(R.drawable.item_212, "Спутниковый интернет SenSat 2 Вт","Скорость до 45 мб/с Оптимальный вариант для северных широт и коллективного доступа."));
        satelittes.add(new satellite(R.drawable.tricolor532,"Триколор двойной GS B532M (Сибирь)","Комплект оборудования с цифровым приёмником GS B532M предназначен для просмотра спутниковых и онлайн каналов Триколор в формате стандартной и высокой (HD)(1) четкости. "));
        satadapter msatadapter = new satadapter(satelittes);
        recyclerView.setAdapter(msatadapter);

         // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d("DEBUG", "FragmentInteraction Uri:"+uri);
    }
}
