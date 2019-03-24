package com.github.kornilovmikhail.mvvmandroidproject.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_data")
data class NewsDB(
    @ColumnInfo(index = true)
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    val author: String?,

    val title: String,

    val description: String?,

    val url: String,

    @ColumnInfo(name = "url_to_image")
    val urlToImage: String?,

    @ColumnInfo(name = "published_at")
    val publishedAt: String,

    val content: String?
)
