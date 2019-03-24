package com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newslist

import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.*
import com.github.kornilovmikhail.mvvmandroidproject.interactor.TopNewsInteractor
import com.github.kornilovmikhail.mvvmandroidproject.model.News
import com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newsdetail.NewsDetailFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

class NewsListViewModel(private val topNewsInteractor: TopNewsInteractor) : ViewModel(), LifecycleObserver {
    val newsLiveData = MutableLiveData<List<News>>()
    val inProgressLiveData = MutableLiveData<Int>()
    var isSuccessLiveData = MutableLiveData<Boolean>()
    private var disposable: Disposable? = null
    private val isFirst = true

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun getNewsList() {
        disposable = topNewsInteractor.getTopNews()
            .doOnSubscribe {
                inProgressLiveData.postValue(View.VISIBLE)
            }
            .doAfterTerminate {
                inProgressLiveData.postValue(View.INVISIBLE)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    topNewsInteractor.deleteTopNews()
                    topNewsInteractor.cacheTopNews(it)
                    isSuccessLiveData.value = true
                },
                onError = {
                    isSuccessLiveData.value = false
                }
            )
    }

    override fun onCleared() {
        disposable?.dispose()
    }

    fun openNews(supportFragmentManager: FragmentManager?, id: Int) {
        NewsDetailFragment.start(supportFragmentManager, id)
    }
}
