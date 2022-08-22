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
import com.nibodev.mobileads.MobileAd;

public class GBApp extends Application {

    // global static
    private static GBApp instance;

    private final String TAG = "GBApp";
    public Object obj = null;
    private int activityCount = 0;
    private int activity_objects = 0;
    private boolean app_finish_launching;

    private VpnManager vpn_manager;

    public static GBApp get_app()
    {
        return instance;
    }

    public void set_vpn_manager(VpnManager vpn_manager)
    {
        this.vpn_manager = vpn_manager;
    }

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
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                activity_objects++;
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                if (activityCount == 0) {
                    if (app_finish_launching) {
                        // on come back to app
                        MobileAd.loadAppOpenAd(activity, null);
                    } else {
                        app_finish_launching = true;
                    }
                }
                console(TAG, "activity started: " + activity);
                activityCount++;
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                if (activityCount == 1) {

                }
                activityCount--;
                console(TAG, "activity stopped: " + activity);
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
            }


            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                activity_objects--;
                // on application quit
                if (activity_objects == 0)
                {
                    if (vpn_manager != null)
                    {
                        vpn_manager.disconnect();
                    }
                }
            }
        });
        instance = this;
    }
}