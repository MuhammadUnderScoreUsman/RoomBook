package com.mohammadosman.roomsocialrdb.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mohammadosman.roomsocialrdb.data.model.User
import com.mohammadosman.roomsocialrdb.data.model.UserAccount
import com.mohammadosman.roomsocialrdb.data.relations.UserAndUserAccounts

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserAccount(userAccount: UserAccount): Long

    @Query("Select * from user where user_name = :userName")
    suspend fun getUser(userName: String): UserAndUserAccounts?

    @Query("Delete from user where room_mail_address = :userEmail")
    suspend fun deleteViaEmail(userEmail: String): Int

    @Query("Select * from user where room_mail_address = :rMail")
    suspend fun checkUserViaEmail(rMail: String): User?
}