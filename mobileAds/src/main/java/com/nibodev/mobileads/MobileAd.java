package com.nibodev.mobileads;

import static com.nibodev.androidutil.AndroidUtility.console;
import static com.nibodev.androidutil.AndroidUtility.isConnectedToNetwork;
import static java.lang.String.format;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.nibodev.androidutil.AndroidUtility;
import com.nibodev.androidutil.Fire;

import java.util.Map;

public class MobileAd {
    public interface IntentModifier {
        void modifier(Intent intent);
    }

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
     * @param postAction action that should be run in the end.
     */
    public static void loadInterAd(Activity currentActivity, Runnable postAction) {
        if (isConnectedToNetwork(currentActivity) && getAdClickCounter().canTrigger()) {
            InterstitialAdLoader adLoader = InterstitialAdLoader.getInstance();
            adLoader.showAd(currentActivity, () -> {
                if (postAction != null) postAction.run();
                adLoader.loadAd(currentActivity);
            });
        } else  {
            if (postAction != null) postAction.run();
        }
    }


    public static void loadAppOpenAd(Activity currentActivity, Runnable postAction) {
        AppOpenAdLoader appOpenAdLoader = AppOpenAdLoader.getInstance();
        appOpenAdLoader.postAction(() -> {
            appOpenAdLoader.loadAd(currentActivity);
            if (postAction != null)
            postAction.run();
        });
        appOpenAdLoader.showAd(currentActivity);
    }

    public static void startActivityWithAppOpenAd(Activity currentActivity, Class<? extends Activity> dest) {
        AppOpenAdLoader appOpenAdLoader = AppOpenAdLoader.getInstance();
        appOpenAdLoader.postAction(() -> {
            AndroidUtility.console("Running Post Action");
            AndroidUtility.startActivity(currentActivity, dest);
            // load ad for the next time
            appOpenAdLoader.loadAd(currentActivity);
        });
        appOpenAdLoader.showAd(currentActivity);
    }


}
