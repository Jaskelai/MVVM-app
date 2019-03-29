package com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newslist

import androidx.lifecycle.*
import com.github.kornilovmikhail.mvvmandroidproject.interactor.TopNewsInteractor
import com.github.kornilovmikhail.mvvmandroidproject.model.News
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

class NewsListViewModel(private val topNewsInteractor: TopNewsInteractor) : ViewModel(), LifecycleObserver {
    private var disposable: Disposable? = null
    val newsLiveData = MutableLiveData<List<News>>()
    val inProgressLiveData = MutableLiveData<Boolean>()
    var isSuccessLiveData = MutableLiveData<Boolean>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun getNewsList() {
        disposable = topNewsInteractor.getTopNews()
            .doOnSubscribe {
                inProgressLiveData.postValue(true)
            }
            .doAfterTerminate {
                inProgressLiveData.postValue(false)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    newsLiveData.value = it
                    isSuccessLiveData.value = true
                },
                onError = {
                    isSuccessLiveData.value = false
                }
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}
