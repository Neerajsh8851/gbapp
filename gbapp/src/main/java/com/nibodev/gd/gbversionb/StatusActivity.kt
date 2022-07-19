package com.nibodev.gd.gbversionb

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.nibodev.androidutil.AndroidUtility.shareThisApp
import com.nibodev.androidutil.Fire
import com.nibodev.domain.console
import com.nibodev.gd.gbversionb.databinding.ActivityStatusBinding
import com.nibodev.mobileads.MobileAd
import com.nibodev.mobileads.NativeAdLoader

class StatusActivity : AppCompatActivity() {
    private val TAG = "StatusActivity"
    private var _binding: ActivityStatusBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityStatusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel = ViewModelProvider(this)[StatusModel::class.java]
        var tabIndex = 0

        binding.recentStatusTab.setOnClickListener {
            closeDrawer()
            if (tabIndex == 0) return@setOnClickListener
            tabIndex = 0
            // show the recent statuses
            replaceFragment(RecentStatusFragment())
            setTitle(resources.getString(R.string.saved_tab_text))
            currentTab(0)
        }

        binding.savedStatusTab.setOnClickListener {
            closeDrawer()
            if (tabIndex == 1) return@setOnClickListener
            tabIndex  = 1
            // show the saved statuses
            replaceFragment(SavedStatusFragment())
            currentTab(1)
        }

        binding.btnShare.setOnClickListener {
            console(TAG, "sharing this app")
            shareThisApp(this)
        }

        binding.iconRefresh.setOnClickListener {
            viewModel.newStatus.refresh()
            viewModel.savedStatus.refresh()
        }


        // Initially add Recent status fragment
        if (savedInstanceState == null) {
            console(TAG, "adding RecentStatusFragment")
            supportFragmentManager.beginTransaction()
                .add(R.id.container, RecentStatusFragment())
                .commit()
            currentTab(0)
            binding.root.postInvalidate()
        }

        val adId = Fire.getString("native_ad_id_2")
        NativeAdLoader(adId).attachNativeAd(binding.templateView2)

        val bannerAdId = Fire.getString("banner_ad_id_1")
        val bannerView = AdView(this)
        bannerView.adUnitId = bannerAdId
        bannerView.setAdSize(AdSize.FULL_BANNER)
        bannerView.loadAd(AdRequest.Builder().build())
        binding.bannerAdContainer.addView(bannerView)
    }

    fun hideRefreshIcon() {
        binding.iconRefresh.visibility = View.GONE
    }

    fun showRefreshIcon() {
        binding.iconRefresh.visibility = View.VISIBLE
    }

    /**
     * Update the ui for the current tab page
     */
    private fun currentTab(index: Int) {
        val typedValue = TypedValue()
        theme.resolveAttribute(androidx.appcompat.R.attr.colorPrimary, typedValue, true)
        val selectedColor = typedValue.data
        val unSelectedColor = 0xa0292929

        var tab1Color: Int = unSelectedColor.toInt()
        var tab2Color: Int = unSelectedColor.toInt()

        when (index) {
            0 -> {
                tab1Color = selectedColor
                setTitle(resources.getString(R.string.recent_tab_text))
            }
            1 -> {
                tab2Color = selectedColor
                setTitle(resources.getString(R.string.saved_tab_text))
            }
        }

        binding.tab1Dot.setColorFilter(tab1Color)
        binding.tab1Text.setTextColor(tab1Color)
        binding.tab2Dot.setColorFilter(tab2Color)
        binding.tab2Text.setTextColor(tab2Color)
    }

    private fun closeDrawer() {
        val valueAnimator = ValueAnimator.ofFloat(binding.motionLayout.progress, 0f)
        valueAnimator.addUpdateListener {
            binding.motionLayout.progress = it.animatedValue as Float
        }
        valueAnimator.duration = resources.getInteger(R.integer.anim_medium_duration).toLong()
        valueAnimator.start()
    }


    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
        binding.root.postInvalidate()
    }

    private fun setTitle(title: String) {
        binding.titleView.text = title
    }


    private fun isDrawerOpen(): Boolean {
        return binding.root.progress == 1f
    }

    override fun onBackPressed() {
        if (isDrawerOpen()) {
            closeDrawer()
        } else {
            MobileAd.interAdActivity(this)
            finishAfterTransition()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            binding.templateView2.destroyNativeAd()
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            _binding = null
        }
    }
}