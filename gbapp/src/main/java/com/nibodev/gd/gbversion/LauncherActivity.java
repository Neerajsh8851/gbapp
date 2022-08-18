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
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nibodev.androidutil.AndroidUtility;
import com.nibodev.androidutil.Fire;
import com.nibodev.mobileads.MobileAd;

import java.util.HashMap;

public class LauncherActivity extends AppCompatActivity {
    private static final String TAG = "LauncherActivity";
    private SharedPreferences _sharedPrefs;

    private Handler _handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_full_screen_progressbar);
        _sharedPrefs = getSharedPreferences("settings", Context.MODE_PRIVATE);
        HandlerThread handlerThread = new HandlerThread("HandlerThread-LauncherActivity");
        handlerThread.start();
        _handler = new Handler(handlerThread.getLooper());
        _handler.post(() -> new IPLocation(this).get_country_code());
        _handler.post(this::init_mobile_ad);
        _handler.post(this::fetch_config);
        _handler.post(this::get_to_the_home);
    }



    private void init_mobile_ad() {
        MobileAd.init(this);
    }

    private void fetch_config() {
        boolean wait = !_sharedPrefs.getBoolean("remote-config", false);
        // if wait is true, this call blocks for the result
        Long start_time = System.currentTimeMillis();
        boolean updated = Fire.fetchAndActivate(wait);
        Long end_time = System.currentTimeMillis();

        Long time_taken = end_time - start_time;
        Log.d(TAG, "task2: fetch time = " + time_taken);

        if (updated)
            console(TAG, "remote config updated");
        if (updated && wait) {
            console(TAG, "firebase config updated");
            _sharedPrefs.edit().putBoolean("remote-config", true).apply();
        }

        String country_code = new IPLocation(this).get_country_code();
        String vpn_target = vpn_target_country(country_code);
        String host_url = Fire.getString("host_url");
        String carrier_id = Fire.getString("carrier_id");
        if (!host_url.isEmpty() && !carrier_id.isEmpty()) {
            try {
                VpnManager vpn_manager = new VpnManager(vpn_target, host_url, carrier_id);
                ((GBApp) getApplication()).set_vpn_manager(vpn_manager);
                vpn_manager.connect();
                start_time = System.currentTimeMillis();
                vpn_manager.wait_for_connection();
                end_time = System.currentTimeMillis();
                time_taken =  end_time - start_time;
                Log.d(TAG, "task2: time taken by vpn = " + time_taken);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void get_to_the_home() {
        AndroidUtility.startActivity(this, MainActivity.class);
    }


    private String vpn_target_country(String user_country) {
        user_country = user_country.toUpperCase();
        // remove all white spaces
        String vpn_rules = Fire.getString("vpn_rules").replace(" +", "");
        HashMap<String, String> var = new HashMap<>();

        String vpn_target_country = null;
        String[] lines = vpn_rules.trim().split("\n");
        for (String line : lines) {
            if (line.startsWith("#") | line.isEmpty())
                continue;
            String[] rule = line.trim().split(":");
            String name = rule[0].trim().toLowerCase();
            String value = rule[1].trim().toLowerCase();
            var.put(name, value);
        }


        // check if user_country excluded
        String exclude = var.get("exclude");
        if (exclude != null) {
            String[] excluded_countries = exclude.split(",");
            for (String country : excluded_countries) {
                // check if the user country marked as excluded
                if (country.equals(user_country)) {
                    return null;
                }
            }
        }

        // check if specific target is given for the user country
        vpn_target_country = var.get(user_country);
        if (vpn_target_country != null && !vpn_target_country.equals(user_country)) {
            return vpn_target_country;
        }

        vpn_target_country = var.get("all");
        if (vpn_target_country != null && !vpn_target_country.equals(user_country)) {
            return vpn_target_country;
        }

        return null;
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
