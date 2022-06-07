package com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Requests

data class RegisterRequestModel(
    val userName: String,
    val email: String,
    val password: String,
    val passwordConfirmation: String,
)