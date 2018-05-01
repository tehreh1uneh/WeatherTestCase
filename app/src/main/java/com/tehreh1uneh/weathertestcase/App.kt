package com.tehreh1uneh.weathertestcase

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.tehreh1uneh.weathertestcase.di.ApplicationComponent
import com.tehreh1uneh.weathertestcase.di.DaggerApplicationComponent
import io.paperdb.Paper
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * @see <a href="https://github.com/pilgr/Paper">Paper</a>
 * @see <a href="https://github.com/JakeWharton/timber">Timber</a>
 */
class App : Application() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
            private set
    }

    override fun onCreate() {

        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            /* This process is dedicated to LeakCanary for heap analysis.
             You should not init your app in this process */
            return
        }

        /* "LeakCanary" library initialization */
        LeakCanary.install(this)

        /* "Paper" library initialization */
        Paper.init(this)

        /* Configures "Timber" library for different build variants */
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            // TODO: release tree not yet implemented
        }

        /* Dagger2 Application scope component initialization */
        applicationComponent = DaggerApplicationComponent.create()

        Timber.d("Application created")
    }
}
