package com.github.kornilovmikhail.mvvmandroidproject.data.repository

import com.github.kornilovmikhail.mvvmandroidproject.data.mapper.mapNewsRemoteToNews
import com.github.kornilovmikhail.mvvmandroidproject.data.network.NewsApi
import com.github.kornilovmikhail.mvvmandroidproject.model.News

class NewsNetworkRepository constructor(private val newsApi: NewsApi) {
    suspend fun getTopNews(): List<News> {
        val news = newsApi.loadTopHeadlinesAsync()
        return news.await().newsRemoteList
            .map { mapNewsRemoteToNews(it) }
    }
}
