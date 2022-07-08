package com.nibodev.mobileads;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.nibodev.androidutil.Fire;

import java.util.ArrayList;

public class AppOpenAds {
    private static AppOpenAds instance;
    private final ArrayList<AppOpenAd> _ads = new ArrayList<>();
    private String adId;

    private AppOpenAds() {
        adId = Fire.getString("app_open_ad_id");
    }

    public static AppOpenAds AppOpenAd() {
        if (instance == null) instance = new AppOpenAds();
        return instance;
    }

    public AppOpenAd firstAd() {
        if (_ads.isEmpty()) return null;
        return _ads.remove(0);
    }

    public void loadAd(Context context) {
        AdRequest adRequest = new AdRequest.Builder().build();
        AppOpenAd.AppOpenAdLoadCallback callback = new AppOpenAd.AppOpenAdLoadCallback() {
                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                    AndroidUtility.console(TAG, "failed to load next app open ad. " + loadAdError);
                }

                @Override
                public void onAdLoaded(@NonNull AppOpenAd ad) {
//                    AndroidUtility.console(TAG, "Loaded an app open ad.");
                    _ads.add(ad);
                }
            };

        AppOpenAd.load(context, adId, adRequest, AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, callback);
    }
}
