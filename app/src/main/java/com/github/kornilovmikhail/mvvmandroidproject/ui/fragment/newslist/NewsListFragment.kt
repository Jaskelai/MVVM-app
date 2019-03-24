package com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newslist


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kornilovmikhail.mvvmandroidproject.App

import com.github.kornilovmikhail.mvvmandroidproject.R
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.component.DaggerNewsComponent
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.module.DataDBModule
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.module.DataNetModule
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.module.NewsModule
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.module.ViewModelModule
import com.github.kornilovmikhail.mvvmandroidproject.model.News
import kotlinx.android.synthetic.main.fragment_news_list.*
import javax.inject.Inject

class NewsListFragment : Fragment() {
    private lateinit var newsListViewModel: NewsListViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<NewsListViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerNewsComponent.builder()
            .appComponent(App.getAppComponents())
            .dataNetModule(DataNetModule())
            .dataDBModule(DataDBModule())
            .newsModule(NewsModule())
            .viewModelModule(ViewModelModule())
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsListViewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsListViewModel::class.java)
        newsListViewModel.newsLiveData.observe(this@NewsListFragment, Observer {
            if (rv_list_news.adapter == null) {
                rv_list_news.adapter = NewsListAdapter(newsClickListener)
                rv_list_news.layoutManager = LinearLayoutManager(context)

            }
            (rv_list_news.adapter as NewsListAdapter).submitList(it)
        })
        newsListViewModel.inProgress.observe(
            this@NewsListFragment,
            Observer { it?.let { list_progressBar.visibility = it } })
        lifecycle.addObserver(newsListViewModel)
    }

    private val newsClickListener: (News) -> Unit = {
        newsListViewModel.openNews()
    }

    companion object {
        fun newInstance(): NewsListFragment {
            return NewsListFragment()
        }
    }
}
