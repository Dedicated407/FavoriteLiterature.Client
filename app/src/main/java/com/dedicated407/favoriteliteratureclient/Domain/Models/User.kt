package com.dedicated407.favoriteliteratureclient.Domain.Models

import java.util.*

open class User(
    open var id: UUID = UUID.randomUUID(),
    open var email: String? = null,
    open var password: String? = null,
    open var jwtToken: String? = null,
    open var role: Role = Role.User,
    open var userName: String? = null,
    open var firstName: String? = null,
    open var lastName: String? = null,
    open var patronymic: String? = null,
)