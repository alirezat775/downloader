package alirezat775.lib.downloader.core.database

import alirezat775.lib.downloader.core.model.ColumnModel
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Author:  Alireza Tizfahm Fard
 * Date:    21/6/2019
 * Email:   alirezat775@gmail.com
 */

internal class DownloaderDatabase private constructor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        @Volatile
        private var instance: DownloaderDatabase? = null

        internal const val DATABASE_NAME = "downloader_db"
        internal const val TABLE_NAME = "downloader"
        internal const val DATABASE_VERSION = 1

        fun getInstance(context: Context): DownloaderDatabase {
            return instance ?: synchronized(this) {
                DownloaderDatabase(context).also { instance = it }
            }
        }
    }

    private val CREATE_TABLE = (
            "CREATE TABLE " + TABLE_NAME + "("
                    + ColumnModel.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + ColumnModel.URL + " TEXT,"
                    + ColumnModel.FILE_NAME + " TEXT,"
                    + ColumnModel.STATUS + " INTEGER,"
                    + ColumnModel.PERCENT + " INTEGER,"
                    + ColumnModel.SIZE + " INTEGER,"
                    + ColumnModel.TOTAL_SIZE + " INTEGER"
                    + ")")

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {}

}