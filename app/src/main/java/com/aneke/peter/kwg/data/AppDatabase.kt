package com.aneke.peter.kwg.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Post::class, RemoteKeys::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao() : PostDao
    abstract fun remoteKeysDao() : RemoteKeysDao

    companion object{
        private const val DB_NAME = "app_db"
        private var appDatabase : AppDatabase? = null

        fun getInstance(context : Context) : AppDatabase? {
            if (appDatabase == null) {
                synchronized(AppDatabase::class) {
                    appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                        .addCallback(object :RoomDatabase.Callback(){

                        })
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return appDatabase
        }
    }



}