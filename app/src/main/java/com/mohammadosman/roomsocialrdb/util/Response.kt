package com.mohammadosman.roomsocialrdb.util

sealed class Response<out T> {

    data class Success<T>(val data: T?, val msg: String?) : Response<T>()

    data class Error<T>(val data: T?, val error: String) : Response<T>()

    object Loading : Response<Nothing>()

}