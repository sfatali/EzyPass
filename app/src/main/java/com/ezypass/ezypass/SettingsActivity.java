package com.ezypass.ezypass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Manage SettingsActivity UI
 */
public class SettingsActivity extends AppCompatActivity {

    public static final int PASSWORD_EXTENSION_DEFAULT_SIZE = 4;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        this.appPreferences = new AppPreferences(getBaseContext());
        
         /*
         * Define UI
         */
        TextView userKeyTextView = (TextView) findViewById(R.id.SettingsActivitytextViewKey);

        // Show to user his key
        // TODO : add security
        userKeyTextView.setText(Generator.keyToString(this.appPreferences.getUserKey()));
    }
}
