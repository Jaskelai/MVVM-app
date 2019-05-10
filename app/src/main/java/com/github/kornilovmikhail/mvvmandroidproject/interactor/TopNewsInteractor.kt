package com.github.kornilovmikhail.mvvmandroidproject.interactor

import com.github.kornilovmikhail.mvvmandroidproject.data.repository.NewsRepository
import com.github.kornilovmikhail.mvvmandroidproject.model.News

class TopNewsInteractor(private val newsRepository: NewsRepository) {

    //TODO
    suspend fun getTopNews(): List<News> =
        newsRepository.getTopNews()
            .map {
                deleteTopNews()
                cacheTopNews(it)
            }
            .flatMap { newsRepository.getTopNews() }

    suspend fun cacheTopNews(newsList: List<News>) =
        newsRepository.cacheTopNews(newsList)

    suspend fun deleteTopNews() =
        newsRepository.deleteTopEvents()

    suspend fun getNews(id: Int): News = newsRepository.getNewsById(id)
}
