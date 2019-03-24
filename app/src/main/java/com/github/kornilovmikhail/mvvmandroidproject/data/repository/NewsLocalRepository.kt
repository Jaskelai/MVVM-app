package com.github.kornilovmikhail.mvvmandroidproject.data.repository

import com.github.kornilovmikhail.mvvmandroidproject.data.local.dao.NewsDao
import com.github.kornilovmikhail.mvvmandroidproject.data.mapper.mapNewsDBToNews
import com.github.kornilovmikhail.mvvmandroidproject.data.mapper.mapNewsToNewsDB
import com.github.kornilovmikhail.mvvmandroidproject.model.News
import io.reactivex.Single

class NewsLocalRepository(private val newsDao: NewsDao) {
    fun getTopNews(): Single<List<News>> {
        return newsDao.getEvents()
            .map { it.map { mapNewsDBToNews(it) } }
    }

    fun cacheTopNews(newsList: List<News>) {
        newsDao.insertEvents(newsList.map { mapNewsToNewsDB(it) })
    }

    fun deleteTopNews() {
        newsDao.deleteEvents()
    }
}
