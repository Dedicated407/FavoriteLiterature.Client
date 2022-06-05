package com.dedicated407.favoriteliteratureclient.Presentation.ViewModels

import androidx.lifecycle.*
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.BookRepository
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Responses.BooksListResponseModel

class BooksListViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    private val mResponse: MutableLiveData<List<BooksListResponseModel>> = MutableLiveData()

    fun getAllBooks(): LiveData<List<BooksListResponseModel>> =
        liveData(viewModelScope.coroutineContext) {
            val books = bookRepository.getAllBooks()
            mResponse.value = books

            emitSource(
                mResponse
            )
        }
}