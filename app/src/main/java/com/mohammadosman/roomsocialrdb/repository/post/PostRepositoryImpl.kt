package com.mohammadosman.roomsocialrdb.repository.post

import android.app.Application
import android.util.Log
import com.mohammadosman.roomsocialrdb.data.dao.PostDao
import com.mohammadosman.roomsocialrdb.data.dao.UserDao
import com.mohammadosman.roomsocialrdb.data.db.SocialDatabase
import com.mohammadosman.roomsocialrdb.data.model.Posts
import com.mohammadosman.roomsocialrdb.data.relations.onetomany.UserAccountWithPosts
import com.mohammadosman.roomsocialrdb.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

class PostRepositoryImpl(
   application: Application
) : PostRepository {

    var userDao: UserDao
    var postDao: PostDao

    init {
        val db = SocialDatabase.invoke(application)
        userDao = db.userDao()
        postDao = db.postDao()

    }

    override suspend fun logout(): Boolean {
        val userAuthentication = userDao.checkAuthentication()
        var logout = false
        var getid = ""
        for (it in userAuthentication) {
                if (it.loginAuth != null && it.loginAuth == it.uid) {
                    getid = it.uid ?: ""
                    logout = true
                }
            }

        if (logout) {
            val update = userDao.updateUserAccForLoginAuth(
                getid,
                null
            )
            return update > 0
        }
        return logout
    }

    override fun createPost(postPic: Int, postDesc: String): Flow<Response<Unit>> {
        return flow {
            val checkAuthId = userDao.checkAuth()
            checkAuthId?.let {
                if (it.loginAuth?.isNotEmpty() == true || it.loginAuth != null) {
                    val createPost = postDao.insertPosts(
                        posts = Posts(
                            pId = UUID.randomUUID().toString(),
                            postPicture = postPic,
                            postDesc = postDesc,
                            uid = it.loginAuth
                        )
                    )
                    if (createPost > 0) {
                        emit(Response.Success(Unit, "Post Created"))
                    } else {
                        emit(
                            Response.Error(
                                Unit,
                                "Cant create Post cause room insertion is < 0 !!"
                            )
                        )
                    }
                }
            }
        }
    }

    override fun returnList(): Flow<List<UserAccountWithPosts>> {
        return postDao.getAllPost()
    }

    override fun returnPosts(): Flow<List<Posts>> {
        return postDao.getPosts()
    }
}