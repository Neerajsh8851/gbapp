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
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.nibodev.androidutil.Fire;
import com.nibodev.androidutil.AndroidUtility;

import java.util.ArrayList;


public class AppOpenAdLoader  {
    private final String TAG = this.getClass().getSimpleName();
    private String adIdKey = "app_open_ad_id";
    private ArrayList<AppOpenAd> mAppOpenAds;
    private final Handler handler;
    private static AppOpenAdLoader instance;

    private AppOpenAdLoader() {
        handler = new Handler(Looper.getMainLooper());
        mAppOpenAds = new ArrayList<>();
    }

    public static AppOpenAdLoader getInstance() {
        if (instance == null) instance = new AppOpenAdLoader();
        return instance;
    }


    public void loadAd(Context context) {
        handler.post(() -> AppOpenAd.load(
                context, getAdId(),
                new AdRequest.Builder().build(),
                AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
                new AppOpenAd.AppOpenAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        AndroidUtility.console(TAG, "Could not load ad: " + loadAdError);
                    }

                    @Override
                    public void onAdLoaded(@NonNull AppOpenAd appOpenAd) {
                        mAppOpenAds.add( appOpenAd);
                    }
                }
        ));
    }

    public boolean isAdAvailable() {
        return !mAppOpenAds.isEmpty();
    }

    /**
     * Load and show ad on the Main ui thread.
     * @param activity
     */
    public void showAd(Activity activity, Runnable do_after) {
        handler.post(()->{
            if (isAdAvailable()) {
                AppOpenAd appOpenAd = mAppOpenAds.remove(0);
                appOpenAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdClicked() {
                        AndroidUtility.console(TAG, "Click on ad : " + mAppOpenAds);
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        if (do_after != null)
                        {
                            do_after.run();
                        }
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        if (do_after != null)
                        {
                            do_after.run();
                        }
                    }
                });
                appOpenAd.show(activity);
            } else  {
                AppOpenAd.load(
                        activity, getAdId(),
                        new AdRequest.Builder().build(),
                        AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
                        new AppOpenAd.AppOpenAdLoadCallback() {
                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                AndroidUtility.console(TAG, "could not load the ad: " + loadAdError);
                                if (do_after != null)
                                {
                                    do_after.run();
                                }
                            }

                            @Override
                            public void onAdLoaded(@NonNull AppOpenAd appOpenAd) {
                                AndroidUtility.console(TAG, "loaded ad: " + appOpenAd);
                                mAppOpenAds.add( appOpenAd);
                                showAd(activity, do_after);
                            }
                        }
                );
            }
        });
    }

    private String getAdId() {
      return Fire.getString(adIdKey);
    }
}
