package com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newslist

import androidx.lifecycle.*
import com.github.kornilovmikhail.mvvmandroidproject.interactor.TopNewsInteractor
import com.github.kornilovmikhail.mvvmandroidproject.model.News
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class NewsListViewModel(private val topNewsInteractor: TopNewsInteractor) : ViewModel(), LifecycleObserver {
    val newsLiveData = MutableLiveData<List<News>>()
    val inProgressLiveData = MutableLiveData<Boolean>()
    var isSuccessLiveData = MutableLiveData<Boolean>()

    private var job = SupervisorJob()
    private val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    suspend fun getNewsList() {
        val handler = CoroutineExceptionHandler { _, _ ->
            isSuccessLiveData.value = false
        }
        inProgressLiveData.postValue(true)
        CoroutineScope(coroutineContext).launch(handler) {
            val news = withContext(Dispatchers.IO) {
                topNewsInteractor.getTopNews()
            }
            withContext(Dispatchers.Main) {
                newsLiveData.value = news
                isSuccessLiveData.value = true
            }
        }.invokeOnCompletion {
            inProgressLiveData.postValue(false)
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }
}
