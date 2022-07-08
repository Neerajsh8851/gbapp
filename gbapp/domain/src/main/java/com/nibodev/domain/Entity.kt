package com.nibodev.domain


sealed class MediaEntity(val location: String, val isRecentStatus: Boolean) {
    /**
     * Holds the location of a image file or image content
     */
    class ImageMediaEntity(location: String, isRecentStatus: Boolean = false) :
        MediaEntity(location, isRecentStatus)

    /**
     * Holds the location of a video file or video content
     */
    class VideoMediaEntity(location: String, isRecentStatus: Boolean = false) :
        MediaEntity(location, isRecentStatus)

    override fun toString(): String = location
}