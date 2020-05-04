package com.app.mexpress.utillity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.app.mexpress.MainActivity;

import java.util.HashMap;

/**
 */
public class UserSessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    Context _context;
    int PRIVATE_MODE =0;
    private static final String PREFER_NAME = "AndroidExamplePref";
    public static final String IS_USER_LOGIN ="isUserLoggedIn";
    public static final String KEY_SESSION_ID ="session_id";
    public static final String KEY_SESSION_TOKEN ="session_token";
    public static final String APP_STATUS ="appstatus";
    public static final String FONT_SIZE ="font";
    public static final String NOTIFICATIONS="notify";
    public static final String THEME="theme";




   public UserSessionManager(Context context) {
        this._context=context;
        pref = _context.getSharedPreferences(PREFER_NAME,PRIVATE_MODE);
        editor=pref.edit();
    }


    public void createUserLoginSession(String session_id, String session_token){
        editor.putBoolean(IS_USER_LOGIN,true);
        editor.putString(KEY_SESSION_TOKEN,session_token);
        editor.putString(KEY_SESSION_ID,session_id);

        editor.commit();
    }

    public void SetAppStatus (String appstatus){
        editor.putString(APP_STATUS,appstatus);
        editor.commit();
    }
    public  void isNotFirstTimeAgain (){
        editor.putBoolean("first_time",false);
        editor.commit();
    }

    public void SetFirebaseToken (String token){
        editor.putString("FIREBASE_TOKEN",token);
        editor.commit();
    }
    public String getFirebaseToken(){
        String token = pref.getString("FIREBASE_TOKEN", "");

        return  token;
    }
    public void setGlobalTopicSubscriptionStatus (boolean status){
        editor.putBoolean("GLOBAL",status);
        editor.commit();
    }

    public boolean getGlobalTopicSubscriptionStatus(){
        boolean status = pref.getBoolean("GLOBAL", false);
        return  status;
    }

    public void SetNotificationSetting (String notify){
        editor.putString(NOTIFICATIONS,notify);
        editor.commit();
    }


    public  void cacheFeed(String feed){
        editor.putString("cachefeed",feed);
        Log.d("cachedfeed",feed);

        editor.commit();
    }

    public String getCachedFeed(){
        String cachefeed = pref.getString("cachefeed", "");
        Log.d("gottenfeed",cachefeed);

        return  cachefeed;
    }

    public boolean checkLogin(){
        if(!this.isUserLoggedIn()){
            Intent intent = new Intent(_context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           _context.startActivity(intent);
            return true;
        }

        return false;
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_SESSION_TOKEN,pref.getString(KEY_SESSION_TOKEN,""));
        user.put(APP_STATUS,pref.getString(APP_STATUS,""));
        user.put(FONT_SIZE,pref.getString(FONT_SIZE,""));
        user.put(NOTIFICATIONS,pref.getString(NOTIFICATIONS,""));
        user.put(KEY_SESSION_ID,pref.getString(KEY_SESSION_ID,""));
        user.put(KEY_SESSION_TOKEN,pref.getString(KEY_SESSION_TOKEN,""));

        user.put(THEME,pref.getString(THEME,""));
        return user;
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
      //  FirebaseAuth.getInstance().signOut();
//        Intent intent = new Intent(_context,SignIn.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        _context.startActivity(intent);

    }

    public boolean isUserLoggedIn() {
        return pref.getBoolean(IS_USER_LOGIN, false);
    }

}
