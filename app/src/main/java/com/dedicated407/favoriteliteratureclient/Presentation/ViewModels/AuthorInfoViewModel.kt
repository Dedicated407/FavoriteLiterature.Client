package com.dedicated407.favoriteliteratureclient.Presentation.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.dedicated407.favoriteliteratureclient.Domain.Models.Author
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Repository
import java.util.*

class AuthorInfoViewModel(
    private val repository: Repository
) : ViewModel() {
    private val mResponse: MutableLiveData<Author> = MutableLiveData()

    fun getAuthor(id: String) =
        liveData(viewModelScope.coroutineContext) {
            mResponse.value = repository.getAuthor(UUID.fromString(id))

            emitSource(
                mResponse
            )
        }
}