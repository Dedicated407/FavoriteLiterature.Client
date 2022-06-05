package com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server

import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Requests.JwtTokenRequestModel
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Responses.JwtTokenResponseModel
import retrofit2.http.*

const val path = "api"
const val users = "$path/users"

interface IWebService {
    @POST("$users/token")
    suspend fun authentication(@Body requestBody: JwtTokenRequestModel): JwtTokenResponseModel

}