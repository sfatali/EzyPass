package com.ezypass.ezypass;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.google.gson.Gson;

import java.util.ArrayList;

import javax.crypto.SecretKey;

/**
 * Manage user app preferences
 */
class AppPreferences {
    private static final String KEY_PREFS_USER_KEY = "EzyPass_User_Secret_key";
    private static final String KEY_PREFS_USER_SHORTCUTS = "EzyPass_User_shotcuts";
    private static final String KEY_PREFS_USER_PASSSIZE = "EzyPass_User_Pass_size";
    private static final String APP_SHARED_PREFS = AppPreferences.class.getSimpleName(); //  Name of the file -.xml
    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    /**
     * Constructor
     * @param context context
     */
    public AppPreferences(Context context) {
        this.sharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        this.prefsEditor = sharedPrefs.edit();
    }

    /**
     * Get the user key
     * @return the registered user key
     */
    public SecretKey getUserKey() {
        String userKey = sharedPrefs.getString(KEY_PREFS_USER_KEY, "");
        return Generator.importSecretKey(userKey);
    }

    /**
     * Set the user key
     * @param userKey new user key
     */
    public void setUserKey(SecretKey userKey) {
        prefsEditor.putString(KEY_PREFS_USER_KEY, Base64.encodeToString(userKey.getEncoded(), Base64.DEFAULT));
        prefsEditor.commit();
    }

    /**
     * Get user shortcuts
     * @return user shortcuts into ArrayList
     */
    public ArrayList<String> getUserShortcuts() {
        String userObject = sharedPrefs.getString(KEY_PREFS_USER_SHORTCUTS, "");
        return (new Gson()).fromJson(userObject, ArrayList.class);
    }

    /**
     * Set user shortcuts
     * @param userShortcuts user shortcuts from ArrayList
     */
    public void setUserShortcut(ArrayList<String> userShortcuts) {
        prefsEditor.putString(KEY_PREFS_USER_SHORTCUTS, (new Gson()).toJson(userShortcuts));
        prefsEditor.commit();
    }

    /**
     * Get user password extension size configuration
     * @return the password extension size
     */
    public int getUserPassSize() {
        String userPassSize = sharedPrefs.getString(KEY_PREFS_USER_PASSSIZE, "");
        return (new Gson()).fromJson(userPassSize, Integer.class);
    }

    /**
     * Set the new password extension size configuration
     * @param userPassSize the new size
     */
    public void setUserPassSize(int userPassSize) {
        prefsEditor.putString(KEY_PREFS_USER_PASSSIZE, (new Gson()).toJson(userPassSize));
        prefsEditor.commit();
    }
}
