package com.github.kornilovmikhail.mvvmandroidproject.interactor

import com.github.kornilovmikhail.mvvmandroidproject.data.repository.NewsRepository
import com.github.kornilovmikhail.mvvmandroidproject.model.News
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class TopNewsInteractor(private val newsRepository: NewsRepository) {
    fun getTopNews(): Single<List<News>> {
        return newsRepository.getTopNews()
            .subscribeOn(Schedulers.io())
    }

    fun cacheTopNews(newsList: List<News>) {
        Completable.fromAction {
            newsRepository.cacheTopNews(newsList)
        }.subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun deleteTopNews() {
        Completable.fromAction {
            newsRepository.deleteTopEvents()
        }.subscribeOn(Schedulers.io())
            .subscribe()
    }
}
