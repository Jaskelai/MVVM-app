package com.github.kornilovmikhail.mvvmandroidproject.data.repository

import com.github.kornilovmikhail.mvvmandroidproject.data.local.dao.NewsDao
import com.github.kornilovmikhail.mvvmandroidproject.data.mapper.mapNewsDBToNews
import com.github.kornilovmikhail.mvvmandroidproject.data.mapper.mapNewsToNewsDB
import com.github.kornilovmikhail.mvvmandroidproject.model.News

class NewsLocalRepository(private val newsDao: NewsDao) {
    suspend fun getTopNews(): List<News> =
        newsDao.getNewsList().map { mapNewsDBToNews(it) }


    suspend fun cacheTopNews(newsList: List<News>) =
        newsDao.insertNewsList(newsList.map { mapNewsToNewsDB(it) })

    suspend fun deleteTopNews() = newsDao.deleteAllNews()

    suspend fun findNewsById(id: Int): News =
        mapNewsDBToNews(newsDao.findNewsById(id))
}
