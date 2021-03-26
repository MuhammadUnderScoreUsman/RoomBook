package com.mohammadosman.roomsocialrdb.data.dao

import androidx.room.*
import com.mohammadosman.roomsocialrdb.data.model.Posts
import com.mohammadosman.roomsocialrdb.data.relations.onetomany.UserAccountWithPosts
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: Posts): Long

    @Transaction
    @Query("Select * from user_account")
    fun getAllPost(): Flow<List<UserAccountWithPosts>>

    @Query("Select * from user_account")
    suspend fun getUserAccount(): UserAccountWithPosts?

    @Query("select * from posts")
    fun getPosts(): Flow<List<Posts>>

}