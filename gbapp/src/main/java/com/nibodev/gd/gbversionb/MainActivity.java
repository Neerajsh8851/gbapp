package com.nibodev.gd.gbversion;

import static com.nibodev.androidutil.AndroidUtility.console;
import static com.nibodev.androidutil.AndroidUtility.openPrivacyPolicyInWeb;
import static com.nibodev.androidutil.AndroidUtility.shareThisApp;

import android.os.Bundle;

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
            MobileAd.interAdActivity(this, WhatsDirectChatActivity.class);
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
    }


    @Override
    public void onBackPressed() {
        MobileAd.interAdActivity(this, ExitActivity.class);
        finishAfterTransition();
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