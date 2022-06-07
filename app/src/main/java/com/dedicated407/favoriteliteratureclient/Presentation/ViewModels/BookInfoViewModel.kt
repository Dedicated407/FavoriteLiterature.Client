package com.dedicated407.favoriteliteratureclient.Presentation.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.dedicated407.favoriteliteratureclient.Domain.Models.Book
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Repository
import java.util.*

class BookInfoViewModel(
    private val repository: Repository
) : ViewModel() {
    private val mResponse: MutableLiveData<Book> = MutableLiveData()

    fun getBook(id: String) =
        liveData(viewModelScope.coroutineContext) {
            mResponse.value = repository.getBook(UUID.fromString(id))

            emitSource(
                mResponse
            )
        }
}