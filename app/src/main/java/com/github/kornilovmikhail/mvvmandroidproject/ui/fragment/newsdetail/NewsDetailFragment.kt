package com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newsdetail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager

import com.github.kornilovmikhail.mvvmandroidproject.R


class NewsDetailFragment : Fragment() {

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }

}
