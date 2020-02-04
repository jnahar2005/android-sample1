package com.stpprojects.einscriptionslms.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.stpprojects.einscriptionslms.LandingActivity;


public class SessionManager {
    SharedPreferences pref;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "IalmAcademy";

    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_FIRST_NAME = "userFirstName";
    private static final String KEY_LAST_NAME = "lastLastName";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_CITY = "city";
    private static final String KEY_POSTAL_CODE = "postalCode";
    private static final String KEY_MOBILE = "mobile";
    private static final String key_Email = "Email";
    private static final String key_SOURCE = "";
    private static final String key_APITOKEN = "ApiToken";
    private static final String isReviewAdded = "isReviewAdded";
    private static final String KEY_LOGIN_TYPE = "loginType";
    private static final String APP_SESSION_TOKEN = "app_token";
    private static final String LAST_LOGIN_ID = "last_login_id";
    private static final String IMAGE_URL = "imageurl";

    public void createSession(String userid,
                               String userFirstName,
                               String userLasttName,
                               String address,
                               String country,
                               String city,
                               String postalCode,
                               String mobile,
                               String email,
                               String source,
                               String imageurl
    ) {
        editor.putString(KEY_USER_ID, userid);
        editor.putString(KEY_FIRST_NAME, userFirstName);
        editor.putString(KEY_LAST_NAME, userLasttName);
        editor.putString(KEY_ADDRESS, address);
        editor.putString(KEY_COUNTRY, country);
        editor.putString(KEY_CITY, city);
        editor.putString(KEY_POSTAL_CODE, postalCode);
        editor.putString(KEY_MOBILE, mobile);
        editor.putString(key_Email, email);
        editor.putString(key_SOURCE, source);
        editor.putString(IMAGE_URL, imageurl);
        editor.putBoolean(IS_LOGIN, true);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void setuserFirstName(String userFirstName) {
        editor.putString(KEY_FIRST_NAME, userFirstName);
        editor.commit();
    }

    public String getuserFirstName() {
        return pref.getString(KEY_FIRST_NAME, "");
    }

    public String getuserLastName() {
        return pref.getString(KEY_LAST_NAME, "");
    }

    public String getuserEmail() {
        return pref.getString(key_Email, "");
    }

    public void setApiToken(String userFirstName) {
        editor.putString(key_APITOKEN, userFirstName);
        editor.commit();
    }

    public String getUserId() {
        return pref.getString(KEY_USER_ID, "");
    }

    public String getApiToken() {
        return pref.getString(key_APITOKEN, "");
    }

    public void setReviewAdded(String userFirstName) {
        editor.putString(isReviewAdded, userFirstName);
        editor.commit();
    }

    public String getReviewAdded() {
        return pref.getString(isReviewAdded, "");
    }

    public String getMobile() {
        return pref.getString(KEY_MOBILE, "");
    }

    public String getAddress() {
        return pref.getString(KEY_ADDRESS, "");
    }

    public String getCity() {
        return pref.getString(KEY_CITY, "");
    }

    public String getCountry() {
        return pref.getString(KEY_COUNTRY, "");
    }

    public String getPostalCode() {
        return pref.getString(KEY_POSTAL_CODE, "");
    }

    public void setLoginType(String userFirstName) {
        editor.putString(KEY_LOGIN_TYPE, userFirstName);
        editor.commit();
    }

    public String getLoginType() {
        return pref.getString(KEY_LOGIN_TYPE, "");
    }

    public void setSessiontoken(String userFirstName, String lastid) {
        editor.putString(APP_SESSION_TOKEN, userFirstName);
        editor.putString(LAST_LOGIN_ID, lastid);
        editor.commit();
    }

    public String getSessiontoken() {
        return pref.getString(APP_SESSION_TOKEN, "");
    }

    public String getLastlogingid() {
        return pref.getString(LAST_LOGIN_ID, "");
    }

    public String getImageUrl() {
        return pref.getString(IMAGE_URL, "");
    }


    /**
     * Clear session details
     */
    public void logoutUser(Activity mcontex) {
        editor.clear();
        editor.apply();
        Intent intent = new Intent(mcontex, LandingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        mcontex.finish();
        mcontex.startActivity(intent);
    }

}