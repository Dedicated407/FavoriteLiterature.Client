package com.dedicated407.favoriteliteratureclient.Presentation.ViewModels

import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.dedicated407.favoriteliteratureclient.Domain.Models.User
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Repository

class PersonalAccountViewModel(
    private val repository: Repository
) : ViewModel() {
    private val mResponse: MutableLiveData<User> = MutableLiveData()

    fun signOut(context: Context): Boolean {

        val key = context.getSharedPreferences(
            "AuthKey",
            Context.MODE_PRIVATE
        ).all["jwt"]

        if (key != null) {
            context.getSharedPreferences("AuthKey", Context.MODE_PRIVATE).edit {
                putString("jwt", null)
            }

            return true
        }
        return false
    }


    fun getUserInfo() =
        liveData(viewModelScope.coroutineContext) {
            mResponse.value = repository.getProfile()

            emitSource(
                mResponse
            )
        }
}