package com.github.kornilovmikhail.mvvmandroidproject

import android.app.Application
import com.github.kornilovmikhail.mvvmandroidproject.di.application.component.AppComponent
import com.github.kornilovmikhail.mvvmandroidproject.di.application.module.ApplicationModule
import com.github.kornilovmikhail.mvvmandroidproject.di.application.component.DaggerAppComponent
import com.github.kornilovmikhail.mvvmandroidproject.di.application.module.DataDBModule
import com.github.kornilovmikhail.mvvmandroidproject.di.application.module.DataNetModule
import com.github.kornilovmikhail.mvvmandroidproject.di.application.module.PicassoModule
import com.squareup.picasso.Picasso

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .dataNetModule(DataNetModule())
            .picassoModule(PicassoModule())
            .dataDBModule(DataDBModule())
            .build()
        appComponent?.let {
            Picasso.setSingletonInstance(it.providePicasso())
        }
    }

    companion object {
        private var appComponent: AppComponent? = null

        fun getAppComponents(): AppComponent? = appComponent
    }
}
