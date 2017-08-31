package com.ewypass.ezypass;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.google.gson.Gson;

import java.util.ArrayList;

import javax.crypto.SecretKey;

class AppPreferences {
    static final String KEY_PREFS_USER_KEY= "EzyPass_User_Secret_key";
    static final String KEY_PREFS_USER_SHORTCUTS= "EzyPass_User_shotcuts";
    static final String KEY_PREFS_USER_PASSSIZE= "EzyPass_User_Pass_size";
    private static final String APP_SHARED_PREFS = AppPreferences.class.getSimpleName(); //  Name of the file -.xml
    private SharedPreferences _sharedPrefs;
    private SharedPreferences.Editor _prefsEditor;

    /**
     *
     * @param context
     */
    AppPreferences(Context context) {
        this._sharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        this._prefsEditor = _sharedPrefs.edit();
    }

    /**
     *
     * @return
     */
    SecretKey getUserKey() {
        String userKey = _sharedPrefs.getString(KEY_PREFS_USER_KEY, "");
        return Generator.importSecretKey(userKey);
    }

    /**
     *
     * @param userKey
     */
    void setUserKey(SecretKey userKey) {
        _prefsEditor.putString(KEY_PREFS_USER_KEY, Base64.encodeToString(userKey.getEncoded(), Base64.DEFAULT));
        _prefsEditor.commit();
    }

    /**
     *
     * @return
     */
    ArrayList<String> getUserShortcuts() {
        String userObject = _sharedPrefs.getString(KEY_PREFS_USER_SHORTCUTS, "");
        return (new Gson()).fromJson(userObject, ArrayList.class);
    }

    /**
     *
     * @param userShortcuts
     */
    void setUserShortcut(ArrayList<String> userShortcuts) {
        _prefsEditor.putString(KEY_PREFS_USER_SHORTCUTS, (new Gson()).toJson(userShortcuts));
        _prefsEditor.commit();
    }

    /**
     *
     * @return
     */
    int getUserPassSize() {
        String userPassSize = _sharedPrefs.getString(KEY_PREFS_USER_PASSSIZE, "");
        return (new Gson()).fromJson(userPassSize, Integer.class);
    }

    /**
     *
     * @param userPassSize
     */
    public void setUserPassSize(int userPassSize) {
        _prefsEditor.putString(KEY_PREFS_USER_PASSSIZE, (new Gson()).toJson(userPassSize));
        _prefsEditor.commit();
    }
}
