package com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Requests

data class JwtTokenRequestModel(
    val email: String,
    val password: String,
)