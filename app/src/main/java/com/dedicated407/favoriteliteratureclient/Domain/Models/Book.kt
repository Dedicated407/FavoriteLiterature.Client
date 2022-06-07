package com.dedicated407.favoriteliteratureclient.Domain.Models

import java.util.*

open class Book(
    open val id: UUID,
    open val name: String,
    open val author: Author,
    open val description: String?,
    open val rating: Float = 0f
)