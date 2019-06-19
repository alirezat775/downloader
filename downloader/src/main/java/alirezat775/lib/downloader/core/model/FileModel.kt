package alirezat775.lib.downloader.core.model

data class FileModel(
    val id: Int,
    val url: String?,
    val fileName: String?,
    val status: Int,
    val percent: Int,
    val size: Int,
    val totalSize: Int
)