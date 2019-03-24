package com.github.kornilovmikhail.mvvmandroidproject.di.screens.module

import android.app.Application
import androidx.room.Room
import com.github.kornilovmikhail.mvvmandroidproject.data.local.AbstractNewsDatabase
import com.github.kornilovmikhail.mvvmandroidproject.data.local.dao.NewsDao
import com.github.kornilovmikhail.mvvmandroidproject.data.repository.NewsLocalRepository
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.scope.NewsScope
import dagger.Module
import dagger.Provides

@Module
class DataDBModule {
    companion object {
        private const val DATABASE_NAME = "news_db"
    }

    @Provides
    @NewsScope
    fun provideNewsDatabase(app: Application): AbstractNewsDatabase = Room.databaseBuilder(
        app,
        AbstractNewsDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @NewsScope
    fun provideNewsDao(database: AbstractNewsDatabase): NewsDao = database.newsDao()

    @Provides
    @NewsScope
    fun provideNewsLocalRepository(newsDao: NewsDao): NewsLocalRepository = NewsLocalRepository(newsDao)
}
