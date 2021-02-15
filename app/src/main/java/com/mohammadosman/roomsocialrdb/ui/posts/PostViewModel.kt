package com.mohammadosman.roomsocialrdb.ui.posts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mohammadosman.roomsocialrdb.repository.post.PostRepository
import com.mohammadosman.roomsocialrdb.repository.post.PostRepositoryImpl

class PostViewModel(application: Application) : AndroidViewModel(application) {

    private val repo: PostRepository = PostRepositoryImpl(application)

    suspend fun logout() = repo.logout()

}