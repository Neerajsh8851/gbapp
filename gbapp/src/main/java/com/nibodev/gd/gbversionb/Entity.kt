package com.nibodev.gd.gbversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nibodev.domain.LoadWhatsAppMediaUseCase
import com.nibodev.domain.MediaEntity
import com.nibodev.domain.console
import kotlinx.coroutines.*


/**
 * Holds the MediaEntity collections for the ui
 *
 * you are required to pass either one of the parameter
 * to the constructor.
 */
class StatusListUIState(
    val whatsAppMediaLoader: LoadWhatsAppMediaUseCase,
    private val scope: CoroutineScope,
    private val tag : String = "MediaListUiState"
) {
    private val _videos = MutableLiveData<List<MediaEntity>>()
    private val _images = MutableLiveData<List<MediaEntity>>()

    val videos: LiveData<List<MediaEntity>> = _videos
    val images: LiveData<List<MediaEntity>> = _images
    var isLoading = MutableLiveData(false)

    init { refresh() }

    fun refresh() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }
        scope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            withContext(Dispatchers.Main) {
                console(tag, "data loading started")
                isLoading.value = true
            }
            // group media by type
            val group = whatsAppMediaLoader().groupBy { mediaEntity ->
                if (mediaEntity is MediaEntity.ImageMediaEntity) images
                else videos
            }
            withContext(Dispatchers.Main) {
                _videos.value = group[videos]
                _images.value = group[images]
                isLoading.value = false
                console(tag, "data loading complete")
            }
            console(tag = tag, msg = "loaded data: videos => ${group[videos]}, images => ${group[images]}")
        }
    }
}