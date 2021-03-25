package com.mohammadosman.roomsocialrdb.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mohammadosman.roomsocialrdb.data.model.User
import com.mohammadosman.roomsocialrdb.data.model.UserAccount
import com.mohammadosman.roomsocialrdb.data.relations.onetoone.UserAndUserAccounts

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

    @Query("Select * from user where user_name = :userName")
    suspend fun checkUserViaUsername(userName: String): User?

    @Query("Select * from user_account")
    suspend fun checkAuth(): UserAccount?

    @Query("Select * from user_account")
    suspend fun checkAuthentication(): List<UserAccount>

    @Query("Update user_account SET login_auth = :loginAu where uid = :id")
    suspend fun updateUserAccForLoginAuth(
        id: String?,
        loginAu: String?
    ): Int
}