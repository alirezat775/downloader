package alirezat775.lib.downloader.core.database

import alirezat775.lib.downloader.core.model.DownloaderData
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Author:  Alireza Tizfahm Fard
 * Date:    21/6/2019
 * Email:   alirezat775@gmail.com
 */
@Database(entities = [DownloaderData::class], version = 1, exportSchema = false)
internal abstract class DownloaderDatabase : RoomDatabase() {

    abstract fun downloaderDao(): DownloaderDao

    companion object {

        private var INSTANCE: DownloaderDatabase? = null

        fun getAppDatabase(context: Context): DownloaderDatabase =
            INSTANCE?.let { it }
                ?: Room.databaseBuilder(
                    context.applicationContext,
                    DownloaderDatabase::class.java,
                    "downloader_db"
                ).allowMainThreadQueries().build().apply { INSTANCE = this }
    }
}
