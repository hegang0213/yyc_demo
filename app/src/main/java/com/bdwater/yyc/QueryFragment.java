package com.bdwater.yyc;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QueryFragment extends Fragment {
    @BindView(R.id.webView)
    WebView webView;

    public QueryFragment() {
        // Required empty public constructor
    }


    public static Fragment newInstance() {
        QueryFragment fragment = new QueryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_query, container, false);
        ButterKnife.bind(this, view);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // instances a new android js object
        // implements a OnCallListener interface to hold 'submit' method
        AndroidJs androidJs = new AndroidJs();
        androidJs.setListener(listener);
        // "androidJs" is a virtual object of javascript
        // html sample code:
        // <script>
        //      function callAndroid() {
        //          androidJs.method();
        //      }
        // </script>
        // methods of androidJs to see AndroidJs
        webView.addJavascriptInterface(androidJs, "androidJs");
        // sample to see query.html
        webView.loadUrl("file:///android_asset/query.html");

        return view;
    }

    // OnCallListener interface, callback of AndroidJs by javascript
    AndroidJs.OnCallListener listener = new AndroidJs.OnCallListener() {
        @Override
        public void onSubmit(String param) {
            //Toast.makeText(getActivity(), "Call submit():" + param, Toast.LENGTH_SHORT).show();
            MainActivity activity = (MainActivity)getActivity();
            activity.payByNumber(param);
        }
    };

}
