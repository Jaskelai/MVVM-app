package com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.mvvmandroidproject.model.News
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy

class NewsListViewModel(private val newsInteractor: NewsInteractor) : ViewModel() {
    val newsLiveData = MutableLiveData<List<News>>()

    fun getNews() {
        newsInteractor.getNewsList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    newsLiveData.value = it
                },
                onError = {

                }
            )
    }
}
