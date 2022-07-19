package com.nibodev.gd.gbversion;

import static com.nibodev.androidutil.AndroidUtility.console;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Process;

import com.nibodev.androidutil.AndroidUtility;
import com.nibodev.androidutil.Fire;
import com.nibodev.gd.gbversion.databinding.ActivityExitBinding;
import com.nibodev.mobileads.NativeAdLoader;

public class ExitActivity extends AppCompatActivity {
    private ActivityExitBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnNo.setOnClickListener(v -> {
            AndroidUtility.startActivity(this, MainActivity.class);
            finishAfterTransition();
        });

        binding.btnYes.setOnClickListener(v -> {
            finishAndRemoveTask();
        });

        String adUnitId = Fire.getString("native_ad_id_1");
        NativeAdLoader adLoader = new NativeAdLoader(adUnitId);
        adLoader.attachNativeAd(binding.templateView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            // might be null if ad was not attached.
            binding.templateView.destroyNativeAd();
        }catch (Exception ignored) {}
    }
}