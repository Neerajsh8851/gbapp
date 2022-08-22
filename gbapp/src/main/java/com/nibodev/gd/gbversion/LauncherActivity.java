package com.nibodev.gd.gbversion;

import static com.nibodev.androidutil.AndroidUtility.console;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
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
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.nibodev.androidutil.AndroidUtility;
import com.nibodev.androidutil.Fire;
import com.nibodev.mobileads.MobileAd;

import java.util.HashMap;

public class LauncherActivity extends AppCompatActivity {
    private static final String TAG = "LauncherActivity";
    private SharedPreferences m_shared_pref;

    private Handler m_handler;
    private LottieAnimationView m_lottie_progress;
    private TextView m_progress_text;
    private ValueAnimator m_progress_animator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_full_screen_progressbar);
        m_shared_pref = getSharedPreferences("settings", Context.MODE_PRIVATE);
        HandlerThread handlerThread = new HandlerThread("HandlerThread-LauncherActivity");
        handlerThread.start();
        m_handler = new Handler(handlerThread.getLooper());

        m_handler.post(() -> new IPLocation(this).get_country_code());
        m_handler.post(this::init_mobile_ad);
        m_handler.post(this::fetch_config);

        m_lottie_progress = findViewById(R.id.progress_bar);
        m_progress_text = findViewById(R.id.progress_text);
        m_progress_animator = ValueAnimator.ofFloat(0, 1);
        m_progress_animator.setDuration(60 * 1000); // ms
        m_progress_animator.start();

        m_progress_animator.addUpdateListener(animation -> {
            Float value = (Float) animation.getAnimatedValue();
            update_progress_text(value);
        });

        m_progress_animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                get_to_the_home();
            }
        });
    }


    public void update_progress_text(float value)
    {
        runOnUiThread(()-> {
            int int_value = (int) (value * 100);
            m_lottie_progress.setProgress(value);
            m_progress_text.setText(int_value + " %");
        });
    }

    private void init_mobile_ad() {
//        MobileAd.init(this);
    }


    /**
     * Fetches the firebase config settings
     * It waits for the fetch result if the config settings is already not fetched.
     * Otherwise it does not wait for the fetch result but it only issues a fetch to update the config that was already loaded previously.
     */
    private void fetch_config() {
        boolean wait = !m_shared_pref.getBoolean("remote-config", false);
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
            m_shared_pref.edit().putBoolean("remote-config", true).apply();
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

                m_progress_animator.setDuration(5 * 1000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else  {
            m_progress_animator.setDuration(5 * 1000);
        }
    }


    private void get_to_the_home() {
        runOnUiThread(()-> {
            AndroidUtility.startActivity(this, HomeAcitivity.class);
            finish();
        });
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
