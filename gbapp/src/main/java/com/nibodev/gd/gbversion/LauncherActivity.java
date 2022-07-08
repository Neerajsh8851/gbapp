package com.nibodev.gd.gbversion;

import static com.nibodev.androidutil.AndroidUtility.console;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nibodev.androidutil.Fire;
import com.nibodev.mobileads.MobileAd;

public class LauncherActivity extends AppCompatActivity {
    private static final String TAG = "LauncherActivity";
    private SharedPreferences _sharedPrefs;

    private Handler _handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _sharedPrefs = getSharedPreferences("settings", Context.MODE_PRIVATE);
        HandlerThread handlerThread = new HandlerThread("HandlerThread-LauncherActivity");
        handlerThread.start();
        _handler = new Handler(handlerThread.getLooper());

        _handler.post(this::task1);
        _handler.post(this::task2);
        _handler.post(this::task3);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        console(TAG, "Launcher Activity has destroyed");
    }


    private void task1() {
        console(TAG, "executing task 1: init mobile ads");
        MobileAd.init(this);
    }

    private void task2() {
        console(TAG, "executing task 2: firebase remote config update");
        boolean wait = !_sharedPrefs.getBoolean("remote-config", false);
        // if wait is true, this call blocks for the result
        boolean updated = Fire.fetchAndActivate(wait);
        if (updated)
        console(TAG, "remote config updated");
        if (updated && wait) {
            console(TAG, "firebase config updated");
            _sharedPrefs.edit().putBoolean("remote-config", true).apply();
        }
    }

    private void task3() {
        console(TAG, "executing task 3: starting ad activity");
        nextActivity();
    }

    private void nextActivity() {
        runOnUiThread(()-> {
            MobileAd.startActivityWithAppOpenAd(LauncherActivity.this, MainActivity.class);
            finishAfterTransition();
        });
    }



    private void doOnConnect(Runnable action) {
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                System.out.println("got a network event" + intent);
                NetworkInfo netInfo = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
                if (netInfo != null) {
                    boolean available = netInfo.isAvailable() && netInfo.isConnectedOrConnecting();
                    console(TAG, "Network state connected: " + available);
                    if (available) {
                        action.run();
                        unregisterReceiver(this);
                    }
                }
            }
        };
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
    }
}
