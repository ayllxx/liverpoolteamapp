package com.example.liverpoolteamapp;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        // Get the WebView
        webView = findViewById(R.id.webview);

        // Get the Wikipedia URL from the intent
        String wikiUrl = getIntent().getStringExtra("wikiUrl");

        // Set up the WebView to load the URL
        webView.setWebViewClient(new WebViewClient());  // Ensures pages open in WebView, not external browser
        webView.loadUrl(wikiUrl);  // Load the Wikipedia page
    }
}
