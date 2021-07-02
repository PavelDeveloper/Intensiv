package ru.androidschool.intensiv

import android.app.Application
import ru.androidschool.intensiv.data.db.AppDatabase
import timber.log.Timber

class MovieFinderApp : Application() {

    val database: AppDatabase by lazy { AppDatabase.get(applicationContext) }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initDebugTools()
    }

    private fun initDebugTools() {
        if (BuildConfig.DEBUG) {
            initTimber()
        }
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var instance: MovieFinderApp
            private set
    }
}
