package ru.androidschool.intensiv

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.androidschool.intensiv.di.dataModule
import ru.androidschool.intensiv.di.domainModule
import timber.log.Timber

class MovieFinderApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        initDebugTools()
        initKoin()
    }

    private fun initDebugTools() {
        if (BuildConfig.DEBUG) {
            initTimber()
        }
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    /**
     * здесь есть небольшая проблемка: если использовать androidLogger(Level.DEBUG)
     * апка крашится => https://github.com/InsertKoinIO/koin/issues/871
     * и с androidLogger(Level.ERROR) работает нормально
     *
     * */
    private fun initKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MovieFinderApp)
            modules(dataModule, domainModule)
        }
    }

    companion object {
        lateinit var instance: MovieFinderApp
            private set
    }
}
