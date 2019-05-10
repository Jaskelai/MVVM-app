package com.github.kornilovmikhail.mvvmandroidproject.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.kornilovmikhail.mvvmandroidproject.data.local.model.NewsDB

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsList(news: List<NewsDB>)

    @Query("SELECT * FROM news_data")
    suspend fun getNewsList(): List<NewsDB>

    @Query("SELECT * FROM news_data WHERE id = :id")
    suspend fun findNewsById(id: Int): NewsDB

    @Query("DELETE FROM news_data")
    suspend fun deleteAllNews()
}
