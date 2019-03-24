package com.github.kornilovmikhail.mvvmandroidproject.data.repository

import com.github.kornilovmikhail.mvvmandroidproject.model.News
import io.reactivex.Single

class NewsRepository(
    private val newsNetworkRepository: NewsNetworkRepository,
    private val newsLocalRepository: NewsLocalRepository
) {
    private var isFirst = true

    fun getTopNews(): Single<List<News>> {
        return if (isFirst) {
            isFirst = false
            newsNetworkRepository.getTopNews()
        } else {
            newsLocalRepository.getTopNews()
        }
    }

    fun getNewsById(id: Int): Single<News> = newsLocalRepository.findNewsById(id)

    fun cacheTopNews(newsList: List<News>) {
        newsLocalRepository.cacheTopNews(newsList)
    }

    fun deleteTopEvents() = newsLocalRepository.deleteTopNews()
}
