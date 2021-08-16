package com.klemer.photoexplore.helpers


import androidx.core.content.ContextCompat.getSystemService

import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri

import android.os.Environment
import java.util.*


class Downloader {
    fun download(path: String, context: Context){
        val r = DownloadManager.Request(Uri.parse(path))
        val filename = Date().time
        r.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "${filename}+.jpg")
        r.allowScanningByMediaScanner()

        r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        val dm = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        dm.enqueue(r)
    }
}