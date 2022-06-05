package com.dedicated407.favoriteliteratureclient.Presentation.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dedicated407.favoriteliteratureclient.DI.ServiceLocator
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Repository
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Responses.JwtTokenResponseModel
import kotlinx.coroutines.launch

class AuthorizationViewModel : ViewModel() {
    private val repository: Repository = Repository()
    private val mResponse: MutableLiveData<JwtTokenResponseModel> = MutableLiveData()

    fun authentication(email: String, password: String): MutableLiveData<JwtTokenResponseModel> {
        viewModelScope.launch {
            repository.authentication(email, password).let {
                mResponse.value = JwtTokenResponseModel(it)

                ServiceLocator.getInstance().jwtToken = it
            }
        }

        return mResponse
    }
}