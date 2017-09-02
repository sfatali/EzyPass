package com.ezypass.ezypass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Manage SettingsActivity UI
 */
public class SettingsActivity extends AppCompatActivity {

    // Define default password extension size
    public static final int PASSWORD_EXTENSION_DEFAULT_SIZE = 4;

    // Define default password extension min size
    public static final int PASSWORD_EXTENSION_MIN_SIZE = 1;

    // Instance of user app preferences
    private AppPreferences appPreferences;
    private SeekBar userPassSizeSeekBar;
    private TextView userPassSizeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        this.appPreferences = new AppPreferences(getBaseContext());

         /*
         * Define UI
         */
        TextView userKeyTextView        = (TextView) findViewById(R.id.SettingsActivitytextViewKey);
        userPassSizeTextView            = (TextView) findViewById(R.id.SettingsAvticitytextViewPassSize);
        userPassSizeSeekBar             = (SeekBar) findViewById(R.id.SettingsActivityseekBarPassSize);

        // Show to user his key
        // TODO : add security
        userKeyTextView.setText(Generator.keyToString(this.appPreferences.getUserKey()));

        // Seek bar management
        updateSeekBar();

        userPassSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress >= PASSWORD_EXTENSION_MIN_SIZE) {
                    appPreferences.setUserPassSize(progress);
                    updateSeekBar();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // void
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // void
            }
        });
    }

    /**
     * Update current seekbar state to current user pass size
     */
    private void updateSeekBar(){
        int passSize;

        try {
            passSize = appPreferences.getUserPassSize();
        } catch (Exception e) {
            passSize = PASSWORD_EXTENSION_DEFAULT_SIZE;
        }

        // update
        userPassSizeSeekBar.setProgress(passSize);
        userPassSizeTextView.setText("" + passSize);
    }
}
