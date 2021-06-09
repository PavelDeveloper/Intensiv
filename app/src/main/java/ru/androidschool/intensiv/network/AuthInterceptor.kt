package ru.androidschool.intensiv.network

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import ru.androidschool.intensiv.BuildConfig

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url: HttpUrl = originalRequest.url.newBuilder()
            .addQueryParameter("api_key", BuildConfig.THE_MOVIE_DATABASE_API).build()
        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
