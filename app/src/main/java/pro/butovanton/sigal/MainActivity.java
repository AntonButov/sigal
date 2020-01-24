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
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements satselect.OnFragmentInteractionListener, ActionBar.TabListener {

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

        // Swipe viewpager when respective page selected
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // When changing the page make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

        fragmentManager = getSupportFragmentManager();
        msatselect = new satselect();

        ArrayList<satellite> satelittes = new ArrayList<satellite>();
        satelittes.add(new satellite(R.drawable.item_212, "Спутниковый интернет SenSat 2 Вт","Скорость до 45 мб/с Оптимальный вариант для северных широт и коллективного доступа."));
        satelittes.add(new satellite(R.drawable.tricolor532,"Триколор двойной GS B532M (Сибирь)","Комплект оборудования с цифровым приёмником GS B532M предназначен для просмотра спутниковых и онлайн каналов Триколор в формате стандартной и высокой (HD)(1) четкости. "));
        satadapter msatadapter = new satadapter(satelittes);

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
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
