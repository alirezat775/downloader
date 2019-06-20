package alirezat775.app.downloader

import alirezat775.lib.downloader.Downloader
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var downloader = Downloader.Builder(
            this,
            "https://dl.freemp3downloads.online/file/youtubeYQHsXMglC9A320.mp3?fn=Adele%20-%20Hello.mp3"
        ).build()

        for (i in 1..10) {
            downloader.download()
            if (i == 2 || i == 6) {
                downloader.pauseDownload()
                downloader.resumeDownload()
            }
        }
    }
}
