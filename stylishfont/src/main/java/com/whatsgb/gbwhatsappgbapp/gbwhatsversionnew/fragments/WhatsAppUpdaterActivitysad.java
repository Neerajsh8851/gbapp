package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.PrefManagersd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.WhatsHomeActivitysadas;

public class WhatsAppUpdaterActivitysad extends AppCompatActivity {

    //Prefrance
    private static PrefManagersd prf;

    //app
    private static final String TAG_APPNAME = "name";
    private static final String TAG_APP_OLDVERSION = "oldversion";
    private static final String TAG_APP_NEWVERSION = "newversion";

    private TextView forceUpdateNote;
    private final String isForceUpdate = "true";
    private Button later;
    private String latestVersion;
    private TextView newVersion;
    private Button update;
    private TextView updateDate;
    private String updatedOn;
    private TextView whatsNew;
    private String whatsNewData;

    private String newversion;

    public WhatsAppUpdaterActivitysad() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whatsactivity_app_updater);

        prf = new PrefManagersd(WhatsAppUpdaterActivitysad.this);

        newversion = getIntent().getStringExtra(TAG_APP_NEWVERSION);

        updateDate = findViewById(R.id.date);
        newVersion = (TextView) findViewById(R.id.version);
        whatsNew = (TextView) findViewById(R.id.whatsnew);
        forceUpdateNote = (TextView) findViewById(R.id.forceUpdateNote);
        later = (Button) findViewById(R.id.laterButton);
        update = (Button) findViewById(R.id.updateButton);
        updateDate.setText(updatedOn);
        newVersion.setText("New Version: v"+newversion);
        whatsNew.setText(whatsNewData);
        if (isForceUpdate.equals("true")) {
            later.setVisibility(View.GONE);
            forceUpdateNote.setVisibility(View.VISIBLE);
        }
        update.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(prf.getString(WhatsHomeActivitysadas.TAG_NEWAPPURL)));
            startActivity(browserIntent);
        });
    }
}
