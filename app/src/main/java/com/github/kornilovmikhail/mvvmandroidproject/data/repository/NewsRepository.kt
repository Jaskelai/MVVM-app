package com.github.kornilovmikhail.mvvmandroidproject.data.repository

import com.github.kornilovmikhail.mvvmandroidproject.model.News

class NewsRepository(
    private val newsNetworkRepository: NewsNetworkRepository,
    private val newsLocalRepository: NewsLocalRepository
) {

    private var isFirst = true

    suspend fun getTopNews(): List<News> =
        if (isFirst) {
            isFirst = false
            newsNetworkRepository.getTopNews()
        } else {
            newsLocalRepository.getTopNews()
        }

    suspend fun getNewsById(id: Int): News = newsLocalRepository.findNewsById(id)

    suspend fun cacheTopNews(newsList: List<News>) = newsLocalRepository.cacheTopNews(newsList)

    suspend fun deleteTopEvents() = newsLocalRepository.deleteTopNews()
}
