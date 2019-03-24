package com.github.kornilovmikhail.mvvmandroidproject.data.repository

import com.github.kornilovmikhail.mvvmandroidproject.data.local.dao.NewsDao
import com.github.kornilovmikhail.mvvmandroidproject.data.mapper.mapNewsDBToNews
import com.github.kornilovmikhail.mvvmandroidproject.data.mapper.mapNewsToNewsDB
import com.github.kornilovmikhail.mvvmandroidproject.model.News
import io.reactivex.Single

class NewsLocalRepository(private val newsDao: NewsDao) {
    fun getTopNews(): Single<List<News>> = newsDao.getNewsList()
        .map { it.map { mapNewsDBToNews(it) } }

    fun cacheTopNews(newsList: List<News>) {
        newsDao.insertNewsList(newsList.map { mapNewsToNewsDB(it) })
    }

    fun deleteTopNews() {
        newsDao.deleteAllNews()
    }

    fun findNewsById(id: Int): Single<News> = newsDao.findNewsById(id)
        .map { mapNewsDBToNews(it) }
}
