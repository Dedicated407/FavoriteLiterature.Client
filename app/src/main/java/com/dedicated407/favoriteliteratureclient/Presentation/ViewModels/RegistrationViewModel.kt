package com.dedicated407.favoriteliteratureclient.Presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dedicated407.favoriteliteratureclient.DI.ServiceLocator
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Repository
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.ErrorResponse
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Responses.JwtTokenResponseModel
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.content
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.first
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegistrationViewModel : ViewModel() {
    private val repository: Repository = Repository()
    private val _response: MutableLiveData<JwtTokenResponseModel> = MutableLiveData()
    private val _lastError: MutableLiveData<String> = MutableLiveData()
    val response: LiveData<JwtTokenResponseModel> by ::_response
    val lastError: LiveData<String> by ::_lastError

    fun register(userName: String, email: String, password: String, passwordConfirm: String): MutableLiveData<JwtTokenResponseModel> {

        viewModelScope.launch {
            try {
                repository.register(userName, email, password, passwordConfirm)

                repository.authenticate(email, password).let {
                    _response.value = JwtTokenResponseModel(it)

                    ServiceLocator.getInstance().jwtToken = it
                }

            } catch (e: HttpException) {
                if (e.code() == 400) {
                    _lastError.value = e.content<ErrorResponse>()?.first
                } else {
                    _lastError.value = "Опаньки... Что-то пошло не так..."
                }
            } catch (e: NullPointerException) {
                _lastError.value = "Нет связи с сервером"
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }

        return _response
    }
}