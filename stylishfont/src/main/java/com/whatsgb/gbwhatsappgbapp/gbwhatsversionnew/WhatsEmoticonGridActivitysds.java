package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.material.appbar.MaterialToolbar;
import com.nibodev.androidutil.Fire;
import com.nibodev.mobileads.MobileAd;
import com.nibodev.mobileads.NativeAdLoader;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.adapter.CustomAdaptersad;


public class WhatsEmoticonGridActivitysds extends AppCompatActivity {

    //Prefrance
    private static PrefManagersa prf;

    //new
    private Context context;

    String[] logos = {"Love", "Happy", "Music", "Animals", "Angry", "Sad", "Sleeping", "Excited", "Hungry", "Shy", "Other","Kiss","Smile","Laugh"};
    int[] icon = {R.drawable.lovewhats, R.drawable.whatshappy, R.drawable.whatsmusic, R.drawable.animalwhats, R.drawable.angrywhats, R.drawable.whatssad,
            R.drawable.whatssleeping, R.drawable.excitedwhats, R.drawable.hungry_whats, R.drawable.shywhats, R.drawable.whatsother, R.drawable.kisswhats, R.drawable.whatssmile, R.drawable.whatslaugh_teri};

    GridView simpleGrid;
    MaterialToolbar materialToolbar;

    //banner ads
    private LinearLayout bannerAdContainer;
    private TemplateView templateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whatsactivity_emoticon_grid);
        materialToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(materialToolbar);
        if (getSupportActionBar() != null){
           getSupportActionBar().setTitle("Emoticons");
           getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        materialToolbar.setNavigationOnClickListener(view -> {
            finishAfterTransition();
        });

        context = getApplicationContext();

        prf = new PrefManagersa(context);

        bannerAdContainer = findViewById(R.id.banner_container);
        simpleGrid = findViewById(R.id.simpleGridView);
        simpleGrid.setAdapter(new CustomAdaptersad(this, logos,icon));
        simpleGrid.setOnItemClickListener(new simpleGridListner());

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

    private class simpleGridListner implements AdapterView.OnItemClickListener {
        private simpleGridListner() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
           /* Intent intent = new Intent(getApplicationContext(), WhatsEmoticonsActivityas.class);
            intent.putExtra("image", logos[i]);
            intent.putExtra("P", i);
            startActivityForResult(intent, 101);*/

            MobileAd.interAdActivity(WhatsEmoticonGridActivitysds.this, WhatsEmoticonsActivityas.class, intent -> {
                intent.putExtra("image", logos[i]);
                intent.putExtra("P", i);
            });
        }
    }
}