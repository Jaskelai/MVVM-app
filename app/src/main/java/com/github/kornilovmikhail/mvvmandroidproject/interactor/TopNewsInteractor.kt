package com.github.kornilovmikhail.mvvmandroidproject.interactor

import com.github.kornilovmikhail.mvvmandroidproject.data.repository.NewsRepository
import com.github.kornilovmikhail.mvvmandroidproject.model.News
import io.reactivex.Completable
import io.reactivex.Single


class TopNewsInteractor(private val newsRepository: NewsRepository) {

    fun getTopNews(): Single<List<News>> =
        newsRepository.getTopNews()
            .map {
                deleteTopNews().subscribe {
                    cacheTopNews(it).subscribe()
                }
            }
            .flatMap { newsRepository.getTopNews() }

    fun cacheTopNews(newsList: List<News>): Completable =
        newsRepository.cacheTopNews(newsList)

    fun deleteTopNews(): Completable =
        newsRepository.deleteTopEvents()

    fun getNews(id: Int): Single<News> = newsRepository.getNewsById(id)
}
