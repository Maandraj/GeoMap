package com.maandraj.geomap

import android.app.Application
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContentProviderCompat.requireContext
import com.chibatching.kotpref.Kotpref
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(){
    override fun onCreate() {
        super.onCreate()
        Kotpref.init(this)
        when (baseContext.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_UNDEFINED ->   AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

    }
}