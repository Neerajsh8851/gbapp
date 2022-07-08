package com.nibodev.mobileads;

import static com.nibodev.androidutil.AndroidUtility.console;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.nibodev.androidutil.Fire;

import java.util.ArrayList;

public class InterAd {
    private static InterAd instance;
    private String adId;
    private final ArrayList<InterstitialAd> _ads = new ArrayList<>(2);
    private InterAd() {
        adId = Fire.getString("interstitial_ad_id");
    }

    public static InterAd getInstance() {
        if (instance == null) instance = new InterAd();
        return instance;
    }

    public InterstitialAd firstAd() {
        if (_ads.isEmpty()) return null;
        return _ads.remove(0);
    }

    public void loadAd(Context context) {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context, adId, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
            }

            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                _ads.add(interstitialAd);
            }
        });
    }
}
