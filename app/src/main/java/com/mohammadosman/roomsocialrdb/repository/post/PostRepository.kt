package com.mohammadosman.roomsocialrdb.repository.post

import com.mohammadosman.roomsocialrdb.data.model.Posts
import com.mohammadosman.roomsocialrdb.data.relations.onetomany.UserAccountWithPosts
import com.mohammadosman.roomsocialrdb.util.Response
import kotlinx.coroutines.flow.Flow

interface PostRepository {


    suspend fun logout(): Boolean

     fun createPost(
        postPic: Int,
        postDesc: String
    ): Flow<Response<Unit>>

    fun returnList() : Flow<List<UserAccountWithPosts>>

    fun returnPosts() :Flow<List<Posts>>

}