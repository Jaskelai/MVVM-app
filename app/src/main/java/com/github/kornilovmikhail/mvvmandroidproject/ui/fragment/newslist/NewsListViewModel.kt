package com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newslist

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.mvvmandroidproject.model.News
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy

class NewsListViewModel(private val newsInteractor: NewsInteractor) : ViewModel() {
    val newsLiveData = MutableLiveData<List<News>>()
    val inProgress = MutableLiveData<Int>()

    fun getNews() {
        newsInteractor.getNewsList()
            .doOnSubscribe {
                inProgress.value = View.VISIBLE
            }
            .doAfterTerminate {
                inProgress.value = View.INVISIBLE
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    newsLiveData.value = it
                },
                onError = {

                }
            )
    }

    fun openNews() {

    }
}
