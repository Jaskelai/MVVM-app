package com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newsdetail

import android.view.View
import androidx.lifecycle.*
import com.github.kornilovmikhail.mvvmandroidproject.interactor.TopNewsInteractor
import com.github.kornilovmikhail.mvvmandroidproject.model.News
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

class NewsDetailViewModel(private val topNewsInteractor: TopNewsInteractor) : ViewModel() {
    val newsLiveData = MutableLiveData<News>()
    private var disposable: Disposable? = null
    val inProgress = MutableLiveData<Int>()
    var isSuccess = MutableLiveData<Boolean>()

    fun getNews(id: Int) {
        disposable = topNewsInteractor.getNews(id)
            .doOnSubscribe {
                inProgress.postValue(View.VISIBLE)
            }
            .doAfterTerminate {
                inProgress.postValue(View.INVISIBLE)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    newsLiveData.value = it
                },
                onError = {
                    isSuccess.value = false
                }
            )
    }
}
