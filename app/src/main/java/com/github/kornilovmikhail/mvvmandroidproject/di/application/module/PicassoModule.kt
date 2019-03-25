package com.github.kornilovmikhail.mvvmandroidproject.di.application.module

import android.content.Context
import com.github.kornilovmikhail.mvvmandroidproject.di.application.scope.ApplicationScope
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module(includes = [ApplicationModule::class, DataNetModule::class])
class PicassoModule {

    @Provides
    @ApplicationScope
    fun provideOkHttp3Downloader(okHttpClient: OkHttpClient): OkHttp3Downloader =
         OkHttp3Downloader(okHttpClient)

    @Provides
    @ApplicationScope
    fun providePicasso(context: Context, okHttp3Downloader: OkHttp3Downloader): Picasso =
         Picasso.Builder(context).downloader(okHttp3Downloader).build()
}
