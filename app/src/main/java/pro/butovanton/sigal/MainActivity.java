package pro.butovanton.sigal;

import android.Manifest;
import android.animation.Animator;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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

import androidx.annotation.NonNull;
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


import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {

 //   public static FirebaseAnalytics mFirebaseAnalytics;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public static double longitude, lantitude;

    private ViewPager viewPager;
    private TabsAdapter tabsAdapter;
    private ActionBar actionBar;

    // Tabs title
    private String[] tabs = {"Спутники", "Оборудование"};

    private final int MY_REQUEST_LOCATION = 115;

    private LocationClass locationClass;

    FloatingActionButton fab, fabcall, fabsendwats, fabsendviber, fabsat;
    LinearLayout fabLayoutcall, fabLayoutsendwats, fabLayoutsendviber;

    boolean isFABOpen = false;

    String ver;

    Timer timer;
    TimerTask mTimerTask;
    Handler handler=new Handler();

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

        locationClass = new LocationClass(getApplicationContext());

        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        ver = packageInfo.versionName ;

        Bundle bundle = new Bundle();
        bundle.putString("api", String.valueOf(Build.VERSION.SDK_INT));
        bundle.putString("ver", ver);

        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getSupportActionBar();
        tabsAdapter = new TabsAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabsAdapter);
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);*/
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // Swipe viewpager when respective page selected
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
                if (fabsat != null) {
                    if (position == 0) {
                        fabsat.setAlpha(0f);
                        fabsat.setVisibility(View.VISIBLE);
                        fabsat.animate()
                                .alpha(1f)
                                .setDuration(1000);
                        // .setListener(null);
                    }
                    else fabsat.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
     //           Log.d("DEBUG","scrolled");
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
       //         Log.d("DEBUG","scrolledchang");
            }
        });

        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
        }

        fabLayoutcall = (LinearLayout) findViewById(R.id.fabLayoutcall);
        fabLayoutsendwats = (LinearLayout) findViewById(R.id.fabLayoutsendwats);
        fabLayoutsendviber = (LinearLayout) findViewById(R.id.fabLayoutsendviber);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fabcall = (FloatingActionButton) findViewById(R.id.fabcall);
        fabsendwats = (FloatingActionButton) findViewById(R.id.fabsendwats);
        fabcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:89632667744"));
                v.getContext().startActivity(intent);
            }
        });
        fabsendwats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appInstalledOrNot("com.whatsapp")) {
                   Uri uri = Uri.parse("https://api.whatsapp.com/send?phone=79632667744");
                   Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
                   startActivity(sendIntent);
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Whatsapp не установлен.", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        fabsendviber = (FloatingActionButton) findViewById(R.id.fabsendviber);
        fabsendviber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appInstalledOrNot("com.viber.voip")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("viber://add?number=79632667744")));
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Viber не установлен.", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
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
        fabsat = findViewById(R.id.fabsat);
        //fabsat.setVisibility(View.GONE);
        fabsat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                         Intent intent = new Intent(v.getContext(), camera.class);
                         intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                         v.getContext().startActivity(intent);
            }
        });

        fragmentManager = getSupportFragmentManager();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                getlocation();        }
        else
        getlocation();

        repeatAnim();
    }

    public void repeatAnim(){
        timer=new Timer();
        mTimerTask=new TimerTask(){
            public void run(){
                handler.post(new Runnable(){
                    public void run(){
                        repeatAnimation();

                    }
                });
            }
        };
        timer.schedule(mTimerTask, 1000,2000);
    }
    public void repeatAnimation(){
     //   fabsat.clearAnimation();
        fabsat.animate().rotationBy(360);
    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    private void showFABMenu() {
        isFABOpen = true;
        fabLayoutcall.setVisibility(View.VISIBLE);
        fabLayoutsendwats.setVisibility(View.VISIBLE);
        fabLayoutsendviber.setVisibility(View.VISIBLE);

       // fabBGLayout.setVisibility(View.VISIBLE);
        fab.animate().rotationBy(180);
        fabLayoutcall.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fabLayoutsendwats.animate().translationY(-getResources().getDimension(R.dimen.standard_100));
        fabLayoutsendviber.animate().translationY(-getResources().getDimension(R.dimen.standard_145));
     }

    private void closeFABMenu() {
        isFABOpen = false;
        //  fabBGLayout.setVisibility(View.GONE);
        fab.animate().rotation(0);
        fabLayoutcall.animate().translationY(0);
        fabLayoutsendwats.animate().translationY(0);
        fabLayoutsendviber.animate().translationY(0);
        fabLayoutsendwats.animate().translationY(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (!isFABOpen) {
                    fabLayoutcall.setVisibility(View.GONE);
                    fabLayoutsendwats.setVisibility(View.GONE);
                    fabLayoutsendviber.setVisibility(View.GONE);
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
        //    Intent intent = new Intent(this, changelocation.class);
            Intent intent = new Intent(this, MapActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        if (id == R.id.navigation_dashboard) {
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
            }// else getlocation();
        }
       // else getlocation();
    }

    private void getlocation() {
        Location location = locationClass.getlocation();
        longitude = location.getLongitude();
        lantitude = location.getLatitude();
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
