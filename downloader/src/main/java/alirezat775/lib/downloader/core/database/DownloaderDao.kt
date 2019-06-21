package alirezat775.lib.downloader.core.database

import alirezat775.lib.downloader.core.model.ColumnModel
import alirezat775.lib.downloader.core.model.FileModel
import android.content.Context

/**
 * Author:  Alireza Tizfahm Fard
 * Date:    21/6/2019
 * Email:   alirezat775@gmail.com
 */

internal class DownloaderDao private constructor(context: Context) {

    private var database: DownloaderDatabase = DownloaderDatabase.getInstance(context)

    companion object {
        @Volatile
        private var instance: DownloaderDao? = null

        fun getInstance(context: Context): DownloaderDao {
            return instance ?: synchronized(this) {
                DownloaderDao(context).also { instance = it }
            }
        }
    }

    fun insertNewDownload(fileModel: FileModel) {
        val query = ("insert into " + DownloaderDatabase.TABLE_NAME + " " +
                "(" +
                ColumnModel.URL + "," +
                ColumnModel.FILE_NAME + "," +
                ColumnModel.STATUS + "," +
                ColumnModel.PERCENT + "," +
                ColumnModel.SIZE + "," +
                ColumnModel.TOTAL_SIZE
                + ")" +
                " VALUES(" + "\"" + fileModel.url + "\"" + ","
                + " " + "\"" + fileModel.fileName + "\"" + ","
                + " " + fileModel.status + ","
                + " " + fileModel.percent + ","
                + " " + fileModel.size + ","
                + " " + fileModel.totalSize + ")")
        database.writableDatabase.execSQL(query)
        database.close()
    }

    fun updateDownload(url: String, status: Int, percent: Int, size: Int, totalSize: Int) {
        val query = "update " + DownloaderDatabase.TABLE_NAME + " " +
                " set " + ColumnModel.STATUS + " = " + status +
                " , " + ColumnModel.PERCENT + " = " + percent +
                " , " + ColumnModel.SIZE + " = " + size +
                " , " + ColumnModel.TOTAL_SIZE + " = " + totalSize +
                " where " + ColumnModel.URL + " = " + "\"" + url + "\""
        database.writableDatabase.execSQL(query)
        database.close()
    }

    fun getDownload(url: String): FileModel? {
        var model: FileModel? = null
        val query =
            "select * from " + DownloaderDatabase.TABLE_NAME + " where " + ColumnModel.URL + " = \"" + url + "\""
        val cursor = database.readableDatabase.rawQuery(query, null)
        while (cursor.moveToNext()) {
            val itemId = cursor.getInt(cursor.getColumnIndex(ColumnModel.ID))
            val itemUrl = cursor.getString(cursor.getColumnIndex(ColumnModel.URL))
            val itemFileName = cursor.getString(cursor.getColumnIndex(ColumnModel.FILE_NAME))
            val itemStatus = cursor.getInt(cursor.getColumnIndex(ColumnModel.STATUS))
            val itemPercent = cursor.getInt(cursor.getColumnIndex(ColumnModel.PERCENT))
            val itemSize = cursor.getInt(cursor.getColumnIndex(ColumnModel.SIZE))
            val itemTotalSize = cursor.getInt(cursor.getColumnIndex(ColumnModel.TOTAL_SIZE))
            model = FileModel(itemId, itemUrl, itemFileName, itemStatus, itemPercent, itemSize, itemTotalSize)
        }
        cursor.close()
        database.close()
        return model
    }
}