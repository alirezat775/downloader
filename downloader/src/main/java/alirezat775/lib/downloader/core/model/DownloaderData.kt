package alirezat775.lib.downloader.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Author:  Alireza Tizfahm Fard
 * Date:    21/6/2019
 * Email:   alirezat775@gmail.com
 */

@Entity
internal data class DownloaderData(
    @PrimaryKey val id: Int,
    val url: String?,
    val filename: String?,
    val status: Int,
    val percent: Int,
    val size: Int,
    val totalSize: Int
)