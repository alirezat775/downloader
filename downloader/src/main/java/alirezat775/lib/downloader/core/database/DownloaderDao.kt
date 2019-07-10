package alirezat775.lib.downloader.core.database

import alirezat775.lib.downloader.core.model.DownloaderData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
internal interface DownloaderDao {

    @Query("SELECT * FROM DownloaderData WHERE url IS :url")
    fun getDownloadByUrl(url: String): DownloaderData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewDownload(vararg item: DownloaderData)

    @Query("UPDATE DownloaderData SET status= :success, percent=:percent, size=:downloadedSize, totalSize=:totalSize WHERE url IS :url")
    fun updateDownload(url: String, success: Int, percent: Int, downloadedSize: Int, totalSize: Int)
}