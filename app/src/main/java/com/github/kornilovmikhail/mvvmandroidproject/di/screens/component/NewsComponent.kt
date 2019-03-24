package com.github.kornilovmikhail.mvvmandroidproject.di.screens.component

import com.github.kornilovmikhail.mvvmandroidproject.di.application.component.AppComponent
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.module.DataDBModule
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.module.NewsModule
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.scope.NewsScope
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.module.DataNetModule
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.module.ViewModelModule
import com.github.kornilovmikhail.mvvmandroidproject.ui.MainActivity
import com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newsdetail.NewsDetailFragment
import com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newslist.NewsListFragment
import dagger.Component

@NewsScope
@Component(
    dependencies = [AppComponent::class],
    modules = [DataNetModule::class, DataDBModule::class, NewsModule::class, ViewModelModule::class]
)
interface NewsComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(newsListFragment: NewsListFragment)
    fun inject(newsDetailFragment: NewsDetailFragment)
}
