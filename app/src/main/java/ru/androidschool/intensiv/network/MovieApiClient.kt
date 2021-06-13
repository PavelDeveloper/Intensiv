package ru.androidschool.intensiv.network

import android.os.Build.VERSION.RELEASE
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.androidschool.intensiv.BuildConfig
import ru.androidschool.intensiv.MovieFinderApp
import java.io.File
import java.util.concurrent.TimeUnit

object MovieApiClient {
    private const val cacheSize: Long = 5 * 1024 * 1024
    private const val HEADER_CACHE_CONTROL = "Cache-Control"
    private const val HEADER_PRAGMA = "Pragma"

    private val cache by lazy {
        Cache(File(MovieFinderApp.instance?.cacheDir, "identifier"), cacheSize)
    }

    private val client: Retrofit
        get() {
            val client = if (BuildConfig.BUILD_TYPE != RELEASE) {
                val interceptor = HttpLoggingInterceptor(CustomHttpLogging())
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                clientBuilder.addInterceptor(interceptor)
                    .build()
            } else
                clientBuilder.build()

            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

    private val clientBuilder: OkHttpClient.Builder
        get() =
            OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor())
                .cache(cache)
                .addNetworkInterceptor(networkInterceptor())
                .addInterceptor(offlineInterceptor())

    val api: MovieApiInterface
        get() {
            return client.create(MovieApiInterface::class.java)
        }

    private fun networkInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(5, TimeUnit.SECONDS)
                .build()

            response.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()
        }
    }

    private fun offlineInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            val cacheControl = CacheControl.Builder()
                .maxAge(7, TimeUnit.DAYS)
                .build()

            request = request.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .cacheControl(cacheControl)
                .build()
            chain.proceed(request)
        }
    }
}
