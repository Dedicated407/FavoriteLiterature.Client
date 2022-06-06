package com.dedicated407.favoriteliteratureclient.Presentation.ViewModels

import androidx.lifecycle.*
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Repository
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Responses.AuthorsListResponseModel

class AuthorsListViewModel(
    private val repository: Repository
) : ViewModel() {
    private val mResponse: MutableLiveData<List<AuthorsListResponseModel>> = MutableLiveData()
    private var mediatorLiveData = MediatorLiveData<List<AuthorsListResponseModel>>()

    fun getAllAuthors(): LiveData<List<AuthorsListResponseModel>> =
        liveData(viewModelScope.coroutineContext) {
            val authors = repository.getAllAuthors()
            mResponse.value = authors

            emitSource(
                mResponse
            )
        }

    fun setAuthorsList(query: String) {
        val authors = MutableLiveData<List<AuthorsListResponseModel>>()
        authors.value = mResponse.value
            ?.filter {
                it.toString().contains(query)
            }?.toList()

        mediatorLiveData.addSource(
            authors
        ) {
            mediatorLiveData.value = it
        }
    }

}