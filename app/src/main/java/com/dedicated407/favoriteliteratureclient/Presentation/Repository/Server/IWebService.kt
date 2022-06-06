package com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server

import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Requests.JwtTokenRequestModel
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Responses.AuthorsListResponseModel
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Responses.BooksListResponseModel
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Responses.JwtTokenResponseModel
import retrofit2.http.*

const val path = "api"
const val users = "$path/users"
const val books = "$path/books"
const val authors = "$path/authors"

interface IWebService {
    @POST("$users/token")
    suspend fun authentication(@Body requestBody: JwtTokenRequestModel): JwtTokenResponseModel

    @GET(books)
    suspend fun getAllBooks(): List<BooksListResponseModel>

    @GET(authors)
    suspend fun getAllAuthors(): List<AuthorsListResponseModel>
}