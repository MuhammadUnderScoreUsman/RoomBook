package com.mohammadosman.roomsocialrdb.repository.post

// Todo Post Repository
interface PostRepository {
    suspend fun logout(): Boolean
}