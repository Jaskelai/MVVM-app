package com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newslist

import com.github.kornilovmikhail.mvvmandroidproject.data.repository.NewsRepository
import com.github.kornilovmikhail.mvvmandroidproject.model.News
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class NewsInteractor(private val newsRepository: NewsRepository) {
    fun getNewsList(): Single<List<News>> {
        return newsRepository.getTopNews()
            .subscribeOn(Schedulers.io())
    }
}
