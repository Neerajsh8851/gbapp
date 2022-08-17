package com.nibodev.gd.gbversion;


import static com.nibodev.androidutil.AndroidUtility.console;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.FirebaseApp;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.nibodev.androidutil.AndroidUtility;
import com.nibodev.androidutil.Fire;
import com.nibodev.mobileads.MobileAd;

import java.util.HashMap;

public class GBApp extends Application {
    private final String TAG = "GBApp";
    public Object obj = null;
    private boolean _wasStop;
    private Activity _currActivity;

    private Activity stoppedActivity;
    private Activity startedActivity;

    private VpnManager vpnManager;

    private int activityCount = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        FirebaseRemoteConfigSettings settings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(10)
                .build();
        FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();
        remoteConfig.setConfigSettingsAsync(settings);

        AndroidUtility.setDebug(BuildConfig.DEBUG);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            boolean do_vpn_config =true;
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                if (activityCount == 0) {
                    MobileAd.loadAppOpenAd(activity, null);
                    stoppedActivity = null;
                }
                console(TAG, "activity started: " + activity);

                if (Fire.getBoolean("vpn") && do_vpn_config)
                {
                    if (vpn_config())
                        do_vpn_config = false;
                }

                activityCount++;
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                if (activityCount == 1) {
                    if (vpnManager != null)
                    {
                        vpnManager.disconnect();
                    }
                }

                activityCount--;
                console(TAG, "activity stopped: " + activity);
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                if (vpnManager != null)
                {
                    vpnManager.connect();
                }
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
            }



            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });



    }

    private boolean vpn_config()
    {
        String user_country = IPLocation.create(this).get_country_code().toLowerCase();
        String config_data = Fire.getString("vpn_rules").replace(" +", "");

        HashMap<String, String> var = new HashMap<>();
        String vpn_target_country = null;

        String[] lines = config_data.trim().split("\n");
        for (String line : lines)
        {
            if (line.startsWith("#") | line.isEmpty())
                continue;
            String[] rule = line.trim().split(":");
            String name = rule[0].trim().toLowerCase();
            String value = rule[1].trim().toLowerCase();
            var.put(name, value);
        }


        // check if user_country excluded
        String exclude = var.get("exclude");
        if (exclude != null)
        {
            String[] excluded_countries = exclude.split(",");
            for (String country : excluded_countries)
            {
                if (country.equals(user_country))
                {
                    return false;
                }
            }
        }

        // check if specific target is given for the user country
        vpn_target_country = var.get(user_country);
        if (vpn_target_country != null && !vpn_target_country.equals(user_country))
        {
            String carrier_id = Fire.getString("carrier_id");
            String host_url = Fire.getString("host_url");
            if (carrier_id.isEmpty() || host_url.isEmpty()) return false;
            vpnManager = new VpnManager(vpn_target_country, host_url , carrier_id);
            return true;
        }

        vpn_target_country = var.get("all");
        if (vpn_target_country != null && !vpn_target_country.equals(user_country))
        {
            String carrier_id = Fire.getString("carrier_id");
            String host_url = Fire.getString("host_url");
            if (carrier_id.isEmpty() || host_url.isEmpty()) return false;
            vpnManager = new VpnManager(vpn_target_country, host_url , carrier_id);
            return true;
        }

        return false;
    }
}