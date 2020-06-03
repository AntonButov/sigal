package pro.butovanton.sigal;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.GeoObjectCollection;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraListener;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.CameraUpdateSource;
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
    private EditText searchEdit;
    private SearchManager searchManager;
    private Session searchSession;

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

    LOCATION = new Point(MainActivity.lantitude, MainActivity.longitude);
    movePoint(LOCATION);
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

        if (response.getCollection().getChildren().size() == 1) {
            Point pointNew = response.getCollection().getChildren().get(0).getObj().getGeometry().get(0).getPoint();
            if (pointNew != null) {
                MainActivity.lantitude = pointNew.getLatitude();
                MainActivity.longitude = pointNew.getLongitude();
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