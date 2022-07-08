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

    public static void interAdActivity(Activity curr) {
        if (isConnectedToNetwork(curr) && getAdClickCounter().canTrigger()) {
            Intent intent = new Intent(curr, InterstitialAdActivity.class);
            curr.startActivity(intent);
        }
    }


    public static void interAdActivity(Activity currentActivity, Class<?> dest, IntentModifier intentModifier) {
        Intent intent;
        if (isConnectedToNetwork(currentActivity) && getAdClickCounter().canTrigger()) {
            intent = new Intent(currentActivity, InterstitialAdActivity.class);
            intent.putExtra("next-activity", dest.getName());
        } else {
            intent = new Intent(currentActivity, dest);
        }
        if (intentModifier != null) intentModifier.modifier(intent);
        currentActivity.startActivity(intent);
    }

    public static void interAdActivity(Activity currentActivity, Class<?> dest) {
        interAdActivity(currentActivity, dest, null);
    }

    public static void appOpenAdActivity(Activity curr) {
        if (isConnectedToNetwork(curr)) {
            curr.startActivity(
                    new Intent(curr, AppOpenAdActivity.class)
            );
        }
    }

    public static void startActivityWithAppOpenAd(Activity currentActivity, Class<?> dest) {
        Intent intent;
        if (isConnectedToNetwork(currentActivity)) {
            intent = new Intent(currentActivity, AppOpenAdActivity.class);
            intent.putExtra("next-activity", dest.getName());
            currentActivity.startActivity(intent);
        } else {
            intent = new Intent(currentActivity, dest);
        }
        currentActivity.startActivity(intent);
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
}
