package com.nibodev.domain

import android.graphics.BitmapFactory
import android.util.Log
import androidx.documentfile.provider.DocumentFile
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*

const val TAG = "domain module"
/**
 * call [Log.d] if the app is in debug mode
 */
fun console(tag: String = "", msg: String) {
    if (BuildConfig.DEBUG) {
        Log.d("console/$tag", msg)
    }
}


/**
 * Calculates in sample size for to use with BitmapFactory options
 * see [BitmapFactory.Options.inSampleSize]
 *
 * @param reqWidth the minimum width, you want to scale down your image.
 * @param reqHeight the minimum height, you want to scale down your image.
 */
fun calculateInSampleSize(
    options: BitmapFactory.Options,
    reqWidth: Int,
    reqHeight: Int
): Int {
    val (height: Int, width: Int) = options.run { outHeight to outWidth }
    var returnedSample = 1
    var inSample = 2
    while (height / inSample >= reqHeight || width / inSample >= reqWidth) {
        returnedSample = inSample
        inSample *= 2
    }
    console(tag = "calculateInSampleSize", msg = "returned inSampleSize = $returnedSample for actual size = $width x $height and requested size = $reqWidth x $reqHeight")
    return returnedSample
}


fun getNewName() : String {
    val date = Date()
    val formatter = SimpleDateFormat("dd-MM-yyyy hh.mm.ss", Locale.getDefault())
    return formatter.format(date)
}


fun copy(input: InputStream, output: OutputStream) {
    val buffer = ByteArray(1024)
    var readBytes = input.read(buffer)
    while (readBytes > 0) {
        output.write(buffer, 0, readBytes)
        readBytes = input.read(buffer)
    }
}




fun visitDocumentTree(root: DocumentFile, visitor: (DocumentFile)->Int) {
    val children = root.listFiles()
    for (child in children) {
        if(child.isFile) {
            // question: should stop after visit this file
            if (visitor(child) == 0) break
        } else {
            // question: should explore this directory
            val ans = visitor(child)
            if (ans == 0) {
            // go in and break
                visitDocumentTree(child, visitor)
                break
            } else if (ans == 1) {
                // go in and don't break
                visitDocumentTree(child, visitor)
            } else {
                // skip
                console(TAG, "skipping dir ${child.name}")
            }
        }
    }
}



fun visitFileTree(root : File, visitor: (File) -> Int) {
    val children = root.listFiles()
    if (children != null)
    for (child in children) {
        if(child.isFile) {
            // question: should stop after visit this file
            if (visitor(child) == 0) break
        } else {
            // question: should explore this directory
            val ans = visitor(child)
            if (ans == 0) {
                // go in and break
                visitFileTree(child, visitor)
                break
            } else if (ans == 1) {
                // go in and don't break
                visitFileTree(child, visitor)
            } else {
                // skip
                console(TAG, "skipping dir ${child.name}")
            }
        }
    }
}


/**
 * converts the media represented by this file to Media object
 */
 internal fun createMediaObject(mediaFile: DocumentFile): MediaEntity {
    return when (mediaFile.type) {
        "image/png", "image/jpeg" -> {
            MediaEntity.ImageMediaEntity(mediaFile.uri.toString())
        }
        "video/mp4" -> {
            MediaEntity.VideoMediaEntity(mediaFile.uri.toString())
        }
        else -> throw IllegalArgumentException("$mediaFile is not a media so couldn't convert it to Media object")
    }
}

/**
 * converts the media represented by this file to MediaEntity object
 */
 internal fun createMediaObject(mediaFile: File): MediaEntity {
    return when (mediaFile.extension) {
        "png", "jpg", "jpeg" -> {
            MediaEntity.ImageMediaEntity(mediaFile.absolutePath)
        }
        "mp4" -> {
            MediaEntity.VideoMediaEntity(mediaFile.absolutePath)
        }
        else -> throw IllegalArgumentException("$mediaFile is not a media so couldn't convert it to Media object")
    }
}