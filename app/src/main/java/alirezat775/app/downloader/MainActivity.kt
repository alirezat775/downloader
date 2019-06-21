package alirezat775.app.downloader

import alirezat775.lib.downloader.Downloader
import alirezat775.lib.downloader.core.OnDownloadListener
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    private var downloader: Downloader? = null
    private val TAG: String = this::class.java.name

    private val handler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getDownloader()

        start_download_btn.setOnClickListener {
            getDownloader()
            downloader?.download()
        }
        cancel_download_btn.setOnClickListener {
            downloader?.cancelDownload()
        }
        pause_download_btn.setOnClickListener {
            downloader?.pauseDownload()
        }
        resume_download_btn.setOnClickListener {
            getDownloader()
            downloader?.resumeDownload()
        }
    }

    private fun getDownloader() {
        downloader = Downloader.Builder(
            this,
            "https://s3-us-west-2.amazonaws.com/uw-s3-cdn/wp-content/uploads/sites/6/2017/11/04133712/waterfall.jpg"
        ).downloadListener(object : OnDownloadListener {
            override fun onStart() {
                handler.post { current_status_txt.text = "onStart" }
                Log.d(TAG, "onStart")
            }

            override fun onPause() {
                handler.post { current_status_txt.text = "onPause" }
                Log.d(TAG, "onPause")
            }

            override fun onResume() {
                handler.post { current_status_txt.text = "onResume" }
                Log.d(TAG, "onResume")
            }

            override fun onProgressUpdate(percent: Int, downloadedSize: Int, totalSize: Int) {
                handler.post {
                    current_status_txt.text = "onProgressUpdate"
                    percent_txt.text = percent.toString().plus("%")
                    size_txt.text = getSize(downloadedSize)
                    total_size_txt.text = getSize(totalSize)
                    download_progress.progress = percent
                }
                Log.d(
                    TAG,
                    "onProgressUpdate: percent --> $percent downloadedSize --> $downloadedSize totalSize --> $totalSize "
                )
            }

            override fun onCompleted(file: File?) {
                handler.post { current_status_txt.text = "onCompleted" }
                Log.d(TAG, "onCompleted: file --> $file")
            }

            override fun onFailure(reason: String?) {
                handler.post { current_status_txt.text = "onFailure: reason --> $reason" }
                Log.d(TAG, "onFailure: reason --> $reason")
            }

            override fun onCancel() {
                handler.post { current_status_txt.text = "onCancel" }
                Log.d(TAG, "onCancel")
            }
        }).build()
    }

    fun getSize(size: Int): String {
        var s = ""
        val kb = (size / 1024).toDouble()
        val mb = kb / 1024
        val gb = kb / 1024
        val tb = kb / 1024
        if (size < 1024) {
            s = "$size Bytes"
        } else if (size >= 1024 && size < 1024 * 1024) {
            s = String.format("%.2f", kb) + " KB"
        } else if (size >= 1024 * 1024 && size < 1024 * 1024 * 1024) {
            s = String.format("%.2f", mb) + " MB"
        } else if (size >= 1024 * 1024 * 1024 && size < 1024 * 1024 * 1024 * 1024) {
            s = String.format("%.2f", gb) + " GB"
        } else if (size >= 1024 * 1024 * 1024 * 1024) {
            s = String.format("%.2f", tb) + " TB"
        }
        return s
    }
}
