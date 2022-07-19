package com.nibodev.gd.gbversion

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.storage.StorageManager
import android.provider.DocumentsContract
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import com.facebook.FacebookSdk.getApplicationName
import com.nibodev.androidutil.AndroidUtility
import com.nibodev.domain.*
import kotlinx.coroutines.*
import java.io.File

/**
 * Checks whether we can read from the external shared storage.
 */
fun Context.hasReadExternalStoragePerm(): Boolean =
    ActivityCompat.checkSelfPermission(
        this,
        Manifest.permission.READ_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
        this,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED


fun Context.getWaUriTree(): Uri? {
    val uri = getSharedPreferences(settingsPref, Context.MODE_PRIVATE).getString(waUriKey, null)
    return if (uri != null) {
        Uri.parse(uri)
    }else {
        null
    }
}

fun Context.preserveUri(uri: Uri) {
    getSharedPreferences(settingsPref, Context.MODE_PRIVATE).edit().putString(waUriKey, uri.toString())
        .apply()
    contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
}

fun Context.hasUriPerm(uri: Uri?): Boolean {
    var result = false;
    if(uri != null) {
        contentResolver.persistedUriPermissions.forEach { uriPermission ->
            if (uriPermission.uri == uri) {
                result = true
            }
        }
    }
    return result
}



/**
 * Creates an intent to open an uri tree
 * @param initialPath : the path which should be open in document provider ui
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun buildOpenUriTreeIntent(context: Context, initialPath: String) : Intent{
    val sm = context.getSystemService(ComponentActivity.STORAGE_SERVICE) as StorageManager
    val intent = sm.primaryStorageVolume.createOpenDocumentTreeIntent()
    var uri = intent.getParcelableExtra<Uri>(DocumentsContract.EXTRA_INITIAL_URI)
    var scheme = uri.toString()
    scheme = scheme.replace("/root/", "/document/")
    scheme += "%3A" + initialPath.replace("/", "%2f")
    uri = Uri.parse(scheme)
    intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, uri)
    return intent
}


/**
 * this fun load and sets the image to this image view.
 * @param onComplete get invoked, after loading
 */
fun  ImageView.setBitmap(media: MediaEntity, onComplete: ()->Unit) {
    val runOnComplete : (Bitmap?) -> Unit = {
        if (it != null) {
            setImageBitmap(it)
            onComplete()
        } else {
            console(msg = "loaded a null image")
        }
    }

    fun loadImage(path: String) {
        if (path.startsWith("content")) {
            val uri = Uri.parse(path)
            ImageLoader.create(uri, context.contentResolver, width, height, runOnComplete)
                .invoke()
        } else {
            ImageLoader.create(path, width, height, runOnComplete)
                .invoke()
        }
    }

    fun loadVideoFrame(path: String) {
        if (path.contains("content")) {
            val uri = Uri.parse(path)
            ImageLoader.createForVideo(uri, context, 1000, width, height, runOnComplete).invoke()
        } else {
            ImageLoader.createForVideo(path, 1000, width, height, runOnComplete).invoke()
        }
    }

    when (media) {
        is MediaEntity.VideoMediaEntity -> {
            loadVideoFrame(media.location)
        }

        is MediaEntity.ImageMediaEntity -> {
            loadImage(media.location)
        }
    }
}


fun Context.downloadStatus(status: MediaEntity) {
    val saveFile = File(SAVED_WA_STATUS_PATH)
    if (saveFile.exists().not()) {
        console(msg = "saved dir does not exits so creating a new")
        saveFile.mkdir()
    }
    val context = this

    val cExHandler = CoroutineExceptionHandler{ _, ex ->
        ex.printStackTrace()
    }
    if (status.location.startsWith("content")) {
        val uri = Uri.parse(status.location)
        val contentRes = contentResolver

        CoroutineScope(Dispatchers.IO + cExHandler).launch {
            downloadStatusUseCase(uri, contentRes, SAVED_WA_STATUS_PATH) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Downloaded to: $it", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    } else {
        CoroutineScope(Dispatchers.IO + cExHandler).launch {
            downloadStatusUseCase(status.location, SAVED_WA_STATUS_PATH) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Downloaded to: $it", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}


fun Context.shareStatus(status: MediaEntity){
    // Todo: make this function to work properly
    val intent = Intent()
    intent.action = Intent.ACTION_SEND
    intent.data = if (status.location.startsWith("content")) Uri.parse(status.location) else File(status.location).toUri()
    intent.type = if (status is MediaEntity.VideoMediaEntity) "video/*" else "image/*"
    console(msg = "type: ${intent.type}, data: ${intent.data}")
    startActivity(Intent.createChooser(intent, "share"))
}


/**
 * Queries for network availability
 * @return true if network is available otherwise false
 */
fun isNetworkConnected(context: Context): Boolean {
    var connected = false
    if (context is Activity) {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        if (netInfo != null) connected = netInfo.isAvailable && netInfo.isConnectedOrConnecting
    }
    return connected
}

