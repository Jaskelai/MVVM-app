package com.github.kornilovmikhail.mvvmandroidproject.di.component

import com.github.kornilovmikhail.mvvmandroidproject.di.module.DataDBModule
import com.github.kornilovmikhail.mvvmandroidproject.di.module.DataNetModule
import com.github.kornilovmikhail.mvvmandroidproject.di.scope.NewsScope
import dagger.Component

@NewsScope
@Component(
    dependencies = [AppComponent::class],
    modules = [DataNetModule::class, DataDBModule::class]
)
interface NewsComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(detailsActivity: DetailsActivity)

    fun inject(linksActivity: LinksActivity)
}
