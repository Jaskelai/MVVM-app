package com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newslist


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.kornilovmikhail.mvvmandroidproject.App

import com.github.kornilovmikhail.mvvmandroidproject.R
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.component.DaggerNewsComponent
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.module.DataDBModule
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.module.DataNetModule
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.module.NewsModule
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.module.ViewModelModule
import javax.inject.Inject

class NewsListFragment : Fragment() {
    @Inject
    lateinit var newsListViewModel: NewsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DaggerNewsComponent.builder()
            .appComponent(App.getAppComponents())
            .dataNetModule(DataNetModule())
            .dataDBModule(DataDBModule())
            .newsModule(NewsModule())
            .viewModelModule(ViewModelModule())
            .build()
            .inject(this)
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    companion object {
        fun newInstance(): NewsListFragment {
            return NewsListFragment()
        }
    }


}
