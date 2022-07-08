package com.nibodev.gd.gbversion

import android.os.Environment

val waStatusDir = ""

val OPEN_URI_INITIAL_PATH = "Android/media/com.whatsapp/WhatsApp/Media"
val SAVED_WA_STATUS_PATH get() = Environment.getExternalStorageDirectory().absolutePath + "/" + Environment.DIRECTORY_DCIM + "/GbVersion"

val settingsPref = "settings"
val waUriKey = "waUri"