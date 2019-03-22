package com.github.kornilovmikhail.mvvmandroidproject.data.network.model

data class NewsRemote(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)
