package com.github.kornilovmikhail.mvvmandroidproject.di.screens.module

import com.github.kornilovmikhail.mvvmandroidproject.data.network.NewsApi
import com.github.kornilovmikhail.mvvmandroidproject.data.network.interceptor.ApiKeyInterceptor
import com.github.kornilovmikhail.mvvmandroidproject.data.repository.NewsNetworkRepository
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.scope.NewsScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataNetModule {
    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"
    }

    @Provides
    @NewsScope
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(rxJava2CallAdapterFactory)
        .build()

    @Provides
    @NewsScope
    fun provideNewsAPI(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)

    @Provides
    @NewsScope
    fun provideNewsNetworkRepository(newsApi: NewsApi): NewsNetworkRepository = NewsNetworkRepository(newsApi)

    @Provides
    @NewsScope
    fun provideOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(apiKeyInterceptor).build()

    @Provides
    @NewsScope
    fun provideApiKeyInterceptor(): ApiKeyInterceptor = ApiKeyInterceptor.instance

    @Provides
    @NewsScope
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @NewsScope
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()
}
