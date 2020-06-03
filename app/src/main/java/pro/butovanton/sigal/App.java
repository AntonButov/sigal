package pro.butovanton.sigal;

import android.app.Application;

import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.search.SearchFactory;

public class App extends Application {

    private final String MAPKIT_API_KEY = "554ce7d8-7881-4542-a90a-9d6bb581c4a3";

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * Set the api key before calling initialize on MapKitFactory.
         * It is recommended to set api key in the Application.onCreate method,
         * but here we do it in each activity to make examples isolated.
         */
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        /**
         * Initialize the library to load required native libraries.
         * It is recommended to initialize the MapKit library in the Activity.onCreate method
         * Initializing in the Application.onCreate method may lead to extra calls and increased battery use.
         */
        MapKitFactory.initialize(this);
        SearchFactory.initialize(this);
    }
}
