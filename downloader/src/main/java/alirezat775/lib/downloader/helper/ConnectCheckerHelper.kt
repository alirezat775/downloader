package alirezat775.lib.downloader.helper

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import android.net.NetworkInfo
import androidx.core.content.ContextCompat.getSystemService




/**
 * Author:  Alireza Tizfahm Fard
 * Date:    21/6/2019
 * Email:   alirezat775@gmail.com
 */

internal object ConnectCheckerHelper {

    /**
     * @param context
     * @return boolean
     */
    @RequiresPermission(allOf = [Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.INTERNET])
    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
