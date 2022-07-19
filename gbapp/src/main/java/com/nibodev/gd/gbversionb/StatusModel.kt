package com.nibodev.gd.gbversion

import android.os.Build
import androidx.documentfile.provider.DocumentFile
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nibodev.domain.LoadWhatsAppMediaUseCase
import com.nibodev.domain.console

class StatusModel : ViewModel() {
    private val TAG = "StatusModel"
    // only for api level < 29
    private lateinit var waStatusDir :String //  Environment.getExternalStorageDirectory().absolutePath + "/" + "WhatsApp/Media/.Statuses"
    private lateinit var  savedStatusDir: String// Environment.getExternalStorageDirectory().absolutePath + "/${Environment.DIRECTORY_DCIM}/StatusSaver"
    private lateinit var waDocumentFile : DocumentFile

    val newStatus: StatusListUIState by lazy { getNewStatusUiState()}
    val savedStatus: StatusListUIState by lazy { getSavedStatusUiState() }

    fun initWith(documentFile: DocumentFile, savedStatusDir: String) {
        waDocumentFile = documentFile
        this.savedStatusDir = savedStatusDir
        console(TAG, "initialize: with documentFile: ${documentFile.uri}")
    }

    fun initWith(waStatusDir: String, savedStatusDir: String) {
        this.waStatusDir = waStatusDir
        this.savedStatusDir = savedStatusDir
    }


    private fun getNewStatusUiState(): StatusListUIState {
        val loader =  if(Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            LoadWhatsAppMediaUseCase.fromDocumentFile(waDocumentFile)
        } else {
            LoadWhatsAppMediaUseCase.fromFilepath(waStatusDir)
        }
        return StatusListUIState(loader, viewModelScope)
    }


    private fun getSavedStatusUiState(): StatusListUIState {
        val loader = LoadWhatsAppMediaUseCase.savedMedia(savedStatusDir)
        return StatusListUIState(loader, viewModelScope)
    }
}
