package com.mohammadosman.roomsocialrdb.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mohammadosman.roomsocialrdb.data.dao.UserDao
import com.mohammadosman.roomsocialrdb.data.model.Posts
import com.mohammadosman.roomsocialrdb.data.model.User
import com.mohammadosman.roomsocialrdb.data.model.UserAccount

@Database(entities = [User::class, UserAccount::class, Posts::class], version = 1, exportSchema = false)
abstract class SocialDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {

        const val DB_NAME = "social_db"

        @Volatile
        private var instance: SocialDatabase? = null

        operator fun invoke(application: Application) = synchronized(this) {
            instance ?: initDb(application).also {
                instance = it
            }
        }

        private fun initDb(application: Application) = Room.databaseBuilder(
            application,
            SocialDatabase::class.java,
            DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}