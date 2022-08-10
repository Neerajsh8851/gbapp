package com.nibodev.mobileads;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.nibodev.androidutil.AndroidUtility;
import com.nibodev.androidutil.Fire;

import java.util.ArrayList;

public class InterstitialAdLoader {
  private final String TAG = this.getClass().getSimpleName();
  private ArrayList<InterstitialAd> mAdList;
  private static InterstitialAdLoader mInstance;
  private final String adIdKey = "interstitial_ad_id";

  private InterstitialAdLoader() {
    mAdList = new ArrayList<>();
  }

  public static InterstitialAdLoader getInstance() {
    if (mInstance == null) mInstance = new InterstitialAdLoader();
    return mInstance;
  }

  public boolean isAdAvailable() {
    return !mAdList.isEmpty();
  }

  public void loadAd(Context context) {
    Handler handler = new Handler(Looper.getMainLooper());
    handler.post(() -> InterstitialAd.load(
            context, getAdId(),
            new AdRequest.Builder().build(),
            new InterstitialAdLoadCallback() {
              @Override
              public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
              }

              @Override
              public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                mAdList.add(interstitialAd);
              }
            }
    ));
  }


  public void showAd(Activity activity, Runnable postAction) {
    if (isAdAvailable()) {
      InterstitialAd ad = mAdList.remove(0);
      ad.setFullScreenContentCallback(new FullScreenContentCallback() {
        @Override
        public void onAdClicked() {
          AndroidUtility.console(TAG, "Ad clicked");
        }

        @Override
        public void onAdDismissedFullScreenContent() {
          if (postAction != null) postAction.run();
        }

        @Override
        public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
          if (postAction != null) postAction.run();
        }
      });
      ad.show(activity);

    } else {
      InterstitialAd.load(
              activity, getAdId(),
              new AdRequest.Builder().build(),
              new InterstitialAdLoadCallback() {
                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                  AndroidUtility.console(TAG, "Could not load the ad: " + loadAdError);
                  if (postAction != null) postAction.run();
                }

                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                  mAdList.add(interstitialAd);
                  showAd(activity, postAction);
                }
              }
      );
    }
  }

  private String getAdId() {
    return Fire.getString(adIdKey);
  }
}
