package com.nibodev.mobileads;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileads.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.nibodev.androidutil.Fire;
import com.nibodev.androidutil.AndroidUtility;

import java.util.ArrayList;


public class AppOpenAdActivity extends AppCompatActivity {
    private static final String TAG = "AppOpenAdActivity";
    private static final ArrayList<AppOpenAd> _ads = new ArrayList<>();
    private String adId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_full_screen_progressbar);
        adId = Fire.getString("app_open_ad_id");
        AndroidUtility.console(TAG, "App open ad id = " + adId);
        showAd();
    }

    private void showAd() {
        if (!_ads.isEmpty()) {
            makeAppear(_ads.remove(0));
            loadAd(null);
            return;
        }
        loadAd(new AppOpenAd.AppOpenAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                AndroidUtility.console(TAG, "Could not load ad");
                exitThisActivity();
            }

            @Override
            public void onAdLoaded(@NonNull AppOpenAd appOpenAd) {
                AndroidUtility.console(TAG, "Ad was loaded");
                makeAppear(appOpenAd);
                loadAd(null);
            }
        });
    }

    private void makeAppear(AppOpenAd appOpenAd) {
        if (appOpenAd == null) return;
        FullScreenContentCallback fscc = new FullScreenContentCallback() {
            @Override
            public void onAdClicked() {
                AndroidUtility.console(TAG, "app open ad was clicked");
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                AndroidUtility.console(TAG, "dismissed this app open ad");
                exitThisActivity();
            }
        };
        appOpenAd.setFullScreenContentCallback(fscc);
        appOpenAd.show(this);
    }

    private void exitThisActivity() {
        runOnUiThread(() -> {
            String nextActivity = getIntent().getStringExtra("next-activity");
            try {
                if (nextActivity != null && !nextActivity.isEmpty()) {
                    Class<?> c = Class.forName(nextActivity);
                    Intent intent = new Intent(this, c);
                    startActivity(intent);
                }
            } catch (ClassNotFoundException exception) {
                exception.printStackTrace();
            } finally {
                finishAfterTransition();
            }
        });
    }

    private void loadAd(final AppOpenAd.AppOpenAdLoadCallback loadCallback) {
        AdRequest adRequest = new AdRequest.Builder().build();
        AppOpenAd.AppOpenAdLoadCallback callback = loadCallback;
        if (callback == null) {
            callback = new AppOpenAd.AppOpenAdLoadCallback() {
                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    AndroidUtility.console(TAG, "failed to load next app open ad. " + loadAdError);
                }

                @Override
                public void onAdLoaded(@NonNull AppOpenAd ad) {
                    AndroidUtility.console(TAG, "Loaded an app open ad.");
                    _ads.add(ad);
                }
            };
        }
        AppOpenAd.load(this, adId, adRequest, AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, callback);
    }
}
