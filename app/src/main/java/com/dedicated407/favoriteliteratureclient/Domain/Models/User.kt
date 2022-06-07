package com.dedicated407.favoriteliteratureclient.Domain.Models

import java.util.*

open class User(
    open var id: UUID = UUID.randomUUID(),
    open var userName: String,
    open var email: String,
    open var firstName: String? = null,
    open var lastName: String? = null,
    open var patronymic: String? = null,
    open var role: String,
)