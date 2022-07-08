package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.stylish;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

public class FloatingStylishOpenShortCutActivitysad extends Activity {
    private static final int REQUEST_CODE_WINDOW_OVERLAY_PERMISSION = 10001;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        finish();
        if (!Settings.canDrawOverlays(this)) {
            System.out.println("can not draw overlays");
            Uri uri = Uri.parse("package:" + getPackageName());
            Intent activity = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, uri);
            startActivityForResult(activity, REQUEST_CODE_WINDOW_OVERLAY_PERMISSION);
        } else {
            onSuccess();
        }
    }

    private void onSuccess() {
        Intent intent = new Intent(this, FloatingStylishService.class);
        startService(intent);
        finish();
    }

    private void onFailure() {

        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_WINDOW_OVERLAY_PERMISSION) {
            if (Build.VERSION.SDK_INT <= 23 || Settings.canDrawOverlays(this)) {
                onSuccess();
            } else {
                onFailure();
            }
        }
    }
}
