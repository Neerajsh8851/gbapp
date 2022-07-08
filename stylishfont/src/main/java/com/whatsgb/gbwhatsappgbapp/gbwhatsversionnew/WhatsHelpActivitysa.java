package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;

import java.util.Objects;

public class WhatsHelpActivitysa extends AppCompatActivity {
    MaterialToolbar materialToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whatsactivity_help);
        materialToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(materialToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Help");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}