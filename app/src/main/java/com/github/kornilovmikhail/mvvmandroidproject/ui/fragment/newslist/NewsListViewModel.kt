package com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newslist

import android.view.View
import androidx.lifecycle.*
import com.github.kornilovmikhail.mvvmandroidproject.data.repository.NewsRepository
import com.github.kornilovmikhail.mvvmandroidproject.model.News
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class NewsListViewModel(private val newsRepository: NewsRepository) : ViewModel(), LifecycleObserver {
    val newsLiveData = MutableLiveData<List<News>>()
    val inProgress = MutableLiveData<Int>()
    var isSuccess = MutableLiveData<Boolean>()
    private var disposable: Disposable? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun getNews() {
        disposable = newsRepository.getTopNews()
            .doOnSubscribe {
                inProgress.postValue(View.VISIBLE)
            }
            .doAfterTerminate {
                inProgress.postValue(View.INVISIBLE)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    newsLiveData.value = it
                    isSuccess.value = true
                },
                onError = {
                    isSuccess.value = false
                }
            )
    }

    override fun onCleared() {
        disposable?.dispose()
    }

    fun openNews() {

    }
}
