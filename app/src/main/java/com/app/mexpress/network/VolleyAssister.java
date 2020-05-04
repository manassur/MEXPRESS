package com.app.mexpress.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class VolleyAssister implements VolleyResponseListener {

    private boolean volleyresult;
    private String volleymessage, requestmessage,requestdata;

    public static boolean  getResponseStatus(String response){
        Log.d("res",response);
        boolean error = true;
        JSONObject jsonResponse = null;
        try {
            jsonResponse = new JSONObject(response);
             error = jsonResponse.getBoolean("error");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return error;
    }

    public static String getMessageWithKey(String response, String key){
        Log.d("res",response);
        String message="";
        JSONObject jsonResponse = null;
        try {
            jsonResponse = new JSONObject(response);
            message = jsonResponse.getString(key);
            Log.d("VolleyAssister.message",message);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return message;
    }

    public static String getResponseMessage(String response){
        Log.d("res",response.toString());
        String message="";
        JSONObject jsonResponse = null;
        try {
            jsonResponse = new JSONObject(response);
             message = jsonResponse.getString("message");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return message;
    }

    public static String getResponseData(String response){
        Log.d("res",response.toString());
        String dataJson="";
        JSONObject jsonResponse = null;
        try {
            jsonResponse = new JSONObject(response);
            dataJson = jsonResponse.getString("data");
            Log.d("data",dataJson);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return dataJson;
    }

    public void MakeRequest(int requestType, String Url, final Map<String, String> parameters, final Context context, final VolleyResponseListener listener){

         StringRequest request= null;

        request = new StringRequest(requestType, Url, new Response.Listener<String>(){

            @Override
            public void onResponse(String response){
                listener.onResponse(response);
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error){


                if(error instanceof NoConnectionError){
                   volleymessage="Looks like you are offline";
                    listener.onError(volleymessage);

                }
                else if(error instanceof TimeoutError){

                    volleymessage="Looks like you have a poor network";
                    listener.onError(volleymessage);
                }
                else  if(error instanceof AuthFailureError){

                   listener.onError(getErrorMessage(error));
                }

                else  if(error instanceof ServerError){
                    listener.onError(getErrorMessage(error));



                }
                else  if(error instanceof NetworkError){
                    volleymessage="Sorry, the network connection is lost";
                    listener.onError(volleymessage);


                }
                else  if(error instanceof ParseError){
                    volleymessage="Sorry, the network connection is lost";
                    listener.onError(volleymessage);


                }
            }
        }) {


            @Override
            protected Map<String, String> getParams()throws AuthFailureError {
                return parameters;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                byte[] body =  new Gson().toJson(parameters).getBytes();
                Log.d("body",body.toString());
                return body;
            }

            @Override
            public Map<String, String> getHeaders()throws AuthFailureError {
                Map<String, String> parameter= new HashMap<String, String>();
                parameter.put("app_key","vvv");




                return parameter;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                4000,
                3,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        CustomVolleyRequest.getInstance(context).getRequestQueue().add(request);




    }


    @Override
    public void onError(String message) {
    }

    @Override
    public void onResponse(String response) {
    }

    private String getErrorMessage(VolleyError error ){
        String message="";
        String response;
        if(error.networkResponse.data!=null) {
            try {
                response = new String(error.networkResponse.data,"UTF-8");
                JSONObject jsonResponse = new JSONObject(response);
                 message = jsonResponse.getString("message");

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return message;
    }
}
