package com.ewypass.ezypass;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;

public class AppPreferences {
    public static final String KEY_PREFS_USER_KEY= "EzyPass_User_Secret_key";
    public static final String KEY_PREFS_USER_SHORTCUTS= "EzyPass_User_shotcuts";
    private static final String APP_SHARED_PREFS = AppPreferences.class.getSimpleName(); //  Name of the file -.xml
    private SharedPreferences _sharedPrefs;
    private SharedPreferences.Editor _prefsEditor;

    public AppPreferences(Context context) {
        this._sharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        this._prefsEditor = _sharedPrefs.edit();
    }

    public String getUserKey() {
        String userKey = _sharedPrefs.getString(KEY_PREFS_USER_KEY, "");
        return (new Gson()).fromJson(userKey, String.class);
    }

    public void setUserKey(String userKey) {
        _prefsEditor.putString(KEY_PREFS_USER_KEY, (new Gson()).toJson(userKey));
        _prefsEditor.commit();
    }

    public ArrayList getUserShortcuts() {
        String userObject = _sharedPrefs.getString(KEY_PREFS_USER_SHORTCUTS, "");
        return (new Gson()).fromJson(userObject, ArrayList.class);
    }

    public void setUserShortcut(ArrayList<String> userShortcuts) {
        _prefsEditor.putString(KEY_PREFS_USER_SHORTCUTS, (new Gson()).toJson(userShortcuts));
        _prefsEditor.commit();
    }
}
