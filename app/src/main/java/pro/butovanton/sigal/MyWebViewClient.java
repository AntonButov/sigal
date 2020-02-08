package pro.butovanton.sigal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.measurement.module.Analytics;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.sql.Time;
import java.util.Date;

class MyWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Uri uri = Uri.parse(url);
        if (uri.getHost() != null && (uri.getHost().contains("сигал.com")||uri.getHost().contains("xn--80afoo0a.com"))) {
            return false;
        }
        Log.d("DEBUG",uri.getHost());
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        view.getContext().startActivity(intent);
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        Log.d("DEBUG","onPageFinished");
        Bundle bundle = new Bundle();
        Date date = new Date();
        long millis = date.getTime();
        bundle.putLong(FirebaseAnalytics.Param.VALUE,millis);
        MainActivity.mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.LEVEL_END, bundle);

    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        Log.d("DEBUG","onPageStarted");
        Bundle bundle = new Bundle();
        Date date = new Date();
        long millis = date.getTime();
        bundle.putLong(FirebaseAnalytics.Param.VALUE,millis);
        MainActivity.mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.LEVEL_START, bundle);
    }
}

