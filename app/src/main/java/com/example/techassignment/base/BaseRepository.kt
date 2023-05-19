package com.example.techassignment.base


import android.content.Context
import com.example.techassignment.data.shared.DataManager
import com.example.techassignment.util.ConnectivityUtils
import com.example.techassignment.util.LocaleUtils
import com.example.techassignment.util.ifNull


abstract class BaseRepository(private val dataManager: DataManager, private var context: Context) {


    private val connectivityUtils: ConnectivityUtils = ConnectivityUtils(context)

    fun isNetworkConnected(): Boolean {
        return connectivityUtils.isConnected()
    }

    fun getString(i: Int): String {
        return context.getString(i)
    }


    val lang: String
        get() = dataManager.lang.ifNull(LocaleUtils.LAN_ENGLISH)



    fun clearData() {
        dataManager.clear()
    }
}