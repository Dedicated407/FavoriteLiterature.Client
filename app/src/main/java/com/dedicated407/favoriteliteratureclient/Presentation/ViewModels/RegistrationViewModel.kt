package com.dedicated407.favoriteliteratureclient.Presentation.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dedicated407.favoriteliteratureclient.DI.ServiceLocator
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Repository
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Responses.JwtTokenResponseModel
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel() {
    private val repository: Repository = Repository()
    private val mResponse: MutableLiveData<JwtTokenResponseModel> = MutableLiveData()

    fun registration(userName: String, email: String, password: String, passwordConfirm: String): MutableLiveData<JwtTokenResponseModel> {

        try {
            viewModelScope.launch {
                repository.registration(userName, email, password, passwordConfirm)

                repository.authentication(email, password).let {
                    mResponse.value = JwtTokenResponseModel(it)

                    ServiceLocator.getInstance().jwtToken = it
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return mResponse
    }
}