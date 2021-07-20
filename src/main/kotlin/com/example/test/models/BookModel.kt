package com.example.test.models

import com.example.test.entity.Book
import org.springframework.stereotype.Component

@Component
class BookModel(
    var id: Int? = null,
    var title: String? = null,
    var author: String? = null,
    var categoryId: Int? = null,
    var description: String? = null
) {
    fun build(book: Book): BookModel{
        return BookModel(
                id = book.id,
                title = book.title,
                author = book.author,
                categoryId = book.categoryId,
                description = book.description
        )
    }

    fun build(books: List<Book>): List<BookModel>{
        return books.map { build(it) }
    }
}