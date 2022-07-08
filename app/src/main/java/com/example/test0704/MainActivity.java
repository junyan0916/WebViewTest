package com.example.test0704;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.view.KeyEvent;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    TextView textView;

    private class MyWebViewClient extends WebViewClient {
        @Override
       public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private class MyWebChromeClient extends WebChromeClient {
        //獲得網頁的載入進度，顯示在右上角的TextView控制元件中
        public void onProgressChanged(WebView view, int newProgress) {
            textView = findViewById(R.id.textView2);

            if(newProgress < 100) {
                String progress = newProgress + "%";
                textView.setText(progress);
            } else {
                textView.setText(" ");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void button_onClick(View view) {
        webView = findViewById(R.id.webView1);

        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setBlockNetworkImage(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(
                    WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new MyWebChromeClient());

        webView.loadUrl("http://192.168.130.51:30000/");
        //https://www.ubay.tw
        //https://www.google.com
        //http://192.168.110.42:30000/
    }

    public void button2_onClick(View view) {

        webView.loadUrl("about:blank");
        webView.clearCache(true);
        webView.clearHistory();
    }

    @Override
    public void onBackPressed(){
        if(webView.canGoBack()) {
            webView.goBack();
        }
        else{
            super.onBackPressed();
        }
    }
}