package com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newsdetail

import androidx.lifecycle.*
import com.github.kornilovmikhail.mvvmandroidproject.interactor.TopNewsInteractor
import com.github.kornilovmikhail.mvvmandroidproject.model.News
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

class NewsDetailViewModel(private val topNewsInteractor: TopNewsInteractor) : ViewModel() {
    val newsLiveData = MutableLiveData<News>()
    private var disposable: Disposable? = null
    val inProgressLiveData = MutableLiveData<Boolean>()
    var isSuccessLiveData = MutableLiveData<Boolean>()

    fun getNews(id: Int) {
        disposable = topNewsInteractor.getNews(id)
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
                },
                onError = {
                    isSuccessLiveData.value = false
                }
            )
    }

    override fun onCleared() {
        disposable?.dispose()
    }
}
