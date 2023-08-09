package com.example.navigationfirstproject

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.navigationfirstproject.dao.NewsDao
import com.example.navigationfirstproject.dao.UserDao
import com.example.navigationfirstproject.entity.News
import com.example.navigationfirstproject.entity.User

@Database(
    entities = [User::class, News::class],
    version = 1
)

abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun newsDao(): NewsDao
}