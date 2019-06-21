package alirezat775.lib.downloader.core

import java.io.File

/**
 * Author:  Alireza Tizfahm Fard
 * Date:    21/6/2019
 * Email:   alirezat775@gmail.com
 */

interface OnDownloadListener {
    fun onStart()
    fun onPause()
    fun onResume()
    fun onProgressUpdate(percent: Int, downloadedSize: Int, totalSize: Int)
    fun onCompleted(file: File?)
    fun onFailure(reason: String?)
    fun onCancel()
}
