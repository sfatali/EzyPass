package com.ezypass.ezypass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Manage NewActivity UI
 */
public class NewActivity extends AppCompatActivity {

    private EditText importKeyEditText;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        appPreferences = new AppPreferences(getBaseContext());

        /*
         * Define UI
         */
        Button importKeyButton  = (Button) findViewById(R.id.NewActivitybuttonImport);
        Button generateButton   = (Button) findViewById(R.id.NewActivitybuttonGenerate);
        this.importKeyEditText  = (EditText) findViewById(R.id.NewActivityeditTextImportKey);

        // Generate new key
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appPreferences.setUserKey(Generator.generateUserKey());
                appPreferences.setUserShortcut(new ArrayList<String>());
                startMainActivity();
            }
        });

        // Import key
        // TODO : validate imported key
        importKeyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String importedKey = importKeyEditText.getText().toString();
                if(!importedKey.isEmpty()){
                    appPreferences.setUserKey(Generator.importSecretKey(importedKey));
                    startMainActivity();
                }
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        try {
            appPreferences.getUserKey();
            Log.d(NewActivity.class.getName(), "User had a key");
            startMainActivity();
        } catch(Exception e){
            Log.w(NewActivity.class.getName(), "User had no key");
        }
    }

    /**
     * Start activity main
     */
    private void startMainActivity() {
        Intent i = new Intent(NewActivity.this, MainActivity.class);
        startActivity(i);
    }
}
