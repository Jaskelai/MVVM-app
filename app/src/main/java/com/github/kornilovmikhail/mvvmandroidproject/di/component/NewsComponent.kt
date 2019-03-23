package com.github.kornilovmikhail.mvvmandroidproject.di.component

import com.github.kornilovmikhail.mvvmandroidproject.di.module.DataDBModule
import com.github.kornilovmikhail.mvvmandroidproject.di.module.DataNetModule
import com.github.kornilovmikhail.mvvmandroidproject.di.scope.NewsScope
import com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newslist.NewsListFragment
import dagger.Component

@NewsScope
@Component(
    dependencies = [AppComponent::class],
    modules = [DataNetModule::class, DataDBModule::class]
)
interface NewsComponent {
    fun inject(newsListFragment: NewsListFragment)
}
