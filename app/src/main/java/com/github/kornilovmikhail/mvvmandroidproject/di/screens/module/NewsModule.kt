package com.github.kornilovmikhail.mvvmandroidproject.di.screens.module

import com.github.kornilovmikhail.mvvmandroidproject.data.network.NewsApi
import com.github.kornilovmikhail.mvvmandroidproject.data.repository.NewsLocalRepository
import com.github.kornilovmikhail.mvvmandroidproject.data.repository.NewsNetworkRepository
import com.github.kornilovmikhail.mvvmandroidproject.data.repository.NewsRepository
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.scope.NewsScope
import com.github.kornilovmikhail.mvvmandroidproject.interactor.TopNewsInteractor
import dagger.Module
import dagger.Provides

@Module
class NewsModule {
    @Provides
    @NewsScope
    fun provideNewsRepository(
        newsLocalRepository: NewsLocalRepository,
        newsNetworkRepository: NewsNetworkRepository
    ): NewsRepository = NewsRepository(newsNetworkRepository, newsLocalRepository)

    @Provides
    @NewsScope
    fun provideNewsNetworkRepository(newsApi: NewsApi): NewsNetworkRepository = NewsNetworkRepository(newsApi)

    @Provides
    @NewsScope
    fun provideNewsInteractor(newsRepository: NewsRepository): TopNewsInteractor = TopNewsInteractor(newsRepository)
}
