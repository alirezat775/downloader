package alirezat775.lib.downloader.helper

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.RequiresPermission

/**
 * Author:  Alireza Tizfahm Fard
 * Date:    17/09/2017
 * Email:   alirezat775@gmail.com
 */
object ConnectCheckerHelper {

    private const val TYPE_WIFI = 1
    private const val TYPE_MOBILE = 2

    /**
     * @param context
     * @return boolean
     */
    @RequiresPermission(allOf = [Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.INTERNET])
    fun isInternetAvailable(context: Context): Boolean {
        val isConnected: Boolean
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
        return isConnected
    }


    /**
     * @param context
     * @return int
     */
    @RequiresPermission(allOf = [Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.INTERNET])
    fun connectionType(context: Context): Int {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        if (null != activeNetwork) {
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI
            if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE
        }
        return 0
    }

    /**
     * @param connectionType
     * @return string
     */
    fun connectionTypeChecker(connectionType: Int): String? {
        var type: String? = null
        when (connectionType) {
            TYPE_WIFI -> type = "TYPE_WIFI"
            TYPE_MOBILE -> type = "TYPE_MOBILE"
        }
        return type
    }

}
