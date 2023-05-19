package com.example.techassignment.data.shared

import android.content.Context
import android.content.SharedPreferences
import com.example.techassignment.util.LocaleUtils

class DataManager(context: Context?) {

    var mSharedPreferences: SharedPreferences? = null


    init {
        mSharedPreferences = context?.getSharedPreferences("RubikansPreferences", 0)
    }


    fun clear() {
        mSharedPreferences!!.edit().clear().apply()
    }


    ////////////////////////////////////////////////////////////////////////////////

    fun saveLang(lang: String?) {
        mSharedPreferences!!.edit().putString(LANG, lang).apply()

    }

    val lang: String?
        get() = mSharedPreferences!!.getString(LANG, LocaleUtils.LAN_ENGLISH)

    ////////////////////////////////////////////////////////////////////////////////


    companion object {
        const val LANG = "LANG"

    }

}