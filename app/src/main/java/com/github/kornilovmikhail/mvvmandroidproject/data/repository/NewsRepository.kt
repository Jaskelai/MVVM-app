package com.github.kornilovmikhail.mvvmandroidproject.data.repository

class NewsRepository(private val newsNetworkRepository: NewsNetworkRepository,
                     private val newsLocalRepository: NewsLocalRepository) {
    fun getNews() {

    }
}
