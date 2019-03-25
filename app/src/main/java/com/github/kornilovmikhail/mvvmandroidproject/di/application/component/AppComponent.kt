package com.github.kornilovmikhail.mvvmandroidproject.di.application.component

import android.app.Application
import android.content.Context
import com.github.kornilovmikhail.mvvmandroidproject.data.network.NewsApi
import com.github.kornilovmikhail.mvvmandroidproject.di.application.module.ApplicationModule
import com.github.kornilovmikhail.mvvmandroidproject.di.application.scope.ApplicationScope
import com.github.kornilovmikhail.mvvmandroidproject.di.application.module.DataNetModule
import com.github.kornilovmikhail.mvvmandroidproject.di.application.module.PicassoModule
import com.squareup.picasso.Picasso
import dagger.Component

@ApplicationScope
@Component(modules = [ApplicationModule::class, PicassoModule::class, DataNetModule::class])
interface AppComponent {
    fun provideApp(): Application
    fun provideContext(): Context
    fun providePicasso(): Picasso
    fun provideNewApi(): NewsApi
}
