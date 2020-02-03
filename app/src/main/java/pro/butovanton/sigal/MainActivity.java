package pro.butovanton.sigal;

import android.Manifest;
import android.animation.Animator;
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
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public static int longitude, land;

    private ViewPager viewPager;
    private TabsAdapter tabsAdapter;
    private ActionBar actionBar;

    // Tabs title
    private String[] tabs = {"Магазин", "Спутники"};

    private final int MY_REQUEST_LOCATION = 115;
    Location location;

    private WebView mWebView;

    FloatingActionButton fab, fabcall, fabsend;
    LinearLayout fabLayoutcall, fabLayoutsend;
    View fabBGLayout;

    boolean isFABOpen = false;

    @Override
    public void onBackPressed() {
        //  super.onBackPressed();
        Log.d("DEBUG", "onBackActivity");
        FragmentManager fm = getSupportFragmentManager();
        OnBackPressedListener backPressedListener = null;
        for (Fragment fragment : fm.getFragments()) {
            if (fragment instanceof OnBackPressedListener) {
                backPressedListener = (OnBackPressedListener) fragment;
                break;
            }
        }

        if (backPressedListener != null && backPressedListener.onBackPressed()) {
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialization
        viewPager = (ViewPager) findViewById(R.id.pager);
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

        fabLayoutcall = (LinearLayout) findViewById(R.id.fabLayoutcall);
        fabLayoutsend = (LinearLayout) findViewById(R.id.fabLayoutsend);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fabcall = (FloatingActionButton) findViewById(R.id.fabcall);
        fabsend = (FloatingActionButton) findViewById(R.id.fabsend);
        fabcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:89632667744"));
                v.getContext().startActivity(intent);
            }
        });
        fabsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("smsto:" + "89632667744");
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO, uri);
                sendIntent.setPackage("com.whatsapp");
                startActivity(sendIntent);
            }
        });
        //fabBGLayout = findViewById(R.id.fabBGLayout);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFABOpen) {
                    showFABMenu();
                } else {
                    closeFABMenu();
                }
            }
        });

        fragmentManager = getSupportFragmentManager();
        }

    private void showFABMenu() {
        isFABOpen = true;
        fabLayoutcall.setVisibility(View.VISIBLE);
        fabLayoutsend.setVisibility(View.VISIBLE);

       // fabBGLayout.setVisibility(View.VISIBLE);
        fab.animate().rotationBy(180);
        fabLayoutcall.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fabLayoutsend.animate().translationY(-getResources().getDimension(R.dimen.standard_100));
     }

    private void closeFABMenu() {
        isFABOpen = false;
      //  fabBGLayout.setVisibility(View.GONE);
        fab.animate().rotation(0);
        fabLayoutcall.animate().translationY(0);
        fabLayoutsend.animate().translationY(0);
        fabLayoutsend.animate().translationY(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (!isFABOpen) {
                    fabLayoutcall.setVisibility(View.GONE);
                    fabLayoutsend.setVisibility(View.GONE);
                }
/*                if (fab.getRotation() != -180) {
                    fab.setRotation(-180);
                }*/
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

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

    //   fragmentManager = getSupportFragmentManager();

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
