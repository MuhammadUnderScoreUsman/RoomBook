package com.mohammadosman.roomsocialrdb.ui.posts

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mohammadosman.roomsocialrdb.repository.post.PostRepository
import com.mohammadosman.roomsocialrdb.repository.post.PostRepositoryImpl
import com.mohammadosman.roomsocialrdb.ui.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PostViewModel(application: Application) : BaseViewModel(application) {

    private val repo: PostRepository = PostRepositoryImpl(application)

    suspend fun logout() = repo.logout()

    fun createPost(
        postPic: Int,
        postDesc: String
    ) {
        repo.createPost(
            postPic = postPic,
            postDesc = postDesc
        ).onEach { response ->
            responseReturn(
                response = response
            )
        }.launchIn(viewModelScope)

    }

    fun getPost() = repo.returnList()

    fun getAllPost() = repo.returnPosts()

}