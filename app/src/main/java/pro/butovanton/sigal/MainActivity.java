package pro.butovanton.sigal;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.content.pm.PackageManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity implements satselect.OnFragmentInteractionListener, ActionBar.TabListener {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    satselect msatselect;

    public static int longitude, land;

    private ViewPager viewPager;
    private TabsAdapter tabsAdapter;
    private ActionBar actionBar;

    // Tabs title
    private String[] tabs = {"ТВ", "Интернет","Спутники"};

    private final int MY_REQUEST_LOCATION = 115;
    Location location;

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
                actionBar.setSelectedNavigationItem(position);
                Log.d("DEBUG","fragmentcount="+fragmentManager.getBackStackEntryCount());
                // When changing the page make respected tab selected
                int count = fragmentManager.getBackStackEntryCount();
                while(count > 0){
                    fragmentManager.popBackStack();
                    count--;
                }
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

  //      ArrayList<satellite> satelittes = new ArrayList<satellite>();
  //      satelittes.add(new satellite(R.drawable.item_212, "Спутниковый интернет SenSat 2 Вт","Скорость до 45 мб/с Оптимальный вариант для северных широт и коллективного доступа."));
 //       satelittes.add(new satellite(R.drawable.ht11small1100,"Модем НТ1100 (JUPITER Hughes) Sensat","НТ1100 (JUPITER Hughes) – высокопроизводительный широкополосный спутниковый маршрутизатор нового поколения с функцией передачи данных  в  многолучевых  спутниковых системах  Ка-диапазона со спутника АМ6"));

    //    ArrayList<satellite> satelittesinternet = new ArrayList<satellite>();
       // satelittesinternet.add(new satellite(R.drawable.item_212, "Спутниковый интернет SenSat 2 Вт","Скорость до 45 мб/с Оптимальный вариант для северных широт и коллективного доступа."));
     //   satelittesinternet.add(new satellite(R.drawable.tricolor532,"Триколор двойной GS B532M (Сибирь)","Комплект оборудования с цифровым приёмником GS B532M предназначен для просмотра спутниковых и онлайн каналов Триколор в формате стандартной и высокой (HD)(1) четкости. "));
         // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
    //    AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
   //             R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
    //            .build();
           }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.navigation_home) {
            Uri address = Uri.parse("http://www.сигал.com");
            Intent openlink = new Intent(Intent.ACTION_VIEW, address);
            startActivity(openlink);
        }
        if (id == R.id.navigation_dashboard) {
            PackageInfo packageInfo = null;
            try {
                packageInfo = getPackageManager().getPackageInfo(getPackageName(),0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            String ver = packageInfo.versionName ;
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Версия программы")
                    .setMessage(ver)
                    .setPositiveButton("Ok", null)
                    .show();
        }
            return true;
        }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_REQUEST_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getlocation();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_REQUEST_LOCATION);
            } else getlocation();
        } else getlocation();
    }

    void getlocation() {
            location = getLocationWithCheckNetworkAndGPS(getApplicationContext());
            if (location == null) {
                land = 56;
                longitude = 92;

            } else {
                longitude = (int) location.getLongitude();
                land = (int) location.getLatitude();
            }
            Log.d("DEBUG", "Latitude=" + land + "Longitude=" + longitude);
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

    public Location getLocationWithCheckNetworkAndGPS(Context mContext) {
        LocationManager lm = (LocationManager)
                mContext.getSystemService(Context.LOCATION_SERVICE);
        assert lm != null;
        boolean isGpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkLocationEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        android.location.Location networkLoacation = null;
        android.location.Location gpsLocation = null;
        android.location.Location finalLoc = null;
        if (isGpsEnabled)
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return null;
            }
        gpsLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (isNetworkLocationEnabled)
            networkLoacation = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if (gpsLocation != null && networkLoacation != null) {

            //smaller the number more accurate result will
            if (gpsLocation.getAccuracy() > networkLoacation.getAccuracy())
                return finalLoc =networkLoacation;
            else
                return finalLoc = gpsLocation;

        } else {

            if (gpsLocation != null) {
                return finalLoc = gpsLocation;
            } else if (networkLoacation != null) {
                return finalLoc = networkLoacation;
            }
        }
        return finalLoc;
    }
}
