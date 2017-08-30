package com.ewypass.ezypass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText appNameEditText;
    private TextView passResultTextView;
    private ListView shortcutsListView;
    private ArrayList<String> shortcuts;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.appPreferences         = new AppPreferences(getBaseContext());
        this.shortcuts              = this.appPreferences.getUserShortcuts();

        /*
         * Define UI
         */
        Button addShortcutButton    = (Button) findViewById(R.id.MainActivitybuttonAddShortcut);
        Button generateButton       = (Button) findViewById(R.id.MainActivitybuttonGenerate);
        this.appNameEditText        = (EditText) findViewById(R.id.MainActivityeditTextAppName);
        this.passResultTextView     = (TextView) findViewById(R.id.MainActivitytextViewResult);
        this.shortcutsListView      = (ListView) findViewById(R.id.MainActivitylistViewShortcuts);

        // Generate pass
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passResultTextView.setText(Generator.generateUserPass(appNameEditText.getText().toString(), appPreferences.getUserKey()));
            }
        });

        // add shortcut
        addShortcutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : verify exists
                shortcuts.add(appNameEditText.getText().toString());
                updateListView();
            }
        });
    }

    private void updateListView(){

    }
}
