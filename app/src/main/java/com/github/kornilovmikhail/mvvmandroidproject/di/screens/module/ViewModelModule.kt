package com.github.kornilovmikhail.mvvmandroidproject.di.screens.module

import com.github.kornilovmikhail.mvvmandroidproject.di.screens.scope.NewsScope
import com.github.kornilovmikhail.mvvmandroidproject.interactor.TopNewsInteractor
import com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newsdetail.NewsDetailViewModel
import com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newslist.NewsListViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    @NewsScope
    fun provideNewsListViewModel(topNewsInteractor: TopNewsInteractor) = NewsListViewModel(topNewsInteractor)

    @Provides
    @NewsScope
    fun provideNewsDetailViewModel(topNewsInteractor: TopNewsInteractor) = NewsDetailViewModel(topNewsInteractor)
}
