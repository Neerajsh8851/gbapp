package com.nibodev.gd.gbversion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import com.nibodev.androidutil.Fire;
import com.nibodev.androidutil.AndroidUtility;
import com.nibodev.gd.gbversion.databinding.ActivityWhatsDirectChatBinding;
import com.nibodev.mobileads.MobileAd;
import com.nibodev.mobileads.NativeAdLoader;

public class WhatsDirectChatActivity extends AppCompatActivity {
    ActivityWhatsDirectChatBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWhatsDirectChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSend.setOnClickListener(v -> {
            String messageStr = binding.messages.getText().toString().trim();
            String phoneStr = binding.phonenumber.getText().toString().trim();
            if (phoneStr.isEmpty()) {
                binding.phonenumber.setError("Please Enter Phone");
            } else if (messageStr.isEmpty()) {
                binding.messages.setError("Please Enter Message");
            } else {
                binding.ccp.registerCarrierNumberEditText(binding.phonenumber);
                phoneStr = binding.ccp.getFullNumber();
                boolean isInstalled = AndroidUtility.isWhatsAppInstalled(getPackageManager());
                //if whatsapp is installed it will be true otherwise false
                if (isInstalled) {
                    //Whatsapp send message using Intent
                    Intent whatsapp = new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://api.whatsapp.com/send?phone=" + phoneStr + "&text=" + messageStr)
                    );
                    startActivity(whatsapp);
                    binding.phonenumber.setText("");
                    binding.messages.setText("");
                } else {
                    Toast.makeText(
                            this,
                            "Whatsapp is not Installed on your Device",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

        binding.backArrow.setOnClickListener(v -> {
            finishAfterTransition();
        });

        String adId = Fire.getString("native_ad_id_3");
        new NativeAdLoader(adId).attachNativeAd(binding.templateView3);
    }


    @Override
    public void onBackPressed() {
        MobileAd.interAdActivity(this);
        finishAfterTransition();
    }

    public void onDestroy() {
        super.onDestroy();
        try {
            binding.templateView3.destroyNativeAd();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            binding = null;
        }
    }
}