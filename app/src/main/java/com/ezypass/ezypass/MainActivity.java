package com.ezypass.ezypass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import javax.crypto.SecretKey;

/**
 * Manage MainActivity UI
 */
public class MainActivity extends AppCompatActivity {

    private EditText appNameEditText;
    private TextView passResultTextView;
    private ListView shortcutsListView;
    private ArrayList<String> shortcuts;
    private AppPreferences appPreferences;
    private int passKeySize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.appPreferences         = new AppPreferences(getBaseContext());
        this.shortcuts              = this.appPreferences.getUserShortcuts();

        /*
         * Define UI
         */
        ImageButton addShortcutButton   = (ImageButton) findViewById(R.id.MainActivityImagebuttonAddShortcut);
        ImageButton generateButton      = (ImageButton) findViewById(R.id.MainActivityimageButtonGenerate);
        ImageButton settingsButton      = (ImageButton) findViewById(R.id.MainActivityimageButtonSettings);
        this.appNameEditText            = (EditText) findViewById(R.id.MainActivityeditTextAppName);
        this.passResultTextView         = (TextView) findViewById(R.id.MainActivitytextViewResult);
        this.shortcutsListView          = (ListView) findViewById(R.id.MainActivitylistViewShortcuts);

        // Pass size
        passKeySize                 = SettingsActivity.PASSWORD_EXTENSION_DEFAULT_SIZE;
        try {
            passKeySize             = this.appPreferences.getUserPassSize();
        }catch (Exception ignored) {}

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
                String newShortcut = appNameEditText.getText().toString();
                if(!newShortcut.isEmpty() && !shortcuts.contains(newShortcut)) {
                    shortcuts.add(appNameEditText.getText().toString());
                    appPreferences.setUserShortcut(shortcuts);
                    updateListView();
                }
            }
        });
        updateListView();

        // listview click
        shortcutsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                generateToTextView(shortcuts.get(position), appPreferences.getUserKey(), passKeySize);
            }
        });

        // listview long click
        shortcutsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                shortcuts.remove(position);
                updateListView();
                return false;
            }
        });

        // settings button click
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(i);
            }
        });
    }

    /**
     * Update the list view to display user shortcuts
     */
    private void updateListView() {
        // Convert ArrayList to array
        ArrayAdapter adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, this.shortcuts);
        this.shortcutsListView.setAdapter(adapter);
    }

    /**
     * Update the text view to display the generated password extension
     * @param appName the app to generate the password extension
     * @param userKey the user key
     * @param size the size of the generated key
     */
    private void generateToTextView(String appName, SecretKey userKey, int size) {
        passResultTextView.setText(Generator.generateUserPass(appName, userKey, size));
    }
}
