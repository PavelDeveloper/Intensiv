package ru.androidschool.intensiv

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import timber.log.Timber

class MovieFinderApp : Application() {

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

    private fun isNetworkConnected(): Boolean {
        if (VERSION.SDK_INT >= VERSION_CODES.Q) {
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.getNetworkCapabilities(cm.activeNetwork)
            if (activeNetwork != null) {
                when {
                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
                }
            }
        } else {
            try {
                val cm =
                    getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork = cm.activeNetworkInfo
                return activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting
            } catch (e: Exception) {
                e.localizedMessage
            }
        }
        return false
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        var instance: MovieFinderApp? = null
            private set

        fun hasNetwork(): Boolean {
            return instance?.isNetworkConnected() ?: false
        }
    }
}
