package ru.androidschool.intensiv

import android.app.Application
import ru.androidschool.intensiv.data.db.AppDatabase
import ru.androidschool.intensiv.data.movies.MoviesDao
import timber.log.Timber

class MovieFinderApp : Application() {

    lateinit var database: MoviesDao

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = AppDatabase.get(applicationContext).movieDao()
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
        var instance: MovieFinderApp? = null
            private set
    }
}
