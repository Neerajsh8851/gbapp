package com.nibodev.gd.gbversionb;


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
    private final String TAG = "GBApp";
    public Object obj = null;
    private boolean _wasStop;
    private Activity _currActivity;

    private Activity stoppedActivity;
    private Activity startedActivity;

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

            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                startedActivity = activity;
                if (startedActivity == stoppedActivity) {
                    MobileAd.appOpenAdActivity(activity);
                    stoppedActivity = null;
                }
                console(TAG, "activity started: " + activity);
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                if (startedActivity == activity) stoppedActivity = activity;
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

            }
        });
    }
}