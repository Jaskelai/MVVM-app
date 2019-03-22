package com.github.kornilovmikhail.mvvmandroidproject.data.mapper

import com.github.kornilovmikhail.mvvmandroidproject.data.local.model.NewsDB
import com.github.kornilovmikhail.mvvmandroidproject.data.network.model.NewsRemote
import com.github.kornilovmikhail.mvvmandroidproject.model.News

class NewsMappers {
    fun mapNewsRemoteToNews(newsRemote: NewsRemote) {
        return with(newsRemote) {
            News(author, title, description, url, urlToImage, publishedAt, content)
        }
    }

    fun mapNewsDBToNews(newsDB: NewsDB) {
        return with(newsDB) {
            News(author, title, description, url, urlToImage, publishedAt, content)
        }
    }
}