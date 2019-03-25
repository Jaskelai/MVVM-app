package com.github.kornilovmikhail.mvvmandroidproject.di.screens.module

import com.github.kornilovmikhail.mvvmandroidproject.data.local.dao.NewsDao
import com.github.kornilovmikhail.mvvmandroidproject.data.network.NewsApi
import com.github.kornilovmikhail.mvvmandroidproject.data.repository.NewsLocalRepository
import com.github.kornilovmikhail.mvvmandroidproject.data.repository.NewsNetworkRepository
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.scope.NewsScope
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    @NewsScope
    fun provideNewsNetworkRepository(newsApi: NewsApi): NewsNetworkRepository = NewsNetworkRepository(newsApi)

    @Provides
    @NewsScope
    fun provideNewsLocalRepository(newsDao: NewsDao): NewsLocalRepository = NewsLocalRepository(newsDao)
}
