package com.github.kornilovmikhail.mvvmandroidproject.di.module

import android.content.Context
import androidx.room.Room
import com.github.kornilovmikhail.mvvmandroidproject.data.local.AbstractNewsDatabase
import com.github.kornilovmikhail.mvvmandroidproject.data.local.dao.NewsDao
import com.github.kornilovmikhail.mvvmandroidproject.data.repository.NewsLocalRepository
import com.github.kornilovmikhail.mvvmandroidproject.di.scope.NewsScope
import dagger.Module
import dagger.Provides

@Module
class DataDBModule {
    companion object {
        private const val DATABASE_NAME = "news_db"
    }

    @Provides
    @NewsScope
    fun provideNewsDatabase(context: Context): AbstractNewsDatabase = Room.databaseBuilder(
        context,
        AbstractNewsDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @NewsScope
    fun provideNewsDao(newsDatabase: AbstractNewsDatabase): NewsDao = newsDatabase.newsDao()

    @Provides
    @NewsScope
    fun provideNewsLocalRepository(newsDao: NewsDao): NewsLocalRepository = NewsLocalRepository(newsDao)
}
