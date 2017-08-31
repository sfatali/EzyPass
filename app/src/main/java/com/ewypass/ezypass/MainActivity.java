package com.ewypass.ezypass;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import javax.crypto.SecretKey;

public class MainActivity extends AppCompatActivity {

    private EditText appNameEditText;
    private TextView passResultTextView;
    private ListView shortcutsListView;
    private ArrayList<String> shortcuts;
    private AppPreferences appPreferences;
    private int passKeySize;

    @Override
    /**
     *
     */
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

        // Pass size
        passKeySize                 = SettingsActivity.PASSWORD_EXTENSION_DEFAULT_SIZE;
        if(this.appPreferences.getUserPassSize() != 0){
            passKeySize             = this.appPreferences.getUserPassSize();
        }

        // Generate pass
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateToTextView(appNameEditText.getText().toString(), appPreferences.getUserKey(), passKeySize);
            }
        });

        // add shortcut
        addShortcutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : verify exists
                shortcuts.add(appNameEditText.getText().toString());
                appPreferences.setUserShortcut(shortcuts);
                updateListView();
            }
        });
        updateListView();

        // manage shortcuts click
        shortcutsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                generateToTextView(shortcuts.get(position), appPreferences.getUserKey(), passKeySize);
            }
        });
    }

    /**
     *
     */
    private void updateListView(){
        // Convert ArrayList to array
        ArrayAdapter adapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1, this.shortcuts);
        this.shortcutsListView.setAdapter(adapter);
    }

    /**
     *
     * @param appName
     * @param userKey
     * @param size
     */
    private void generateToTextView(String appName, SecretKey userKey, int size){
        passResultTextView.setText(Generator.generateUserPass(appName, userKey, size));
    }
}
