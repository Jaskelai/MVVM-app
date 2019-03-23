package com.github.kornilovmikhail.mvvmandroidproject.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ApiKeyInterceptor private constructor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url().newBuilder().addQueryParameter("apiKey", apiKey).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

    companion object {
        private const val apiKey = "3c7ed49568f542bbb584dc2c5a236346"
        val instance: ApiKeyInterceptor by lazy {
            ApiKeyInterceptor()
        }
    }
}
