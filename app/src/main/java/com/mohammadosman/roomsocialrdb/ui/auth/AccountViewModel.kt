package com.mohammadosman.roomsocialrdb.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mohammadosman.roomsocialrdb.repository.account.AccountRepository
import com.mohammadosman.roomsocialrdb.repository.account.AccountRepositoryImpl
import com.mohammadosman.roomsocialrdb.util.Response
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AccountViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val accountRepository: AccountRepository = AccountRepositoryImpl(application)

    private val _response = MutableSharedFlow<Response<Unit>>()
    val response = _response.asSharedFlow()

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

    private fun responseReturn(response: Response<Unit>) {
        viewModelScope.launch {
            _response.let {
                it.emit(response)
            }
        }
    }

    fun loginAccount(userName: String) {
        accountRepository.loginAccount(userName = userName).onEach {
            responseReturn(it)
        }.launchIn(viewModelScope)
    }

    suspend fun checkUserAuth() = accountRepository.checkAuth()


}