package com.github.kornilovmikhail.mvvmandroidproject.data.repository

import com.github.kornilovmikhail.mvvmandroidproject.model.News
import io.reactivex.Single

class NewsRepository(private val newsNetworkRepository: NewsNetworkRepository,
                     private val newsLocalRepository: NewsLocalRepository) {
    fun getTopNews(): Single<List<News>> {
        return newsNetworkRepository.getTopNews()
    }
}
