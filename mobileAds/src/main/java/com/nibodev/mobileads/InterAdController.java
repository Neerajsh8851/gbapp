package com.nibodev.mobileads;

import static com.nibodev.androidutil.AndroidUtility.console;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.nibodev.androidutil.Fire;

public class InterAdController {
    private final String TAG = "InterAdController";
    private Activity _activity;
    private InterstitialAd _ad;
    private String adId;
    private OnAdClick onAdClick;
    private OnFullScrContentDismissed _onFscd;
    private Runnable _doAfter;

    interface OnAdClick {
        void onClick();
    }

    interface OnFullScrContentDismissed {
        void fscd();
    }

    public InterAdController(Activity activity) {
        _activity = activity;
        adId = Fire.getString("interstitial_ad_id");
    }

    public void setOnFullScrContentDismissed(OnFullScrContentDismissed onFSCD) {
        _onFscd = onFSCD;
    }

    public void loadAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAdLoadCallback callback;
        callback = new InterstitialAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                console(TAG, "could not load the ad: " + loadAdError);
                if (_doAfter != null)
                    _doAfter.run();
            }

            @Override
            public void onAdLoaded(@NonNull InterstitialAd ad) {
                console(TAG, "success fully loaded an ad");
                makeAppear(ad);
            }
        };
        console(TAG, "trying to load inter ad");
        InterstitialAd.load(_activity, adId, adRequest, callback);
    }

    private void makeAppear(InterstitialAd ad) {
        if (ad == null) return;
        FullScreenContentCallback fscc = new FullScreenContentCallback() {
            @Override
            public void onAdClicked() {
                console(TAG, "app open ad was clicked");
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                console(TAG, "dismissed this app open ad");
                if (_doAfter != null)
                    _doAfter.run();
            }
        };
        ad.setFullScreenContentCallback(fscc);
        ad.show(_activity);
    }

    public void doAfter(Runnable runnable) {
        _doAfter = runnable;
    }
}
