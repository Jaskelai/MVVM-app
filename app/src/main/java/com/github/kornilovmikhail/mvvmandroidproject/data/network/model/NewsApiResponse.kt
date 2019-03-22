package com.github.kornilovmikhail.mvvmandroidproject.data.network.model

import com.google.gson.annotations.SerializedName

data class NewsApiResponse(
    val status: String,
    val totalResults: Int,
    @SerializedName("articles") val newsRemoteList: List<NewsRemote>
)
