package com.ezypass.ezypass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ezypass.ezypass.welcome.MainWelcomeActivity;

/**
 * Manage redirection to welcome / main activity at startup
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppPreferences appPreferences = new AppPreferences(getBaseContext());
        try {
            appPreferences.getUserKey();
            Log.d(NewActivity.class.getName(), "User had a key");
            startMainActivity();
        }catch (Exception e){
            Log.w(NewActivity.class.getName(), "User had no key");
            startNewActivity();
            startWelcomeActivity();
        }
    }

    /**
     * Start activity main
     */
    private void startMainActivity() {
        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(i);
    }

    /**
     * Start welcome main
     */
    private void startWelcomeActivity() {
        Intent i = new Intent(SplashActivity.this, MainWelcomeActivity.class);
        startActivity(i);
    }

    /**
     * Start new main
     */
    private void startNewActivity() {
        Intent i = new Intent(SplashActivity.this, NewActivity.class);
        startActivity(i);
    }
}
