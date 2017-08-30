package com.ewypass.ezypass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewActivity extends AppCompatActivity {

    private Button importKeyButton;
    private Button generateButton;
    private EditText importKeyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        AppPreferences appPreferences = new AppPreferences(getBaseContext());
        String userKey = appPreferences.getUserKey();
        if(userKey != null){
            Log.d(NewActivity.class.getName(), "User had a key");
            Intent i = new Intent(NewActivity.this, MainActivity.class);
            startActivity(i);
        }else{
            Log.w(NewActivity.class.getName(), "User had no key");
        }

        /*
         * Define UI
         */
        this.importKeyButton    = (Button) findViewById(R.id.NewActivitybuttonImport);
        this.generateButton     = (Button) findViewById(R.id.NewActivitybuttonGenerate);
        this.importKeyEditText  = (EditText) findViewById(R.id.NewActivityeditTextImportKey);

        // Generate new key
        this.generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        // Import key
    }
}
