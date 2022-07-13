package com.nibodev.gd.gbversion;
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.nibodev.domain.MediaEntity
import com.nibodev.domain.console
import com.nibodev.gd.gbversion.databinding.*
import java.io.File

private var _statuses: LiveData<List<MediaEntity>>? = null
private var _binding: ActivityDetailedStatusBinding? = null
private val binding get() = _binding!!
private val statuses get() = _statuses!!.value!!
private var isRecentStatus = false

/**
 * Detailed view of the whatsapp status videos or images.
 */

class DetailedStatusActivity : AppCompatActivity() {
    /**
     * Input params for this activity
     * this should be provide via GBApp.obj
     */
    data class Params(
        val isRecentStatus: Boolean,
        val status: LiveData<List<MediaEntity>>,
        val index: Int
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailedStatusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val inputParam = (application as GBApp).obj as Params
        _statuses = inputParam.status
        isRecentStatus = inputParam.isRecentStatus

        console(msg = "isRecentStatus: $isRecentStatus")
        binding.titleView.text = if (isRecentStatus) "Recent Status" else "Saved Status"

        binding.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = statuses.size

            override fun createFragment(position: Int): Fragment {
                return ContentViewFrag(position)
            }
        }

        binding.viewPager.currentItem = inputParam.index

        binding.backArrow.setOnClickListener {
            finish()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        _statuses = null
    }
}


class ContentViewFrag : Fragment {
    private val _tag = "DetailedStatus/ContentFragment"
    private lateinit var status: MediaEntity
    constructor(pos: Int) : super() {
        status = statuses[pos]
    }

    constructor()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val contentView = when {
            isRecentStatus && status is MediaEntity.ImageMediaEntity -> {
                ContentRecentImageStatusBinding.inflate(layoutInflater).run {
                    imageView.doOnLayout {
                        imageView.setBitmap(status) {
                            imageView.alpha = 0f
                            imageView.animate().alpha(1f)
                        }
                    }
                    btnDownload.setOnClickListener{requireContext().downloadStatus(status)}
                    btnShare.setOnClickListener{requireContext().shareStatus(status)}
                    root
                }
            }
            isRecentStatus && status is MediaEntity.VideoMediaEntity -> {
                ContentRecentVideoStatusBinding.inflate(layoutInflater).run {
                    var exoPlayer: ExoPlayer? = null
                    playerView.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
                        override fun onViewAttachedToWindow(v: View?) {
                            console(_tag, "player view attached")
                            exoPlayer = ExoPlayer.Builder(requireContext()).build()
                            with(exoPlayer!!) {
                                try{
                                    if(status.location.startsWith("content")) {
                                        setMediaItem(MediaItem.fromUri(status.location))
                                    } else {
                                        setMediaItem(MediaItem.fromUri(File(status.location).toUri()))
                                    }
                                    playWhenReady = true
                                    prepare()
                                }catch (ex: Exception){
                                    ex.printStackTrace()
                                    Toast.makeText(context, "something went wrong..", Toast.LENGTH_LONG
                                    ).show()
                                }

                                playerView.player = exoPlayer
                            }
                        }

                        override fun onViewDetachedFromWindow(v: View?) {
                            console(_tag, "playerView detached from the window")
                            exoPlayer?.release()
                        }
                    })

                    btnDownload.setOnClickListener{requireContext().downloadStatus(status)}
                    btnShare.setOnClickListener{requireContext().shareStatus(status)}
                    root
                }
            }
            !isRecentStatus && status is MediaEntity.ImageMediaEntity -> {
                ContentSavedImageStatusBinding.inflate(layoutInflater).run {
                    imageView.doOnLayout {
                        imageView.setBitmap(status) {
                            imageView.alpha = 0f
                            imageView.animate().alpha(1f)
                        }
                    }
                    btnShare.setOnClickListener {requireContext().shareStatus(status)}
                    root
                }
            }
            else -> {
                ContentSavedVideoStatusBinding.inflate(layoutInflater).run {
                    var exoPlayer: ExoPlayer? = null
                    playerView.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
                        override fun onViewAttachedToWindow(v: View?) {
                            console(_tag, "player view attached")
                            exoPlayer = ExoPlayer.Builder(requireContext()).build()
                            with(exoPlayer!!) {
                                try{
                                    if(status.location.startsWith("content")) {
                                        setMediaItem(MediaItem.fromUri(status.location))
                                    } else {
                                        setMediaItem(MediaItem.fromUri(File(status.location).toUri()))
                                    }
                                    playWhenReady = true
                                    prepare()
                                }catch (ex: Exception){
                                    ex.printStackTrace()
                                    Toast.makeText(context, "something went wrong..", Toast.LENGTH_LONG
                                    ).show()
                                }

                                playerView.player = exoPlayer
                            }
                        }

                        override fun onViewDetachedFromWindow(v: View?) {
                            console(_tag, "playerView detached from the window")
                            exoPlayer?.release()
                        }
                    })
                    btnShare.setOnClickListener{requireContext().shareStatus(status) }
                    root
                }
            }
        }
        return contentView
    }
}
