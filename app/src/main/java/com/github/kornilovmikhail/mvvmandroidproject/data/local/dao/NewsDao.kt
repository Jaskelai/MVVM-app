package com.github.kornilovmikhail.mvvmandroidproject.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.kornilovmikhail.mvvmandroidproject.data.local.model.NewsDB
import io.reactivex.Single

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewsList(news: List<NewsDB>)

    @Query("SELECT * FROM news_data")
    fun getNewsList(): Single<List<NewsDB>>

    @Query("SELECT * FROM news_data WHERE id = :id")
    fun findNewsById(id: Int): Single<NewsDB>

    @Query("DELETE FROM news_data")
    fun deleteAllNews()
}
