package com.github.kornilovmikhail.mvvmandroidproject.data.repository

import com.github.kornilovmikhail.mvvmandroidproject.data.mapper.mapNewsRemoteToNews
import com.github.kornilovmikhail.mvvmandroidproject.data.network.NewsApi
import com.github.kornilovmikhail.mvvmandroidproject.model.News
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class NewsNetworkRepository constructor(private val newsApi: NewsApi) {
    fun getTopNews(): Single<List<News>> = newsApi
        .loadTopHeadlines()
        .map { it.newsRemoteList }
        .map { it.map { mapNewsRemoteToNews(it) } }
        .subscribeOn(Schedulers.io())
}
