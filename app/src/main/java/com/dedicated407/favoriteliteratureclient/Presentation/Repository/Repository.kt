package com.dedicated407.favoriteliteratureclient.Presentation.Repository

import com.dedicated407.favoriteliteratureclient.DI.ServiceLocator
import com.dedicated407.favoriteliteratureclient.Domain.Models.Book
import com.dedicated407.favoriteliteratureclient.Domain.Models.User
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.IWebService
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Requests.JwtTokenRequestModel
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Requests.RegisterRequestModel
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Responses.AuthorsListResponseModel
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Responses.BooksListResponseModel
import java.util.*

class Repository {
    private var mApiFavLit: IWebService = ServiceLocator.getInstance().getApiFavLit()

    suspend fun authentication(email: String, password: String) =
        mApiFavLit.authentication(JwtTokenRequestModel(email, password)).accessToken

    suspend fun registration(userName: String, email: String, password: String, passwordConfirm: String) =
        mApiFavLit.authentication(RegisterRequestModel(
            userName,
            email,
            password,
            passwordConfirm))


    suspend fun getAllBooks(): List<BooksListResponseModel> =
        mApiFavLit.getAllBooks()

    suspend fun getAllAuthors(): List<AuthorsListResponseModel> =
        mApiFavLit.getAllAuthors()

    suspend fun getBook(bookId: UUID): Book =
        mApiFavLit.getBook(bookId)

    suspend fun getProfile(): User =
        mApiFavLit.getProfile()
}