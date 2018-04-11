package com.bdwater.yyc;

import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by hegang on 18/4/10.
 */

// javascript of html calls this object
public class AndroidJs extends Object {
    OnCallListener listener;

    public void setListener(OnCallListener listener) {
        this.listener = listener;
    }

    @JavascriptInterface
    public void submit(String param) {
        if(null != this.listener)
            listener.onSubmit(param);
    }

    public interface OnCallListener {
        void onSubmit(String param);
    }
}
