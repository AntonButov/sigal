package pro.butovanton.sigal;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.InputListener;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.VisibleRegionUtils;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.search.Response;
import com.yandex.mapkit.search.SearchFactory;
import com.yandex.mapkit.search.SearchManager;
import com.yandex.mapkit.search.SearchManagerType;
import com.yandex.mapkit.search.SearchOptions;
import com.yandex.mapkit.search.Session;
import com.yandex.runtime.Error;
import com.yandex.runtime.image.ImageProvider;
import com.yandex.runtime.network.NetworkError;
import com.yandex.runtime.network.RemoteError;

/**
 * This is a basic example that displays a map and sets camera focus on the target location.
 * Note: When working on your projects, remember to request the required permissions.
 */
public class MapActivity extends Activity implements Session.SearchListener {
    /**
     * Replace "your_api_key" with a valid developer key.
     * You can get it at the https://developer.tech.yandex.ru/ website.
     */
    private Point LOCATION;

    private MapView mapView;
    private EditText searchEdit, searchEditLat, searchEditLng;
    private SearchManager searchManager;
    private Session searchSession;
    private Switch aSwitch;
    private Button buttomSetupLockation;

    private void submitQuery(String query) {
        searchSession = searchManager.submit(
                query,
                VisibleRegionUtils.toPolygon(mapView.getMap().getVisibleRegion()),
                new SearchOptions(),
                this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.map);
        super.onCreate(savedInstanceState);

        searchManager = SearchFactory.getInstance().createSearchManager(SearchManagerType.COMBINED);

        mapView = (MapView) findViewById(R.id.mapview);
        mapView.getMap().addInputListener(new InputListener() {
            @Override
            public void onMapTap(@NonNull Map map, @NonNull Point point) {
                movePoint(point);
            }

            @Override
            public void onMapLongTap(@NonNull Map map, @NonNull Point point) {

            }
        });

        searchEdit = (EditText) findViewById(R.id.search_edit);
        searchEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    submitQuery(searchEdit.getText().toString());
                }

                return false;
            }
        });

        searchEditLat = findViewById(R.id.search_edit_Lat);
        searchEditLng = findViewById(R.id.search_edit_Lng);
        searchEditLng.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH &&
                    notEmpty()) {
                    String latS = searchEditLat.getText().toString();
                    latS = latS.replace(",", ".");
                    String lngS = searchEditLat.getText().toString();
                    lngS = lngS.replace(",",".");
                    Double lat = null;
                    Double lng;
                    try {
                        lat = Double.valueOf(latS);
                    }
                    catch (NumberFormatException e) {

                    }
                    try {
                        lng = Double.valueOf(lngS);
                        movePoint( new Point(lat,lng));
                    }
                    catch (NumberFormatException e) {

                    }
                }
                return false;
            }
        });

        aSwitch = findViewById(R.id.switch1);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    searchEdit.setVisibility(View.VISIBLE);
                    searchEditLat.setVisibility(View.INVISIBLE);
                    searchEditLng.setVisibility(View.INVISIBLE);
                }
                else {
                    searchEdit.setVisibility(View.INVISIBLE);
                    searchEditLat.setVisibility(View.VISIBLE);
                    searchEditLng.setVisibility(View.VISIBLE);
                }
            }
        });
        aSwitch.setChecked(true);

        buttomSetupLockation = findViewById(R.id.buttonSetLocation);
        buttomSetupLockation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LOCATION = mapView.getMap().getCameraPosition().getTarget();
                MainActivity.lantitude = LOCATION.getLatitude();
                MainActivity.longitude = LOCATION.getLongitude();
                onBackPressed();
            }
        });

    LOCATION = new Point(MainActivity.lantitude, MainActivity.longitude);
    movePoint(LOCATION);
    }

    private boolean notEmpty() {
        return ! (searchEditLat.getText().toString().equals("") ||
                  searchEditLng.getText().toString().equals(""));
    }

    void movePoint(Point point) {
        mapView.getMap().move(
                new CameraPosition(point, 14.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 5),
                null);
        MapObjectCollection mapObjects = mapView.getMap().getMapObjects();
        mapObjects.clear();
        mapObjects.addPlacemark(point,
                ImageProvider.fromResource(this, R.drawable.search_result));
    }

    @Override
    protected void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }

    @Override
    public void onSearchResponse(Response response) {

        if (response != null)
            Log.d("DEBUG", "Найдено " + response.getCollection().getChildren().size());

        if (response.getCollection().getChildren().size() >= 1) {
            Point pointNew = response.getCollection().getChildren().get(0).getObj().getGeometry().get(0).getPoint();
            if (pointNew != null) {
                movePoint(pointNew);
            }
        }
    }

    @Override
    public void onSearchError(@NonNull Error error) {
            String errorMessage = getString(R.string.unknown_error_message);
            if (error instanceof RemoteError) {
                errorMessage = getString(R.string.remote_error_message);
            } else if (error instanceof NetworkError) {
                errorMessage = getString(R.string.network_error_message);
            }

            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

}