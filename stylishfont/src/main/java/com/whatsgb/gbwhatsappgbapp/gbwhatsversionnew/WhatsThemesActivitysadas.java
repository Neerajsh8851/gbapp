package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.appbar.MaterialToolbar;

public class WhatsThemesActivitysadas extends AppCompatActivity {
    MaterialToolbar materialToolbar;
    Button mode;
    private static final String NIGHT_MODE = "NightMode";
    private static final String PREF = "AppSettingsPrefs";
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whatsactivity_themes);
        materialToolbar = findViewById(R.id.toolbar);
        mode = findViewById(R.id.mode);
        setSupportActionBar(materialToolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Themes");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //Night Mode
        final SharedPreferences appSettingsPrefs = getSharedPreferences(PREF,0);
        final boolean isNightModeOn = appSettingsPrefs.getBoolean(NIGHT_MODE, false);
        if (isNightModeOn) {
                mode.setText("DARK MODE");
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                mode.setText("LIGHT MODE");
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }

          //Night or Light Mode Button
        mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = appSettingsPrefs.edit();

                if (isNightModeOn) {
                    mode.setText("DARK MODE");
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor.putBoolean(NIGHT_MODE, false);
                    editor.apply();
                    Intent intent = getIntent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    finish();
                    startActivity(intent);
                }
                else {
                    mode.setText("DARK MODE");
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor.putBoolean(NIGHT_MODE, true);
                    editor.apply();
                    Intent intent = getIntent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    finish();
                    startActivity(intent);
                }
            }
        });


    }

    @Override
    public void onBackPressed() {

    }


}