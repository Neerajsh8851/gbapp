package com.nibodev.gd.gbversion;

import static com.nibodev.androidutil.AndroidUtility.console;
import static com.nibodev.androidutil.AndroidUtility.openPrivacyPolicyInWeb;
import static com.nibodev.androidutil.AndroidUtility.shareThisApp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import com.nibodev.androidutil.AndroidUtility;
import com.nibodev.androidutil.Fire;
import com.nibodev.gd.gbversion.databinding.ActivityMainBinding;
import com.nibodev.mobileads.MobileAd;
import com.nibodev.mobileads.NativeAdLoader;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.WhatsHomeActivitysadas;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.mainContent.btnStatus.setOnClickListener(view -> {
            // Viewpager onPageSelected call trigger the ad
//            startActivityWithInterAd(this, StatusActivity.class);
            AndroidUtility.startActivity(this, StatusActivity.class);
        });

        binding.mainContent.btnDirectChat.setOnClickListener(v -> {
            MobileAd.loadInterAd(this, ()-> {
                AndroidUtility.startActivity(this, WhatsDirectChatActivity.class );
            });
        });

        binding.mainContent.btnShare.setOnClickListener( v -> {
            shareThisApp(this);
        });

        binding.mainContent.btnPrivacyPolicy.setOnClickListener(v-> {
            openPrivacyPolicyInWeb(this);
        });

        binding.mainContent.btnStylishFont.setOnClickListener(v -> {
            AndroidUtility.startActivity(this, WhatsHomeActivitysadas.class);
/*
            InterAdController interAdController = new InterAdController(this);
            interAdController.doAfter(() -> {
                AndroidUtility.startActivity(this, WhatsHomeActivitysadas.class);
            });
            interAdController.loadAd();
*/
        });
        String adId = Fire.getString("native_ad_id_1");
        new NativeAdLoader(adId).attachNativeAd(binding.mainContent.templateView1);
        MobileAd.loadAppOpenAd(this,null);
    }


    @Override
    public void onBackPressed() {
//      finish();
        MobileAd.loadInterAd(this, null);
        Intent exit_activity_intent = new Intent(this, ExitActivity.class);
        startActivityForResult(exit_activity_intent, 101);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK)
        {
            finish();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        try {
            binding.mainContent.templateView1.destroyNativeAd();
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            binding = null;
        }
        console(MainActivity.class.getCanonicalName(),"MainActivity destroyed");
    }
}