package com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server

import com.google.gson.GsonBuilder
import retrofit2.HttpException
import java.io.IOException

inline fun <reified T> HttpException.content() = response()?.errorBody()?.string()?.let {
    try {
        GsonBuilder().create().fromJson(it, T::class.java)
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}

data class ErrorResponse(
    val errors: Errors?,
    val title: String?,
) {
    data class Errors(
        val Email: List<String>?,
        val Password: List<String>?
    )
}

val ErrorResponse.first get() = errors?.let {
    it.Email?.firstOrNull() ?: it.Password?.firstOrNull()
} ?: title