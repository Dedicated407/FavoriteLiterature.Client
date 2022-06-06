package com.dedicated407.favoriteliteratureclient.Presentation.ViewModels

import androidx.lifecycle.*
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Repository
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Responses.BooksListResponseModel

class BooksListViewModel(
    private val repository: Repository
) : ViewModel() {
    private val mResponse: MutableLiveData<List<BooksListResponseModel>> = MutableLiveData()

    fun getAllBooks(): LiveData<List<BooksListResponseModel>> =
        liveData(viewModelScope.coroutineContext) {
            val books = repository.getAllBooks()
            mResponse.value = books

            emitSource(
                mResponse
            )
        }
}