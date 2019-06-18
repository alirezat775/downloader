package alirezat775.lib.downloader.core

data class DownloaderModel(
    private val id: Int,
    private val url: String,
    private val fileName: String,
    private val status: Int,
    private val percent: Int,
    private val size: Int,
    private val totalSize: Int
)