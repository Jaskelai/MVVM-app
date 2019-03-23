package com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newslist

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.mvvmandroidproject.data.repository.NewsRepository
import com.github.kornilovmikhail.mvvmandroidproject.model.News
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class NewsListViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    val newsLiveData = MutableLiveData<List<News>>()
    val inProgress = MutableLiveData<Int>()

    fun getNews() {
        newsRepository.getTopNews()
            .doOnSubscribe {
                inProgress.value = View.VISIBLE
            }
            .doAfterTerminate {
                inProgress.value = View.INVISIBLE
            }
            .subscribeOn(Schedulers.io())
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
