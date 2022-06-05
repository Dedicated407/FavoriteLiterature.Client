package com.dedicated407.favoriteliteratureclient.Presentation.ViewModels

import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.ViewModel

class PersonalAccountViewModel : ViewModel() {

    fun signOut(context: Context): Boolean {

        val key = context.getSharedPreferences(
            "AuthKey",
            Context.MODE_PRIVATE
        ).all["email"]

        if (key != null) {
            context.getSharedPreferences("AuthKey", Context.MODE_PRIVATE).edit {
                putString("email", null)
            }

            return true
        }
        return false
    }


}