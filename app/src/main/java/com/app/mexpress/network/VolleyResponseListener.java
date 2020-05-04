package com.app.mexpress.network;

public interface VolleyResponseListener {
    void onError(String message);
    void onResponse(String response);

}
