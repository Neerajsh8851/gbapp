package com.nibodev.mobileads;

import static com.nibodev.androidutil.AndroidUtility.console;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileads.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.nibodev.androidutil.Fire;

import java.util.ArrayList;

public class InterstitialAdActivity extends AppCompatActivity {
    private static final String TAG = "InterstitialAdActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_full_screen_progressbar);
//        console(TAG, "adId = " + adId);
        showAd();
    }

    private void showAd() {
        InterAd interAd = InterAd.getInstance();
        InterstitialAd ad = interAd.firstAd();
        if (ad != null)
        makeAppear(ad);
        interAd.loadAd(this);
    }

    private void makeAppear(final InterstitialAd ad) {
        FullScreenContentCallback fscc = new FullScreenContentCallback() {
            @Override
            public void onAdClicked() {
                console(TAG, "ad was clicked: ");
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                console(TAG, "dismissed");
                exitThisActivity();
            }
        };
        ad.setFullScreenContentCallback(fscc);
        ad.show(this);
    }

    private void exitThisActivity() {
        console(TAG, "exiting this activity");
        String nextActivity = getIntent().getStringExtra("next-activity");
        try {
            console(TAG, "next-activity: " + nextActivity);
            if (nextActivity != null && !nextActivity.isEmpty()) {
                Class<?> c = Class.forName(nextActivity);
                Intent intent = new Intent(this, c);
                intent.putExtras(getIntent().getExtras());
                startActivity(intent);
                console("bundle", getIntent().getExtras().toString());
            }
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        } finally {
            finishAfterTransition();
        }
    }

    public void loadAd(InterstitialAdLoadCallback loadCallback) {
        InterAd interAd = InterAd.getInstance();
        interAd.loadAd(this);

      /*  AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAdLoadCallback callback = loadCallback;
        if (callback == null)
        callback = new InterstitialAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                console(TAG, "failed to load next ad. " + loadAdError);
            }

            @Override
            public void onAdLoaded(@NonNull InterstitialAd ad) {
                console(TAG, "Loaded next ad.");
                _ads.add(ad);
            }
        };
        console(TAG, "trying to load inter ad");
        InterstitialAd.load(this, adId, adRequest, callback);*/
    }
}
