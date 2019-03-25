package com.github.kornilovmikhail.mvvmandroidproject.di.screens.module

import android.app.Application
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.scope.NewsScope
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import okhttp3.OkHttpClient
import dagger.Provides

@Module
class PicassoModule {
    @Provides
    @NewsScope
    fun providePicasso(app: Application, okHttp3Downloader: OkHttp3Downloader): Picasso {
        return Picasso.Builder(app).downloader(okHttp3Downloader).build()
    }

    @Provides
    @NewsScope
    fun okHttp3Downloader(okHttpClient: OkHttpClient): OkHttp3Downloader {
        return OkHttp3Downloader(okHttpClient)
    }
}
