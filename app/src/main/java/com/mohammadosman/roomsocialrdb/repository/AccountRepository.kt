package com.mohammadosman.roomsocialrdb.repository

import com.mohammadosman.roomsocialrdb.util.Response
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    fun createAccount(
        uid: String?,
        roomMail: String,
        userName: String,
        profilePic: Int
    ): Flow<Response<Unit>>

    fun loginAccount(
        userName: String
    ): Flow<Response<Unit>>

}