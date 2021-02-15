package com.mohammadosman.roomsocialrdb.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mohammadosman.roomsocialrdb.data.model.Posts
import com.mohammadosman.roomsocialrdb.data.relations.onetoone.UserAndUserAccounts
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    //todo one to many Relation Dao / Post Dao
/*

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: Posts): Long

    @Query("Select * from user_account")
    fun getAllPost(): Flow<List<UserAccountWithPosts>>

    @Query("Select * from user_account")
    suspend fun getUserAccount(): UserAccountWithPosts?
*/



}