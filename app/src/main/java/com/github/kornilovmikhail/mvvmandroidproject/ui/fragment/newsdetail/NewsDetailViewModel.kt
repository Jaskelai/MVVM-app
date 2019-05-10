package com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newsdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.mvvmandroidproject.interactor.TopNewsInteractor
import com.github.kornilovmikhail.mvvmandroidproject.model.News
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class NewsDetailViewModel(private val topNewsInteractor: TopNewsInteractor) : ViewModel() {
    val newsLiveData = MutableLiveData<News>()
    val inProgressLiveData = MutableLiveData<Boolean>()
    var isSuccessLiveData = MutableLiveData<Boolean>()

    private var job = SupervisorJob()
    private val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    suspend fun getNews(id: Int) {
        val handler = CoroutineExceptionHandler { _, _ ->
            isSuccessLiveData.value = false
        }
        inProgressLiveData.postValue(true)
        CoroutineScope(coroutineContext).launch(handler) {
            val news = withContext(Dispatchers.IO) {
                topNewsInteractor.getNews(id)
            }
            withContext(Dispatchers.Main) {
                newsLiveData.value = news
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
