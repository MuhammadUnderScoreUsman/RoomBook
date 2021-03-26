package com.mohammadosman.roomsocialrdb.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mohammadosman.roomsocialrdb.util.Response
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {


    private val _response = MutableSharedFlow<Response<Unit>>()
    val response = _response.asSharedFlow()


    protected fun responseReturn(response: Response<Unit>) {
        viewModelScope.launch {
            _response.let {
                it.emit(response)
            }
        }
    }
}