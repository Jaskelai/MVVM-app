package com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newslist


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.github.kornilovmikhail.mvvmandroidproject.R
import javax.inject.Inject

class NewsListFragment : Fragment() {
    @Inject
    lateinit var newsListViewModel: NewsListViewModel

    companion object {
        fun newInstance(): NewsListFragment {
            return NewsListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }


}
