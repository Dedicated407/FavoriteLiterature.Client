package com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Responses

import java.util.*

class AuthorsListResponseModel(
    val id: UUID,
    val userName: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val patronymic: String,
) {
    override fun toString(): String {
        return "$lastName $firstName $patronymic"
    }
}