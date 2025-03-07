package com.ltr.tackle

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TackleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}