package com.github.kornilovmikhail.mvvmandroidproject.di.screens.module

import com.github.kornilovmikhail.mvvmandroidproject.data.repository.NewsRepository
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.scope.NewsScope
import com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newslist.NewsListViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    @NewsScope
    fun provideNewsListViewModule(newsRepository: NewsRepository): NewsListViewModel = NewsListViewModel(newsRepository)
}
