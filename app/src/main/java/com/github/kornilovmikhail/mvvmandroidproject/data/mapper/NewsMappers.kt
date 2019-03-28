package com.github.kornilovmikhail.mvvmandroidproject.data.mapper

import com.github.kornilovmikhail.mvvmandroidproject.data.local.model.NewsDB
import com.github.kornilovmikhail.mvvmandroidproject.data.network.model.NewsRemote
import com.github.kornilovmikhail.mvvmandroidproject.model.News

fun mapNewsRemoteToNews(newsRemote: NewsRemote): News = with(newsRemote) {
    News(0, author, title, description, url, urlToImage, publishedAt, content)
}

fun mapNewsDBToNews(newsDB: NewsDB): News = with(newsDB) {
    News(id, author, title, description, url, urlToImage, publishedAt, content)
}

fun mapNewsToNewsDB(news: News): NewsDB = with(news) {
    NewsDB(0, author, title, description, url, urlToImage, publishedAt, content)
}
