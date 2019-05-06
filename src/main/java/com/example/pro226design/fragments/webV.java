package com.example.pro226design.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.pro226design.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class webV extends Fragment {

 WebView webView;
 String url1;
    public webV() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_web_v, container, false);
        //toolbar
       Toolbar tool1=(Toolbar)v.findViewById(R.id.tool1);
        ((AppCompatActivity)getActivity()).setSupportActionBar(tool1);

        webView=(WebView)v.findViewById(R.id.webv);
        if(getArguments()!=null){
            url1=getArguments().getString("arturl1");
        }
        if(url1!=null){
            webView.loadUrl(url1);

        }else {
            Toast.makeText(getContext(), "bad url", Toast.LENGTH_SHORT).show();
        }

        //enabling javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //forcing links and redirect to open in webview instead of browser
       webView.setWebViewClient(new WebViewClient());






        return v;
    }

}
