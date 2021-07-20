package com.example.test.models

import org.springframework.stereotype.Component

@Component
data class CategoryModel(
    var name: String? = null,
    var books: List<BookModel>? = null
)