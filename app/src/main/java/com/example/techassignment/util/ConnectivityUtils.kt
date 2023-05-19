package com.example.techassignment.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


class ConnectivityUtils constructor(private val context: Context) {



    private fun getNetworkInfo(context: Context): NetworkInfo? {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo
    }



    fun isConnected(): Boolean {
        val info = getNetworkInfo(context)

        return info != null && info.isConnected
    }






}
