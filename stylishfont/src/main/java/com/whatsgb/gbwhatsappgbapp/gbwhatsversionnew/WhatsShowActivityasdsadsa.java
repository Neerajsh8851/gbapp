package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.material.appbar.MaterialToolbar;
import com.nibodev.androidutil.Fire;
import com.nibodev.mobileads.NativeAdLoader;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments.GiltchFragmentsad;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments.ProNameFragmentsadfsa;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments.WhatsDecorationFragmentdsgfsd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments.WhatsEmojiFragmentasdsad;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments.WhatsEmojiSheetFragmentdefd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments.WhatsFontFragmentsad;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments.WhatsNumberStyleFragmentyfgdf;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments.WhatsRepeatFragmentdsfdsf;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments.WhatsTextDesignFragmentghsdfgs;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments.WhatsTextPlayFragment;

public class WhatsShowActivityasdsadsa extends AppCompatActivity {

    //Prefrance
    private static PrefManagersa prf;

    //new
    private Context context;

    String KEY = "Home";
    MaterialToolbar materialToolbar;


    private FrameLayout frameLayout;

    //fb
    private LinearLayout nativeAdContainer;
    private LinearLayout adView;

    private TemplateView templateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whatsactivity_show);
        materialToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(materialToolbar);

        materialToolbar.setNavigationOnClickListener(v -> {
            finishAfterTransition();
        });

        context = getApplicationContext();

        prf = new PrefManagersa(context);

        int itemid = getIntent().getIntExtra(KEY,1);

        if (itemid==1){
            getSupportActionBar().setTitle("Stylish Text");
            loadFragment(new WhatsFontFragmentsad());
        }
        if (itemid==2){
            getSupportActionBar().setTitle("Decoration Text");
            loadFragment(new WhatsDecorationFragmentdsgfsd());
        }
        if (itemid==3){
            getSupportActionBar().setTitle("Emoticons");
        }
        if (itemid==4){
            getSupportActionBar().setTitle("Emoji Sheet");
            loadFragment(new WhatsEmojiSheetFragmentdefd());
        }
        if (itemid==5){
            getSupportActionBar().setTitle("Glitch Text");
            loadFragment(new GiltchFragmentsad());
        }
        if (itemid==6){
            getSupportActionBar().setTitle("Text Repeater");
            loadFragment(new WhatsRepeatFragmentdsfdsf());
        }
        if (itemid==7){
            getSupportActionBar().setTitle("Text To Emoji");
            loadFragment(new WhatsEmojiFragmentasdsad());
        }
        if (itemid==8){
            getSupportActionBar().setTitle("Text Arts");
            loadFragment(new WhatsTextDesignFragmentghsdfgs());
        }
        if (itemid==9){
            getSupportActionBar().setTitle("Text To Play");
            loadFragment(new WhatsTextPlayFragment());
        }
        if (itemid==10){
            getSupportActionBar().setTitle("Pro Nickname");
            loadFragment(new ProNameFragmentsadfsa());
        }
        if (itemid==11){
            getSupportActionBar().setTitle("Stylish Number");
            loadFragment(new WhatsNumberStyleFragmentyfgdf());
        }

        templateView = findViewById(R.id.template_view);
        // native ad
        String adId = Fire.getString("native_ad_id_1");
        new NativeAdLoader(adId).attachNativeAd(templateView);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            templateView.destroyNativeAd();
        }catch (Exception ignore) {}
    }

    //Fragment Container
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.showFragment,fragment)
                .commit();

    }
}