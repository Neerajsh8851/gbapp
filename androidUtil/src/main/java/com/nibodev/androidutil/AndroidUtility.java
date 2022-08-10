package com.nibodev.androidutil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class AndroidUtility {
    private static String TAG = "AndroidUtility";
    private static boolean debugMode = false;

    public static void setDebug(boolean mode) {
        debugMode = mode;
    }

    public static void console(String msg) {
        if (debugMode) {
            Log.d("console", msg);
        }
    }

    public static void console(final String tag, String msg) {
        if (debugMode) {
            Log.d("console/" + tag, msg);
        }
    }

    public static void displayToast(Activity activity, String msg, int duration) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, msg, duration).show();
            }
        });
    }

    public static boolean isWhatsAppInstalled(PackageManager pm) {
        String whatsPkg = "com.whatsapp";
        boolean result = false;
        try {
            PackageInfo info = pm.getPackageInfo(whatsPkg, PackageManager.GET_ACTIVITIES);
            if (info != null) result = true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void shareThisApp(Activity activity) {
        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("text/plain");
        String body = Fire.getString("share_text_body");
        String sub = Fire.getString("share_sub_body");
        myIntent.putExtra(Intent.EXTRA_SUBJECT, sub);
        myIntent.putExtra(Intent.EXTRA_TEXT, body);
        activity.startActivity(Intent.createChooser(myIntent, "Share Using"));
    }

    public static void openPrivacyPolicyInWeb(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://w3cleverprogrammer.blogspot.com/p/wa-saver-privacy-policy.html"));
        activity.startActivity(Intent.createChooser(intent, "View in"));
    }

    public static void startActivity(Activity currentAc, Class<?> dest) {
        Intent intent = new Intent(currentAc, dest);
        currentAc.startActivity(intent);
    }

    public static boolean isConnectedToNetwork(Context context) {
        boolean connected = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null) connected = netInfo.isAvailable() && netInfo.isConnectedOrConnecting();
        return connected;
    }
}
