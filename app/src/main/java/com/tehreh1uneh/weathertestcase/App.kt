package com.tehreh1uneh.weathertestcase

import android.app.Application
import io.paperdb.Paper
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * @see <a href="https://github.com/pilgr/Paper">Paper</a>
 * @see <a href="https://github.com/JakeWharton/timber">Timber</a>
 */
class App : Application() {

    override fun onCreate() {

        super.onCreate()

        /* "Paper" library initialization */
        Paper.init(this)

        /* Configures "Timber" library for different build variants */
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            // TODO: release tree not yet implemented
        }

        Timber.d("Application created")
    }
}
