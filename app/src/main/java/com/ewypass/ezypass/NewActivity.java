package com.ewypass.ezypass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class NewActivity extends AppCompatActivity {

    private EditText importKeyEditText;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        this.appPreferences = new AppPreferences(getBaseContext());
        String userKey = appPreferences.getUserKey();
        if(userKey != null){
            Log.d(NewActivity.class.getName(), "User had a key");
            startMainActivity();
        }else{
            Log.w(NewActivity.class.getName(), "User had no key");
        }

        /*
         * Define UI
         */
        Button importKeyButton = (Button) findViewById(R.id.NewActivitybuttonImport);
        Button generateButton = (Button) findViewById(R.id.NewActivitybuttonGenerate);
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
        importKeyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String importedKey = importKeyEditText.getText().toString();
                // TODO : validate imported key
                appPreferences.setUserKey(importedKey);
                startMainActivity();
            }
        });
    }

    /**
     *
     */
    private void startMainActivity(){
        Intent i = new Intent(NewActivity.this, MainActivity.class);
        startActivity(i);
    }
}
