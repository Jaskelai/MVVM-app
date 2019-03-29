package com.github.kornilovmikhail.mvvmandroidproject.data.repository

import com.github.kornilovmikhail.mvvmandroidproject.model.News
import io.reactivex.Completable
import io.reactivex.Single

class NewsRepository(
    private val newsNetworkRepository: NewsNetworkRepository,
    private val newsLocalRepository: NewsLocalRepository
) {

    private var isFirst = true

    fun getTopNews(): Single<List<News>> =
        if (isFirst) {
            isFirst = false
            newsNetworkRepository.getTopNews()
        } else {
            newsLocalRepository.getTopNews()
        }

    fun getNewsById(id: Int): Single<News> = newsLocalRepository.findNewsById(id)

    fun cacheTopNews(newsList: List<News>): Completable =
        newsLocalRepository.cacheTopNews(newsList)

    fun deleteTopEvents(): Completable = newsLocalRepository.deleteTopNews()
}
