package com.dedicated407.favoriteliteratureclient.Domain.Models

import java.util.*

open class Author(
    open val id: UUID,
    open val userName: String,
    open val email: String,
    open val firstName: String,
    open val lastName: String,
    open val patronymic: String,
    open val description: String?,
    open val birthday: Date?,
    open val phoneNumber: String?,
    open val address: String?,
    open val rating: Float?,
)