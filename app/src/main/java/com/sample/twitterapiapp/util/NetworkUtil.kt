package com.sample.twitterapiapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object NetworkUtil {

    fun isNetworkAvailable(context: Context?): Boolean {
        return if (context == null) {
            false
        } else {
            isConnected(context)
        }
    }

    private fun isConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm.activeNetworkInfo
        return (info != null && info.isConnected)
    }

}