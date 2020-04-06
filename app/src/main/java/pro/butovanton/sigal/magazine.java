package pro.butovanton.sigal;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

public class magazine extends Fragment implements OnBackPressedListener {

    private WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.magazine, container, false);
        mWebView = view.findViewById(R.id.activity_main_webview);
        mWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // REMOTE RESOURCE
        mWebView.loadUrl("http://www.xn--80afoo0a.com/");
        mWebView.setWebViewClient(new MyWebViewClient());
        return view;
    }

    @Override
    public boolean onBackPressed() {
        Log.d("DEBUG","BackMagazine");
        if(mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        } else {
          //  super.onBackPressed();
          return false;
        }
    }
    }



