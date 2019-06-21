package alirezat775.lib.downloader.core.model

/**
 * Author:  Alireza Tizfahm Fard
 * Date:    21/6/2019
 * Email:   alirezat775@gmail.com
 */

internal data class FileModel(
    val id: Int,
    val url: String?,
    val fileName: String?,
    val status: Int,
    val percent: Int,
    val size: Int,
    val totalSize: Int
)