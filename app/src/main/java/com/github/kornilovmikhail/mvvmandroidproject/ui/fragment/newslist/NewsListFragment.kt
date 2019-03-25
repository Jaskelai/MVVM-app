package com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kornilovmikhail.mvvmandroidproject.App

import com.github.kornilovmikhail.mvvmandroidproject.R
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.component.DaggerNewsComponent
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.module.DataDBModule
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.module.NewsModule
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.module.ViewModelModule
import com.github.kornilovmikhail.mvvmandroidproject.model.News
import com.github.kornilovmikhail.mvvmandroidproject.utils.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_news_list.*
import javax.inject.Inject
import kotlin.properties.Delegates

class NewsListFragment : Fragment() {
    private var newsListViewModel: NewsListViewModel by Delegates.notNull()
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<NewsListViewModel>

    companion object {
        fun newInstance(): NewsListFragment = NewsListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerNewsComponent.builder()
            .appComponent(App.getAppComponents())
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
    ): View? = inflater.inflate(R.layout.fragment_news_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsListViewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsListViewModel::class.java)
        observeNewsLiveData()
        observeInProgress()
        observeIsSuccess()
        lifecycle.addObserver(newsListViewModel)
    }

    private fun observeNewsLiveData() {
        newsListViewModel.newsLiveData.observe(this, Observer {
            if (rv_list_news.adapter == null) {
                rv_list_news.adapter = NewsListAdapter(newsClickListener)
                rv_list_news.layoutManager = LinearLayoutManager(context)
            }
            (rv_list_news.adapter as NewsListAdapter).submitList(it)
        })
    }

    private fun observeInProgress() {
        newsListViewModel.inProgressLiveData.observe(
            this,
            Observer { it?.let { list_progressBar.visibility = it } })
    }

    private fun observeIsSuccess() {
        newsListViewModel.isSuccessLiveData.observe(this, Observer {
            if (it) {
                makeToast(getString(R.string.server_load_success))
            } else {
                makeToast(getString(R.string.server_load_error))
            }
        })
    }

    private fun makeToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    private val newsClickListener: (News) -> Unit = {
        newsListViewModel.openNews(activity?.supportFragmentManager, it.id)
    }
}
