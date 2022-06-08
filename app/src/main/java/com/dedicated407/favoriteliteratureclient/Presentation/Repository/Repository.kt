package com.dedicated407.favoriteliteratureclient.Presentation.Repository

import com.dedicated407.favoriteliteratureclient.DI.ServiceLocator
import com.dedicated407.favoriteliteratureclient.Domain.Models.Book
import com.dedicated407.favoriteliteratureclient.Domain.Models.User
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.IWebService
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Requests.JwtTokenRequestModel
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Requests.RegisterRequestModel
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Responses.AuthorsListResponseModel
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Responses.BooksListResponseModel
import java.io.EOFException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.*

class Repository {
    private var mApiFavLit: IWebService = ServiceLocator.getInstance().getApiFavLit()

    suspend fun authenticate(email: String, password: String) = catchAll {
        mApiFavLit.authenticate(JwtTokenRequestModel(email, password)).accessToken
    }

    suspend fun register(userName: String, email: String, password: String, passwordConfirm: String) = catchAll {
        mApiFavLit.register(
            RegisterRequestModel(
                userName,
                email,
                password,
                passwordConfirm
            )
        )
    }

    suspend fun getAllBooks(): List<BooksListResponseModel> = catchAll {
        mApiFavLit.getAllBooks()
    }

    suspend fun getAllAuthors(): List<AuthorsListResponseModel> = catchAll {
        mApiFavLit.getAllAuthors()
    }

    suspend fun getBook(bookId: UUID): Book = catchAll {
        mApiFavLit.getBook(bookId)
    }

    suspend fun getProfile(): User = catchAll {
        mApiFavLit.getProfile()
    }

    private suspend inline fun<reified T> catchAll(
        crossinline block: suspend () -> T,
    ): T = try {
        retry(block)
    } catch (e: EOFException) {
        null as T
    } catch (e: UnknownHostException) {
        null as T
    } catch (e: ConnectException) {
        null as T
    } catch (e: retrofit2.HttpException) {
        e.printStackTrace()
        throw e
    } catch (e: Throwable) {
        e.printStackTrace()
        throw e
    }

    private suspend inline fun<reified T> retry(
        crossinline block: suspend () -> T,
    ): T {
        repeat(MAX_RETRY_COUNT) {
            try {
                return block()
            } catch (e: SocketTimeoutException) {
                // Ignore (delay included in timeout)
            }
        }
        return null as T
    }

    companion object {
        const val MAX_RETRY_COUNT = 4
    }
}