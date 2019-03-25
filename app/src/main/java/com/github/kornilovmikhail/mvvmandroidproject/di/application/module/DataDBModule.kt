package com.github.kornilovmikhail.mvvmandroidproject.di.application.module

import android.app.Application
import androidx.room.Room
import com.github.kornilovmikhail.mvvmandroidproject.data.local.AbstractNewsDatabase
import com.github.kornilovmikhail.mvvmandroidproject.data.local.dao.NewsDao
import com.github.kornilovmikhail.mvvmandroidproject.di.application.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class DataDBModule {
    companion object {
        private const val DATABASE_NAME = "news_db.db"
    }

    @Provides
    @ApplicationScope
    fun provideNewsDatabase(app: Application): AbstractNewsDatabase = Room.databaseBuilder(
        app,
        AbstractNewsDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @ApplicationScope
    fun provideNewsDao(database: AbstractNewsDatabase): NewsDao = database.newsDao()
}
