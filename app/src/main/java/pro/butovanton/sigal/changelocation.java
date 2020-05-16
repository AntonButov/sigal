package pro.butovanton.sigal;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class changelocation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changelocation);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setAllGesturesEnabled(true);
        uiSettings.setCompassEnabled(true);
        //uiSettings.setMyLocationButtonEnabled(true);
       // mMap.setMyLocationEnabled(true);
        uiSettings.setZoomControlsEnabled(true);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.d("DEBUG", "Lat="+latLng.latitude+" long="+latLng.longitude);
                addMarker(latLng);
                MainActivity.lantitude = (int) latLng.latitude;
                MainActivity.longitude = (int) latLng.longitude;
            }
        });
               // Add a marker in Sydney and move the camera
        LatLng location = new LatLng(MainActivity.lantitude, MainActivity.longitude);
        addMarker(location);
       // mMap.setLatLngBoundsForCameraTarget(mMap.getCameraPosition());
    }

    @Override
    protected void onResume() {
        super.onResume();
  //      mMap.
//        LatLng location = new LatLng(MainActivity.lantitude, MainActivity.longitude);
 //       addMarker(location);
    }

    void addMarker (LatLng latLng) {
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(latLng));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}
