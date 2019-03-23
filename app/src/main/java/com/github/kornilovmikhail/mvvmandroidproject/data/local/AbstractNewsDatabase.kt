package com.github.kornilovmikhail.mvvmandroidproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.kornilovmikhail.mvvmandroidproject.data.local.dao.NewsDao
import com.github.kornilovmikhail.mvvmandroidproject.data.local.model.NewsDB

@Database(entities = [NewsDB::class], version = 1)
abstract class AbstractNewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}
