package com.github.kornilovmikhail.mvvmandroidproject.data.network

import com.github.kornilovmikhail.mvvmandroidproject.data.network.model.NewsApiResponse
import io.reactivex.Single
import retrofit2.http.GET

interface NewsApi {
    @GET("top-headlines?country=ru")
    fun loadTopHeadlines(): Single<NewsApiResponse>
}
