package pro.butovanton.sigal;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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
}

