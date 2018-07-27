package com.imeiinfopy.imeiinfopy.fragmen;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.imeiinfopy.imeiinfopy.ConatelP;
import com.imeiinfopy.imeiinfopy.R;


public class ReportarFragment extends Fragment {

    Button report;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reportar,container,false);
        WebView webView = (WebView)v.findViewById(R.id.webWiewReportar);

        webView.loadUrl("http://gustavofleitas.com/4/login/login.html");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        return v;
    }

}
