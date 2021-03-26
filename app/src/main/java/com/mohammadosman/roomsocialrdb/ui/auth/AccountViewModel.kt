package com.mohammadosman.roomsocialrdb.ui.auth

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.mohammadosman.roomsocialrdb.repository.account.AccountRepository
import com.mohammadosman.roomsocialrdb.repository.account.AccountRepositoryImpl
import com.mohammadosman.roomsocialrdb.ui.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AccountViewModel(
    application: Application
) : BaseViewModel(application) {

    private val accountRepository: AccountRepository = AccountRepositoryImpl(application)

    fun insertUser(
        roomMail: String,
        userName: String
    ) {
        accountRepository.createAccount(
            null,
            roomMail = roomMail,
            userName = userName,
            171
        ).onEach { response ->
            responseReturn(response)
        }.launchIn(viewModelScope)
    }


    fun loginAccount(userName: String) {
        accountRepository.loginAccount(userName = userName).onEach {
            responseReturn(it)
        }.launchIn(viewModelScope)
    }

    suspend fun checkUserAuth() = accountRepository.checkAuth()


}