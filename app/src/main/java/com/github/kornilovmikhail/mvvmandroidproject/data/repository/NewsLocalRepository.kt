package com.github.kornilovmikhail.mvvmandroidproject.data.repository

import com.github.kornilovmikhail.mvvmandroidproject.data.local.dao.NewsDao
import com.github.kornilovmikhail.mvvmandroidproject.data.mapper.mapNewsDBToNews
import com.github.kornilovmikhail.mvvmandroidproject.data.mapper.mapNewsToNewsDB
import com.github.kornilovmikhail.mvvmandroidproject.model.News
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class NewsLocalRepository(private val newsDao: NewsDao) {
    fun getTopNews(): Single<List<News>> = newsDao.getNewsList()
        .map { it.map { mapNewsDBToNews(it) } }
        .subscribeOn(Schedulers.io())

    fun cacheTopNews(newsList: List<News>): Completable = Completable.fromAction {
        newsDao.insertNewsList(newsList.map { mapNewsToNewsDB(it) })
    }.subscribeOn(Schedulers.io())

    fun deleteTopNews(): Completable = Completable.fromAction {
        newsDao.deleteAllNews()
    }.subscribeOn(Schedulers.io())

    fun findNewsById(id: Int): Single<News> = newsDao.findNewsById(id)
        .map { mapNewsDBToNews(it) }
        .subscribeOn(Schedulers.io())
}
