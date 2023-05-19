package com.example.techassignment.base;

import android.app.Application
import android.content.res.Configuration
import com.example.techassignment.data.shared.DataManager
import com.example.techassignment.util.LocaleUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    var dataManager: DataManager? = null

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleUtils.updateConfig(this, newConfig)


    }

    companion object {
        lateinit var instance: BaseApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        dataManager = DataManager(applicationContext)
    }


}