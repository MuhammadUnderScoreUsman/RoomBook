package com.mohammadosman.roomsocialrdb.repository.post

import android.app.Application
import com.mohammadosman.roomsocialrdb.data.dao.PostDao
import com.mohammadosman.roomsocialrdb.data.dao.UserDao
import com.mohammadosman.roomsocialrdb.data.db.SocialDatabase

// Todo Post RepositoryImpl
class PostRepositoryImpl(
    private val application: Application
) : PostRepository {

    var userDao: UserDao
    var postDao: PostDao

    init {
        val db = SocialDatabase.invoke(application)
        userDao = db.userDao()
        postDao = db.postDao()

    }

    override suspend fun logout(): Boolean {
        val userAuth = userDao.checkAuth()
        userAuth?.let {
            val update = userDao.updateUserAccForLoginAuth(
                it.uid,
                null
            )
            return update > 0
        }

        return false
    }
}