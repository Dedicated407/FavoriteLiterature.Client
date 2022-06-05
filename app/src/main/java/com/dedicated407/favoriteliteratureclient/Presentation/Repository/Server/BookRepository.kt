package com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server

import com.dedicated407.favoriteliteratureclient.DI.ServiceLocator
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Responses.BooksListResponseModel

class BookRepository {
    private var mApiFavLit: IWebService = ServiceLocator.getInstance().getApiFavLit()

    suspend fun getAllBooks(): List<BooksListResponseModel> =
        mApiFavLit.getAllBooks()
}