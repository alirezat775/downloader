package alirezat775.lib.downloader.core

import java.io.File

/**
 * Author:  Alireza Tizfahm Fard
 * Date:    07/11/2017
 * Email:   alirezat775@gmail.com
 */

interface OnDownloadListener {
    fun onCompleted(file: File)
    fun onFailure(reason: String)
    fun progressUpdate(percent: Int, downloadedSize: Int, totalSize: Int)
    fun onCancel()
}
