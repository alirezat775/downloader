package alirezat775.lib.downloader

import alirezat775.lib.downloader.core.OnDownloadListener
import alirezat775.lib.downloader.core.database.DownloaderDao
import android.Manifest
import android.content.Context
import android.os.AsyncTask
import android.util.Pair
import androidx.annotation.CheckResult
import androidx.annotation.RequiresPermission
import java.io.File
import java.lang.ref.SoftReference
import java.net.HttpURLConnection
import java.net.MalformedURLException

/**
 * Author:  Alireza Tizfahm Fard
 * Date:    11/6/2017
 * Email:   alirezat775@gmail.com
 */

class Downloader private constructor(downloadTask: DownloadTask) {

    private var downloadTask: DownloadTask? = null

    init {
        if (this.downloadTask == null) {
            this.downloadTask = downloadTask
        }
    }

    @RequiresPermission(Manifest.permission.INTERNET)
    fun download() {
        if (downloadTask == null) {
            throw IllegalStateException("rebuild new instance after \"pause or cancel\" download")
        }
        downloadTask!!.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    fun cancelDownload() {
        if (downloadTask != null) {
            downloadTask!!.cancel()
            downloadTask = null
        }
    }

    fun pauseDownload() {
        if (downloadTask != null) {
            downloadTask!!.pause()
            downloadTask = null
        }
    }

    fun resumeDownload() {
        if (downloadTask != null) downloadTask!!.resume = true
        download()
    }

    private class DownloadTask : AsyncTask<Void, Void, Pair<Boolean, Exception>>() {
        var context: SoftReference<Context>? = null
        internal var resume = false
        internal var dao: DownloaderDao? = null
        internal var downloadDir: String? = null
        internal var fileName: String? = null
        internal var extension: String? = null
        internal var stringURL: String? = null
        internal var timeOut: Int = 0
        internal var downloadListener: OnDownloadListener? = null
        internal var header: Map<String, String>? = null
        private var connection: HttpURLConnection? = null
        private var downloadedFile: File? = null
        private var downloadedSize: Int? = 0
        private var percent: Int? = 0
        private var totalSize: Int? = 0

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg voids: Void): Pair<Boolean, Exception> {
            return Pair<Boolean, Exception>(true, null)
        }

        override fun onPostExecute(result: Pair<Boolean, Exception>) {
            super.onPostExecute(result)
        }

        override fun onCancelled() {
            super.onCancelled()
            if (connection != null) connection!!.disconnect()
        }

        override fun onCancelled(booleanExceptionPair: Pair<Boolean, Exception>) {
            super.onCancelled(booleanExceptionPair)
            if (connection != null) connection!!.disconnect()
        }

        internal fun cancel() {
        }

        internal fun pause() {
            cancel(true)
        }
    }

    class Builder
    /**
     * @param context the best practice is passing application context
     * @param url     passing url of the download file
     */
        (private val mContext: Context, private val mUrl: String) {
        private var mTimeOut: Int = 0
        private var mDownloadDir: String? = null
        private var mFileName: String? = null
        private var mExtension: String? = null
        private var mDownloadListener: OnDownloadListener? = null
        private var mHeader: Map<String, String>? = null

        /**
         * @param downloadDir for setting custom download directory (default value is sandbox/download/ directory)
         * @return builder
         */
        @CheckResult
        fun setDownloadDir(downloadDir: String): Builder {
            this.mDownloadDir = downloadDir
            return this
        }


        /**
         * @param downloadListener an event listener for tracking download events
         * @return builder
         */
        @CheckResult
        fun setDownloadListener(downloadListener: OnDownloadListener): Builder {
            this.mDownloadListener = downloadListener
            return this
        }

        /**
         * @param fileName  for saving with this name
         * @param extension extension of the file
         * @return builder
         */
        @CheckResult
        fun setFileName(fileName: String, extension: String): Builder {
            this.mFileName = fileName
            this.mExtension = extension
            return this
        }

        /**
         * @param header for adding headers in http request
         * @return builder
         */
        @CheckResult
        fun setHeader(header: Map<String, String>): Builder {
            this.mHeader = header
            return this
        }

        /**
         * @param timeOut is a parameter for setting connection time out.
         * @return Builder
         */
        @CheckResult
        fun setTimeOut(timeOut: Int): Builder {
            this.mTimeOut = timeOut
            return this
        }

        fun build(): Downloader {
            val downloadTask = DownloadTask()
            downloadTask.dao = DownloaderDao.getInstance(mContext)
            downloadTask.timeOut = mTimeOut
            downloadTask.downloadListener = mDownloadListener
            downloadTask.fileName = mFileName
            downloadTask.header = mHeader
            downloadTask.extension = mExtension
            downloadTask.context = SoftReference(mContext)
            if (mDownloadDir == null || mDownloadDir!!.isEmpty()) {
                downloadTask.downloadDir = mContext.getExternalFilesDir(null).toString()
            } else
                downloadTask.downloadDir = mDownloadDir

            if (mUrl.isEmpty()) {
                throw MalformedURLException("The entered URL is not valid")
            } else {
                downloadTask.stringURL = mUrl
            }
            return Downloader(downloadTask)
        }
    }
}
