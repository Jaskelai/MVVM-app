package com.github.kornilovmikhail.mvvmandroidproject.interactor

import com.github.kornilovmikhail.mvvmandroidproject.data.repository.NewsRepository
import com.github.kornilovmikhail.mvvmandroidproject.model.News
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class TopNewsInteractor(private val newsRepository: NewsRepository) {
    fun getTopNews(): Single<List<News>> = newsRepository.getTopNews()
        .subscribeOn(Schedulers.io())

    fun cacheTopNews(newsList: List<News>): Completable =
        Completable.fromAction {
            newsRepository.cacheTopNews(newsList)
        }.subscribeOn(Schedulers.io())

    fun deleteTopNews(): Completable =
        Completable.fromAction {
            newsRepository.deleteTopEvents()
        }.subscribeOn(Schedulers.io())

    fun getNews(id: Int): Single<News> = newsRepository.getNewsById(id)
        .subscribeOn(Schedulers.io())
}
