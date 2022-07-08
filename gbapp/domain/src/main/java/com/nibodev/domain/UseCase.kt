package com.nibodev.domain

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import androidx.documentfile.provider.DocumentFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File


abstract class ImageLoader(
    private val runOnComplete: (Bitmap?) -> Unit
) {
    operator fun invoke() {
        CoroutineScope(Dispatchers.IO).launch {
            var image: Bitmap? = null
            try {
                image = loadImage()
            } catch (ex: Exception) {
                ex.printStackTrace()
            } finally {
                withContext(Dispatchers.Main) {
                    runOnComplete(image)
                }
            }
        }
    }

    protected abstract fun loadImage(): Bitmap?

    /**
     * Loads an image from a file or a content uri, it returns immediately and returns the result in the callback
     * Loaded image will be nearly scaled down to requested size
     * if it can't load the image you will get a null as result and it also calls the print stack trace.
     */
    companion object {
        fun create(
            filepath: String,
            reqWidth: Int,
            reqHeight: Int,
            runOnComplete: (Bitmap?) -> Unit
        ): ImageLoader {
            return object : ImageLoader(runOnComplete) {
                override fun loadImage(): Bitmap? {
                    return BitmapFactory.Options().run {
                        inJustDecodeBounds = true
                        BitmapFactory.decodeFile(filepath, this)
                        inJustDecodeBounds = false
                        inSampleSize = calculateInSampleSize(this, reqWidth, reqHeight)
                        BitmapFactory.decodeFile(filepath, this)?.also {
                            console(
                                tag = "load image use case",
                                msg = "loaded image from = $filepath, scaled size = ${it.width} x ${it.height}"
                            )
                        }
                    }
                }
            }
        }

        fun create(
            content: Uri,
            contentResolver: ContentResolver,
            reqWidth: Int,
            reqHeight: Int,
            runOnComplete: (Bitmap?) -> Unit
        ): ImageLoader {
            return object : ImageLoader(runOnComplete) {
                override fun loadImage(): Bitmap? {
                    return BitmapFactory.Options().run {
                        inJustDecodeBounds = true
                        contentResolver.openFileDescriptor(content, "r")
                            .use { BitmapFactory.decodeFileDescriptor(it!!.fileDescriptor) }
                        inJustDecodeBounds = false
                        inSampleSize = calculateInSampleSize(this, reqWidth, reqHeight)
                        contentResolver.openFileDescriptor(content, "r")
                            .use { BitmapFactory.decodeFileDescriptor(it!!.fileDescriptor) }?.also {
                                console(
                                    tag = "LoadContentUseCase",
                                    msg = "loaded image from = $content, scaled size = ${it.width} x ${it.height}"
                                )
                            }
                    }
                }
            }
        }

        fun createForVideo(
            filepath: String,
            frameAtms: Long,
            reqWidth: Int,
            reqHeight: Int,
            runOnComplete: (Bitmap?) -> Unit
        ): ImageLoader {
            return object : ImageLoader(runOnComplete) {
                override fun loadImage(): Bitmap? {
                    return MediaMetadataRetriever().run {
                        setDataSource(filepath)
                        getFrameAtTime(frameAtms * 1000).also { release() }
                    }
                }
            }
        }

        fun createForVideo(
            uri: Uri,
            context: Context,
            frameAtms: Long,
            reqWidth: Int,
            reqHeight: Int,
            runOnComplete: (Bitmap?) -> Unit
        ): ImageLoader {
            return object : ImageLoader(runOnComplete) {
                override fun loadImage(): Bitmap? {
                    return MediaMetadataRetriever().run {
                        setDataSource(context, uri)
                        getFrameAtTime(frameAtms * 1000).also { release() }
                    }
                }
            }
        }

    }
}



/**
 * Use case for loading the whatsapp media content from local storage
 * using the file api, calling invoke returns a list of ImageMedia.
 */
abstract class LoadWhatsAppMediaUseCase {
    val statusDirName = ".Statuses"
    protected abstract fun exec(): List<MediaEntity>
    operator fun invoke() = exec()

    companion object {
        fun fromFilepath(pathToWhatsApp: String): LoadWhatsAppMediaUseCase {
            return object : LoadWhatsAppMediaUseCase() {
                override fun exec(): List<MediaEntity> {
                    val mediaList = mutableListOf<MediaEntity>()
                    try {
                        visitFileTree(File(pathToWhatsApp)) { file ->
                            if (file.isDirectory) {
                                if (file.name == statusDirName) return@visitFileTree 0 // visit this dir and stop
                                return@visitFileTree 2 // don't explore
                            } else {
                                if (file.parentFile?.name == statusDirName) {
                                    try {
                                        mediaList.add(createMediaObject(file))
                                    } catch (ex: IllegalArgumentException) {
                                        ex.printStackTrace()
                                    }
                                }
                            }
                            1 // don't stop
                        }
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                    return mediaList
                }
            }
        }


        fun fromDocumentFile(documentFile: DocumentFile): LoadWhatsAppMediaUseCase {
            return object : LoadWhatsAppMediaUseCase() {
                override fun exec(): List<MediaEntity> {
                    val mediaList = mutableListOf<MediaEntity>()
                    try {
                        visitDocumentTree(documentFile) { doc ->
                            if (doc.isDirectory) {
                                if (doc.name == statusDirName) return@visitDocumentTree 0 // visit this dir and stop
                                return@visitDocumentTree 2 // don't explore
                            } else {
                                if (doc.parentFile?.name == statusDirName) {
                                    try {
                                        mediaList.add(createMediaObject(doc))
                                    } catch (ex: IllegalArgumentException) {
                                        ex.printStackTrace()
                                    }
                                }
                            }
                            1 // don't stop
                        }
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                    return mediaList
                }
            }
        }

        /**
         * Use case for loading the saved whatsapp media from
         * the given dir.
         */
        fun savedMedia(dir: String) : LoadWhatsAppMediaUseCase {
            return object : LoadWhatsAppMediaUseCase() {
                override fun exec(): List<MediaEntity> {
                    val list = mutableListOf<MediaEntity>()
                    visitFileTree(File(dir)) { file ->
                        if (file.isFile) {
                            try {
                                list.add(createMediaObject(file))
                            } catch (ex: IllegalArgumentException) {
                                ex.printStackTrace()
                            }
                        }
                        1
                    }
                    return list
                }
            }
        }
    }
}


/**
 * Save file to another location
 * @param file path to a file
 * @param saveTo path to a directory
 */
suspend fun downloadStatusUseCase(
    file: String, saveTo: String, onComplete: suspend (String) -> Unit
) {
    val inputFile = File(file)
    val name = "$saveTo/${getNewName()}.${inputFile.extension}"
    val input = inputFile.inputStream()
    val output = File(name).outputStream()
    copy(input, output)
    input.close()
    output.close()
    onComplete(name)
}

suspend fun downloadStatusUseCase(
    uri: Uri,
    contentResolver: ContentResolver,
    saveTo: String,
    onComplete: suspend (String) -> Unit
) = withContext(Dispatchers.IO) {
    val ext = uri.toString().run { substring(lastIndexOf(".") + 1) }
    val name = "$saveTo/${getNewName()}.${ext}"
    val input = contentResolver.openInputStream(uri)
    val output = File(name).outputStream()
    if (input != null) {
        copy(input, output)
        onComplete(name)
    }

    try {
        input?.close()
        output.close()
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}













