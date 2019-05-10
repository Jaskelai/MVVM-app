package com.github.kornilovmikhail.mvvmandroidproject.interactor

import com.github.kornilovmikhail.mvvmandroidproject.data.repository.NewsRepository
import com.github.kornilovmikhail.mvvmandroidproject.model.News

class TopNewsInteractor(private val newsRepository: NewsRepository) {

    suspend fun getTopNews(): List<News> {
        val topNews =  newsRepository.getTopNews()
        deleteTopNews()
        cacheTopNews(topNews)
        return topNews
    }

    suspend fun cacheTopNews(newsList: List<News>) =
        newsRepository.cacheTopNews(newsList)

    suspend fun deleteTopNews() = newsRepository.deleteTopEvents()

    suspend fun getNews(id: Int): News = newsRepository.getNewsById(id)
}
