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
            "https://dl3.android30t.com/apps/Q-U/Instagram-v79.0.0.21.101(Android30t.Com).apk"
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
                    size_txt.text = downloadedSize.toString()
                    total_size_txt.text = totalSize.toString()
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
}
