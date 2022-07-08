package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.stylish;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;

public class FloatingStylishCreateShortCutActivitydfgds extends Activity {
    private static final int REQUEST_CODE_WINDOW_OVERLAY_PERMISSION = 10001;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        if (Intent.ACTION_CREATE_SHORTCUT.equals(getIntent().getAction())) {
            if (Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, REQUEST_CODE_WINDOW_OVERLAY_PERMISSION);
            } else {
                onSuccess();
            }
        }
    }

    private void onSuccess() {
        Intent.ShortcutIconResource icon = Intent.ShortcutIconResource.fromContext(this, R.drawable.whatsic_bubble);

        Intent intent = new Intent();
        Intent launchIntent = new Intent(this, FloatingStylishOpenShortCutActivitysad.class);

        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, launchIntent);
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.app_name));
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);

        setResult(RESULT_OK, intent);
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
