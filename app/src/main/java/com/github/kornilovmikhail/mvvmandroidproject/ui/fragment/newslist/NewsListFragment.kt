package com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newslist

import android.os.Bundle
import android.transition.*
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kornilovmikhail.mvvmandroidproject.App
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.component.DaggerNewsComponent
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.module.NewsModule
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.module.ViewModelModule
import com.github.kornilovmikhail.mvvmandroidproject.model.News
import com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newsdetail.NewsDetailFragment
import com.github.kornilovmikhail.mvvmandroidproject.utils.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_news_list.*
import kotlinx.android.synthetic.main.news_list_item.*
import javax.inject.Inject
import kotlin.properties.Delegates
import com.github.kornilovmikhail.mvvmandroidproject.R

class NewsListFragment : Fragment() {
    private var newsListViewModel: NewsListViewModel by Delegates.notNull()

    @Inject lateinit var viewModelFactory: ViewModelFactory<NewsListViewModel>

    companion object {
        const val KEY_NEWS_ID = "id_news"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerNewsComponent.builder()
            .appComponent(App.getAppComponents())
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

    private fun initAdapter(){
        if (rv_list_news.adapter == null) {
            rv_list_news.adapter = NewsListAdapter(newsClickListener)
            rv_list_news.layoutManager = LinearLayoutManager(context)
            rv_list_news.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun observeNewsLiveData() {
        newsListViewModel.newsLiveData.observe(this, Observer {
            initAdapter()
            rv_list_news.adapter.submitList(it)
        })
    }

    private fun observeInProgress() {
        newsListViewModel.inProgressLiveData.observe(this, Observer {
            it?.let { list_progressBar.visibility =
                if (it) View.VISIBLE else View.GONE } })
    }

    private fun observeIsSuccess() {
        newsListViewModel.isSuccessLiveData.observe(this, Observer {
            makeToast(
                if (it) {
                    getString(R.string.server_load_success)
                } else{
                    getString(R.string.server_load_error)
                }
            )
        })
    }

    private fun makeToast(text: String) =
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

    private val newsClickListener: (News) -> Unit = {
        val args = Bundle()
        args.putInt(KEY_NEWS_ID, it.id)
        val newsDetailFragment = NewsDetailFragment()
        setupTransitionAnimation(newsDetailFragment)
        newsDetailFragment.arguments = args
        activity?.supportFragmentManager?.beginTransaction()
            ?.addSharedElement(iv_main_image, getString(R.string.transaction_img_news))
            ?.replace(R.id.main_container, newsDetailFragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun setupTransitionAnimation(fragment: Fragment) {
        val transitionSet = TransitionSet().setOrdering(TransitionSet.ORDERING_TOGETHER)
            .addTransition(ChangeBounds())
            .addTransition(ChangeTransform())
            .addTransition(ChangeImageTransform())
        fragment.sharedElementEnterTransition = transitionSet
        fragment.sharedElementReturnTransition = transitionSet
        fragment.enterTransition = Fade()
        fragment.exitTransition = Fade()
    }
}
