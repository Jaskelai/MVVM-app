package com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newsdetail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.kornilovmikhail.mvvmandroidproject.App

import com.github.kornilovmikhail.mvvmandroidproject.di.screens.component.DaggerNewsComponent
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.module.DataDBModule
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.module.NewsModule
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.module.ViewModelModule
import com.github.kornilovmikhail.mvvmandroidproject.utils.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_news_details.*
import javax.inject.Inject
import androidx.databinding.DataBindingUtil
import com.github.kornilovmikhail.mvvmandroidproject.R
import com.squareup.picasso.Picasso


class NewsDetailFragment : Fragment() {
    private var newsDetailViewModel: NewsDetailViewModel? = null
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<NewsDetailViewModel>

    companion object {
        private const val KEY_NEWS_ID = "id_news"

        fun start(supportFragmentManager: FragmentManager?, id: Int) {
            val args = Bundle()
            args.putInt(KEY_NEWS_ID, id)
            val instance = newInstance()
            instance.arguments = args
            supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_container, instance)
                ?.addToBackStack(null)
                ?.commit()
        }

        fun newInstance(): NewsDetailFragment {
            return NewsDetailFragment()
        }
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
    ): View? {
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsDetailViewModel::class.java)
        newsDetailViewModel?.getNews(arguments?.get(KEY_NEWS_ID) as Int)
        observeNewsDetailData()
        observeInProgress()
        observeIsSuccess()
    }

    private fun observeNewsDetailData() {
        newsDetailViewModel?.newsLiveData?.observe(this, Observer {
            tv_detail_description.text = it.description
            activity?.let { activity ->
                Picasso.get()
                    .load(it.urlToImage)
                    .into(iv_details_image)
            }
        })
    }

    private fun observeInProgress() {
        newsDetailViewModel?.inProgress?.observe(
            this,
            Observer { it?.let { details_progressBar.visibility = it } })
    }

    private fun observeIsSuccess() {
        newsDetailViewModel?.isSuccess?.observe(this, Observer {
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
}
