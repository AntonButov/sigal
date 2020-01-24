package pro.butovanton.sigal;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements satselect.OnFragmentInteractionListener, ActionBar.TabListener {

    RecyclerView recyclerView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    satselect msatselect;

    private ViewPager viewPager;
    private TabsAdapter tabsAdapter;
    private ActionBar actionBar;

    // Tabs title
    private String[] tabs = {"Android", "Ios"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialization
        viewPager = (ViewPager)findViewById(R.id.pager);
        actionBar = getSupportActionBar();
        tabsAdapter = new TabsAdapter(getSupportFragmentManager());

        viewPager.setAdapter(tabsAdapter);
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);*/
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
        }

        fragmentManager = getSupportFragmentManager();
        msatselect = new satselect();
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onLongClick(View child, int childPosition) {

            }

            @Override
            public void onClick(View child, int childPosition) {
                Log.d("DEBUG", "Click childPosittion="+childPosition);
     //           if (fragmentManager.getFragments().size() == 0) {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.fcontainer, msatselect);
                    fragmentTransaction.addToBackStack("satfragment");
                    fragmentTransaction.commit();
            //    }
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

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
