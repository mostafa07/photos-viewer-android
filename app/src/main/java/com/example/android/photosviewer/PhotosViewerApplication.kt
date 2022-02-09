package com.example.android.photosviewer

import android.app.Application
import timber.log.Timber

class PhotosViewerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}