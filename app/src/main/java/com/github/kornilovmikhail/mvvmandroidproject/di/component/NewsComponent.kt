package com.github.kornilovmikhail.mvvmandroidproject.di.component

import com.github.kornilovmikhail.mvpandroidproject.di.module.*
import com.github.kornilovmikhail.mvpandroidproject.di.scope.EventScope
import com.github.kornilovmikhail.mvpandroidproject.ui.detail.DetailsActivity
import com.github.kornilovmikhail.mvpandroidproject.ui.links.LinksActivity
import com.github.kornilovmikhail.mvpandroidproject.ui.main.MainActivity
import com.github.kornilovmikhail.mvvmandroidproject.di.module.DataNetModule
import com.github.kornilovmikhail.mvvmandroidproject.di.scope.NewsScope
import dagger.Component

@NewsScope
@Component(
    dependencies = [AppComponent::class],
    modules = [BaseModule::class, MainModule::class, DetailModule::class,
        DataNetModule::class, DataDBModule::class]
)
interface NewsComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(detailsActivity: DetailsActivity)

    fun inject(linksActivity: LinksActivity)
}
