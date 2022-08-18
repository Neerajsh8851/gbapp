package com.nibodev.mobileads;

import static com.nibodev.androidutil.AndroidUtility.console;
import static com.nibodev.androidutil.AndroidUtility.isConnectedToNetwork;
import static java.lang.String.format;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.nibodev.androidutil.Fire;

import java.util.Map;

public class MobileAd {
    private static final String TAG = "MobileAd";
    private static AdTimer adTimer;
    private static AdClickCounter clickCounter;

    private static AdTimer getAdTimer() {
        if (adTimer == null) adTimer = new AdTimer(getAdWaitingTime());
        return adTimer;
    }

    /**
     * Display interstitial ad before the destination
     * activity
     */
    private static int getAdWaitingTime() {
        String str = Fire.getString("ad_waiting_time");
        console(TAG, "ad_waiting_time: " + str);
        int time = 20;
        try {
            time = Integer.parseInt(str);
        } catch (NumberFormatException exception) {
            exception.printStackTrace();
        }
        return time * 1000; // milliseconds
    }


    @SuppressLint("DefaultLocale")
    public static void init(final Activity activity) {
        MobileAds.initialize(activity, initializationStatus -> {
            Map<String, AdapterStatus> statusMap = initializationStatus.getAdapterStatusMap();
            for (String adapterClass : statusMap.keySet()) {
                AdapterStatus status = statusMap.get(adapterClass);
                assert status != null;
                console(TAG, format("Adapter name: %s, Description: %s, Latency: %d",
                        adapterClass, status.getDescription(), status.getLatency()));
            }
            activity.runOnUiThread(() -> {
                InterAd.getInstance().loadAd(activity);
            });
        });
    }

    private static AdClickCounter getAdClickCounter() {
        if (clickCounter == null) {
            int value = (int) Fire.getLong("trigger_inter_ad_at_clicks");
            console(TAG, "app will show ad at clicks: " + value);
            clickCounter = new AdClickCounter(value);
        }
        return clickCounter;
    }

    /**
     * Helper functions to load and show the Interstitial ad.
     * @param currentActivity Activity Instance
     * @param do_after action that should be run in the end.
     */
    public static void loadInterAd(Activity currentActivity, Runnable do_after) {
        if (isConnectedToNetwork(currentActivity) && getAdClickCounter().canTrigger()) {
            InterstitialAdLoader adLoader = InterstitialAdLoader.getInstance();
            adLoader.showAd(currentActivity, do_after);
            adLoader.loadAd(currentActivity);
        } else  {
            if (do_after != null) do_after.run();
        }
    }


    /**
     * Helper function to load and show the app open ad
     * @param currentActivity
     * @param do_after
     */
    public static void loadAppOpenAd(Activity currentActivity, Runnable do_after) {
        AppOpenAdLoader appOpenAdLoader = AppOpenAdLoader.getInstance();
        appOpenAdLoader.showAd(currentActivity, do_after);
        // load an add for the next time
        appOpenAdLoader.loadAd(currentActivity);
    }
}
