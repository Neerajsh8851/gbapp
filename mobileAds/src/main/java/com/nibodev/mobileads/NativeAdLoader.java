package com.nibodev.mobileads;

import static com.nibodev.androidutil.AndroidUtility.console;

import android.graphics.drawable.ColorDrawable;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.nativead.NativeAdOptions;

public class NativeAdLoader {
    private final String TAG = "NativeAdLoader";
    private String _nativeAdId;
    private Runnable _onAttach;

    public NativeAdLoader(String nativeAdId) {
        _nativeAdId = nativeAdId;
        console(TAG, "provided ad id = " + nativeAdId);
    }

    public NativeAdLoader(String nativeAdId, Runnable onAttach) {
        this(nativeAdId);
        _onAttach = onAttach;
    }

    public void attachNativeAd(TemplateView nativeAdView) {
        VideoOptions videoOptions = new VideoOptions.Builder()
                .setStartMuted(true)
                .build();

        NativeAdOptions options = new NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build();

        AdLoader adLoader = new AdLoader.Builder(nativeAdView.getContext(), _nativeAdId)
                .forNativeAd(
                        nativeAd -> {
                            NativeTemplateStyle style = new NativeTemplateStyle.Builder()
                                    .withMainBackgroundColor(new ColorDrawable(0xffffffff))
                                    .build();
                            nativeAdView.setStyles(style);
                            nativeAdView.setNativeAd(nativeAd);
                            nativeAdView.setVisibility(View.VISIBLE);
                            nativeAdView.setAlpha(0f);
                            nativeAdView.animate().alpha(1f);
                            if (_onAttach != null) _onAttach.run();
                        }
                )
                .withNativeAdOptions(options)
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        console(TAG, "Ad was loaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        console(TAG, "Could not load the ad: \n" + loadAdError);
                    }
                })
                .build();
        AdRequest request = new AdRequest.Builder().build();
        adLoader.loadAd(request);
    }
}
