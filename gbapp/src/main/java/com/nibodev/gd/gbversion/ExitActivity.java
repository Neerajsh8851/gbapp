package com.nibodev.gd.gbversion;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.nibodev.androidutil.Fire;
import com.nibodev.gd.gbversion.databinding.ActivityExitBinding;
import com.nibodev.mobileads.MobileAd;
import com.nibodev.mobileads.NativeAdLoader;

public class ExitActivity extends AppCompatActivity {
    private ActivityExitBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnNo.setOnClickListener(v -> {
            finish();
        });

        binding.btnYes.setOnClickListener(v -> {
            // result ok means: quit application
            setResult(RESULT_OK);
            finish();
        });

        String adUnitId = Fire.getString("native_ad_id_1");
        NativeAdLoader adLoader = new NativeAdLoader(adUnitId);
        adLoader.attachNativeAd(binding.templateView);

        MobileAd.loadInterAd(this, null);
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