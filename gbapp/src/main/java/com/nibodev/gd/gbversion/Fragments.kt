package com.nibodev.gd.gbversion

import android.Manifest
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.doOnLayout
import androidx.documentfile.provider.DocumentFile
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.nibodev.androidutil.AndroidUtility
import com.nibodev.androidutil.Fire
import com.nibodev.domain.MediaEntity
import com.nibodev.domain.console
import com.nibodev.gd.gbversion.databinding.ContentRecentStatusCardBinding
import com.nibodev.gd.gbversion.databinding.ContentStatusListBinding
import com.nibodev.gd.gbversion.databinding.CustomTabBinding
import com.nibodev.gd.gbversion.databinding.FragmentStatusBinding
import com.nibodev.mobileads.MobileAd
import com.nibodev.mobileads.NativeAdLoader

abstract class StatusListFragment : Fragment() {
    private val TAG = "StatusListFragment"

    private var _itemClickHandler: ((View, Int) -> Unit)? = null
    private var _itemDownloadHandler: ((Int) -> Unit)? = null
    protected lateinit var viewmodel: StatusModel
    private var _binding: ContentStatusListBinding? = null
    private val binding get() = _binding!!
    private val transitionDuration = 500L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*
        return RecyclerView(requireContext()).run {
            // take the full size, match parent: -1
            layoutParams = RecyclerView.LayoutParams(-1, -1)
            this
        }
        */
        _binding = ContentStatusListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewmodel = ViewModelProvider(requireActivity())[StatusModel::class.java]
        binding.feedbackView.visibility = View.GONE
        binding.progressView.visibility = View.GONE
        binding.statusListView.visibility = View.GONE
        populate(binding)
    }

    fun setOnItemClickHandler(handler: (View, Int) -> Unit) {
        _itemClickHandler = handler
    }

    fun setOnItemDownloadHandler(handler: (Int) -> Unit) {
        _itemDownloadHandler = handler
    }


    abstract fun populate(binding: ContentStatusListBinding)

    /**
     * Recycler view adapter
     */
    inner class Adapter(private var dataset: List<MediaEntity>) :
        RecyclerView.Adapter<Adapter.ViewHolder>() {
        inner class ViewHolder(val binding: ContentRecentStatusCardBinding) :
            RecyclerView.ViewHolder(binding.root) {
            init {
                binding.image.setOnClickListener { image ->
                    console(TAG, "item clicked at pos: $absoluteAdapterPosition")
                    if (_itemClickHandler != null) {
                        _itemClickHandler!!(image, absoluteAdapterPosition)
                    } else {
                        console(TAG, "no click handler attached")
                    }
                }

                binding.btnDownload.setOnClickListener {
                    console(TAG, "download button clicked at pos: $absoluteAdapterPosition ")
                    _itemDownloadHandler?.invoke(absoluteAdapterPosition)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = ContentRecentStatusCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            /** REMOVE DOWNLOAD BUTTON **/
            if (this@StatusListFragment is SavedVideosStatusFragment || this@StatusListFragment is SavedImagesStatusFragment) {
                binding.btnDownload.visibility = View.GONE
            }
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            with(holder.binding.image) {
                setImageBitmap(null)
                doOnLayout {
                    setBitmap(dataset[position]) {
                        alpha = 0f
                        animate().alpha(1f)
                    }
                }
            }
        }

        override fun getItemCount(): Int = dataset.size

        fun changeDataSet(newData: List<MediaEntity>) {
            dataset = newData
            notifyDataSetChanged()
        }
    }

    protected fun hideFeedbackView(onComplete: () -> Unit) {
        val view = binding.feedbackView
        view.alpha = 1f
        view.animate()
            .alpha(0f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    view.visibility = View.GONE
                    onComplete()
                }
            })
    }

    protected fun showFeedbackView(onComplete: () -> Unit) {
        val view = binding.feedbackView
        view.visibility = View.VISIBLE
        view.alpha = 0f
        view.animate()
            .alpha(1f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    onComplete()
                }
            })
    }

    protected fun hideListView(onComplete: () -> Unit) {
        val view = binding.statusListView
        view.alpha = 1f
        view.animate()
            .alpha(0f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    view.visibility = View.GONE
                    onComplete()
                }
            })
    }

    protected fun showListView(onComplete: () -> Unit) {
        val view = binding.statusListView
        view.visibility = View.VISIBLE
        view.alpha = 0f
        view.animate()
            .alpha(1f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    onComplete()
                }
            })
    }

    protected fun hideProgressView(onComplete: () -> Unit) {
        val view = binding.progressView
        view.alpha = 1f
        view.animate()
            .alpha(0f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    view.visibility = View.GONE
                    onComplete()
                }
            })
    }

    protected fun showProgressView(onComplete: () -> Unit) {
        val view = binding.progressView
        view.visibility = View.VISIBLE
        view.alpha = 0f
        view.animate()
            .alpha(1f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    onComplete()
                }
            })
    }

    protected fun showNativeAd() {
        // TODO: implement this to show native ad on top of the feedback after some time.
    }
}


class RecentVideosStatusFragment : StatusListFragment() {
    private val TAG = "RecentVideosStatusFragment"
    @SuppressLint("NotifyDataSetChanged")
    override fun populate(binding: ContentStatusListBinding) {
        val adapter = Adapter(listOf())
        binding.statusListView.adapter = adapter
        binding.statusListView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val recentStatus = viewmodel.newStatus
        var isLoading: Boolean

        showProgressView {}
        recentStatus.isLoading.observe(requireActivity()) { loading ->
            isLoading = loading
            if (isLoading) {
                showProgressView {}
            } else {
                hideProgressView {}
            }
        }

        viewmodel.newStatus.videos.observe(requireActivity()) { videos ->
            if (videos != null && videos.isNotEmpty()) {
                console(TAG, "calling showStatus()")
                showListView{}
                hideFeedbackView{}
                adapter.changeDataSet(videos)
            } else {
                console(TAG, "calling showfeedbackView()")
                hideListView {}
                showFeedbackView{}
            }
        }
    }
}


class RecentImagesStatusFragment : StatusListFragment() {
    private val TAG = "RecentImagesStatusFragment"

    @SuppressLint("NotifyDataSetChanged")
    override fun populate(binding: ContentStatusListBinding) {
        val adapter = Adapter(listOf())
        binding.statusListView.adapter = adapter
        binding.statusListView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        val recentStatus = viewmodel.newStatus
        var isLoading: Boolean
        recentStatus.isLoading.observe(requireActivity()) { loading ->
            isLoading = loading
            if (isLoading) {
                console(TAG, "calling showProgressBar()")
                showProgressView {}
            } else {
                console(TAG, "calling hideProgressBar()")
                hideProgressView {}
            }
        }

        recentStatus.images.observe(requireActivity()) { images ->
            if (images != null && images.isNotEmpty()) {
                showListView {}
                hideFeedbackView {}
                adapter.changeDataSet(images)
            } else {
                hideListView {}
                showFeedbackView{}
            }
        }
    }
}

class SavedVideosStatusFragment : StatusListFragment() {
    private val TAG = "SavedVideosStatusFragment"
    @SuppressLint("NotifyDataSetChanged")
    override fun populate(binding: ContentStatusListBinding) {
        val adapter = Adapter(listOf())
        binding.statusListView.adapter = adapter
        binding.statusListView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val savedStatus = viewmodel.savedStatus
        var isLoading : Boolean
        showProgressView {}
        savedStatus.isLoading.observe(requireActivity()) { loading ->
            isLoading = loading
            if (isLoading) {
                showProgressView {}
            } else {
                hideProgressView {}
            }
        }

        savedStatus.videos.observe(requireActivity()) { videos ->
            if (videos != null && videos.isNotEmpty()) {
                console(TAG, "calling showStatus()")
                showListView{}
                hideFeedbackView{}
                adapter.changeDataSet(videos)
            } else {
                console(TAG, "calling showfeedbackView()")
                hideListView {}
                showFeedbackView{}
            }
        }
    }
}

class SavedImagesStatusFragment : StatusListFragment() {
    private val TAG = "SavedImagesStatusFragment"
    @SuppressLint("NotifyDataSetChanged")
    override fun populate(binding: ContentStatusListBinding) {
        val adapter = Adapter(listOf())
        binding.statusListView.adapter = adapter
        binding.statusListView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        val savedStatus = viewmodel.savedStatus
        var isLoading: Boolean
        savedStatus.isLoading.observe(requireActivity()) { loading ->
            isLoading = loading
            if (isLoading) {
                console(TAG, "calling showProgressBar()")
                showProgressView {}
            } else {
                console(TAG, "calling hideProgressBar()")
                hideProgressView {}
            }
        }

        savedStatus.images.observe(requireActivity()) { images ->
            if (images != null && images.isNotEmpty()) {
                showListView {}
                hideFeedbackView {}
                adapter.changeDataSet(images)
            } else {
                hideListView {}
                showFeedbackView{}
            }
        }
    }
}


abstract class StatusFragment : Fragment() {
    private val _tag = "StatusFragment"
    private var _binding: FragmentStatusBinding? = null
    private val binding get() = _binding!!
    protected lateinit var viewModel: StatusModel

    private lateinit var activityLauncher: ActivityResultLauncher<Intent>
    private var activityResultCallback: ((Uri) -> Unit)? = null
    private lateinit var permResultLauncher: ActivityResultLauncher<Array<String>>
    private var permResultCallback: ((Boolean) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                val uri = result.data?.data
                if (uri != null) {
                    activityResultCallback?.invoke(uri)
                }
                activityResultCallback = null
            }

        permResultLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { perm ->
                val result =
                    perm[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true && perm[Manifest.permission.READ_EXTERNAL_STORAGE] == true
                if (result) {
                    permResultCallback?.invoke(result)
                }
                permResultCallback = null
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentStatusBinding.inflate(layoutInflater).also {
            _binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ensurePerms {
            setupViewPager()
        }
    }

    private fun showPermPopup() {
        val ac = requireActivity() as StatusActivity
        ac.hideRefreshIcon()
        binding.tabContainer.visibility = View.GONE
        with(binding.permPopup.root) {
            visibility = View.VISIBLE
            alpha = 0f
            scaleX = 0.5f
            scaleY = 0.5f
            animate().alpha(1f).scaleX(1f).scaleY(1f)
                .apply { duration = resources.getInteger(R.integer.anim_medium_duration).toLong() }
        }

        val adUnitId = Fire.getString("native_ad_id_5")
        val loader = NativeAdLoader(adUnitId)
        loader.attachNativeAd(binding.permPopup.templateView5)
    }

    private fun hidePermPopup() {
        val ac = requireActivity() as StatusActivity
        ac.showRefreshIcon()
        with(binding.permPopup.root) {
            animate().alpha(0f).scaleX(0.5f).scaleY(0.5f).apply {
                duration = resources.getInteger(R.integer.anim_medium_duration).toLong()
                setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        visibility = View.GONE
                    }
                })
            }
        }

        with(binding.tabContainer) {
            visibility = View.VISIBLE
            alpha = 0f
            animate().alpha(1f).duration =
                resources.getInteger(R.integer.anim_medium_duration).toLong()
        }
        try {
            binding.permPopup.templateView5.destroyNativeAd()
        } catch (ex: Exception) {
        }

    }

    private fun ensurePerms(onEnsure: () -> Unit) {
        val context = requireContext()
        when {
            // when the scoped storage is enable
            Build.VERSION.SDK_INT > Build.VERSION_CODES.P -> {
                val hasUriPerms = context.hasUriPerm(context.getWaUriTree())
                if (hasUriPerms) {
                    onEnsure()
                } else {
                    val button = binding.permPopup.reqPermButton
                    showPermPopup()
                    activityResultCallback = { uri ->
                        hidePermPopup()
                        context.preserveUri(uri)
                        onEnsure()
                    }
                    button.setOnClickListener {
                        buildOpenUriTreeIntent(context, OPEN_URI_INITIAL_PATH).run {
                            activityLauncher.launch(this)
                        }
                    }
                }
            }
            else -> {
                if (context.hasReadExternalStoragePerm()) {
                    onEnsure()
                } else {
                    val button = binding.permPopup.reqPermButton
                    showPermPopup()
                    permResultCallback = { result ->
                        if (result) {
                            hidePermPopup()
                            onEnsure()
                        }
                    }

                    button.setOnClickListener {
                        permResultLauncher.launch(
                            arrayOf(
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                            )
                        )
                    }
                }
            }
        }
    }


    /**
     * Setup viewpager after ensuring required permissions.
     */
    private fun setupViewPager() {
        console(_tag, "setup viewpager content")
        // reference to shared view model
        viewModel = ViewModelProvider(requireActivity())[StatusModel::class.java].apply {
            when {
                Build.VERSION.SDK_INT > Build.VERSION_CODES.P -> {
                    // TODO: add firebase service here so that you can take report about the returned value
                    // you can check for, is it null, if yes then why?
                    DocumentFile.fromTreeUri(requireContext(), requireContext().getWaUriTree()!!)
                        ?.let {
                            initWith(it, SAVED_WA_STATUS_PATH)
                        }
                }
                else -> {
                    val dir =
                        "${Environment.getExternalStorageDirectory().absolutePath}/WhatsApp/Media/.Statuses"
                    initWith(dir, SAVED_WA_STATUS_PATH)
                }
            }
        }

        binding.viewPager.adapter = onCreateViewPagerAdapter()
        // sync the tab layout with the viewpager
        TabLayoutMediator(binding.tabContainer, binding.viewPager) { tab, pos ->
            val customTab = CustomTabBinding.inflate(layoutInflater).root
            when (pos) {
                0 -> customTab.text = resources.getString(R.string.image_tab_text)
                1 -> customTab.text = resources.getString(R.string.video_tab_text)
            }
            tab.customView = customTab
        }.attach()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                MobileAd.loadInterAd(requireActivity(), null);
            }
        })
    }

    abstract fun onCreateViewPagerAdapter(): FragmentStateAdapter

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


class RecentStatusFragment : StatusFragment() {
    private val _tag = "RecentStatusFragment"
    override fun onCreateViewPagerAdapter(): FragmentStateAdapter {
        return object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 2

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> {
                        val risf = RecentImagesStatusFragment()
                        risf.setOnItemClickHandler { _, pos ->
                            val param =
                                DetailedStatusActivity.Params(true, viewModel.newStatus.images, pos)
                            val app = requireActivity().application as GBApp
                            app.obj = param
                            MobileAd.loadInterAd(requireActivity()) {
                                AndroidUtility.startActivity(requireActivity(), DetailedStatusActivity::class.java)
                            }

                        }

                        risf.setOnItemDownloadHandler { pos ->
                            val item = viewModel.newStatus.images.value?.get(pos)
                            item?.let {
                                requireContext().downloadStatus(it)
                                MobileAd.loadInterAd(requireActivity(), null)
                            }
                        }
                        risf
                    }
                    else -> {
                        val rvsf = RecentVideosStatusFragment()
                        rvsf.setOnItemClickHandler { _, pos ->
                            console(_tag, "Creating parameters")
                            val param =
                                DetailedStatusActivity.Params(true, viewModel.newStatus.videos, pos)
                            val app = requireActivity().application as GBApp
                            app.obj = param
                            MobileAd.loadInterAd(requireActivity()) {
                                AndroidUtility.startActivity(requireActivity(), DetailedStatusActivity::class.java)
                            }
                        }
                        rvsf.setOnItemDownloadHandler { pos ->
                            val item = viewModel.newStatus.videos.value?.get(pos)
                            item?.let {
                                requireContext().downloadStatus(it)
                                MobileAd.loadInterAd(requireActivity(), null)
                            }
                        }
                        rvsf
                    }

                }
            }
        }
    }
}


class SavedStatusFragment : StatusFragment() {
    private val _tag = "SavedStatusFragment"
    override fun onCreateViewPagerAdapter(): FragmentStateAdapter {
        return object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 2
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> {
                        val sisf = SavedImagesStatusFragment()
                        sisf.setOnItemClickHandler { _, pos ->
                            val param = DetailedStatusActivity.Params(
                                false,
                                viewModel.savedStatus.images,
                                pos
                            )
                            val app = requireActivity().application as GBApp
                            app.obj = param
                            val intent =
                                Intent(requireContext(), DetailedStatusActivity::class.java)
                            startActivity(intent)
                        }
                        sisf
                    }
                    else -> {
                        val svsf = SavedVideosStatusFragment()
                        svsf.setOnItemClickHandler { _, pos ->
                            console(_tag, "Creating parameters")
                            val param =
                                DetailedStatusActivity.Params(
                                    false,
                                    viewModel.savedStatus.videos,
                                    pos
                                )
                            val app = requireActivity().application as GBApp
                            app.obj = param
                            val intent =
                                Intent(requireContext(), DetailedStatusActivity::class.java)
                            startActivity(intent)
                        }
                        svsf
                    }
                }
            }
        }
    }
}







