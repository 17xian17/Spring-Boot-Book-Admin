package com.example.test.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "BOOK")
open class Book(open var title: String? = null,
                open var author: String? = null,
                open var categoryId: Int? = null,
                open var categoryName: String? = null,
                open var description: String? = null) {
        @Id
        @GeneratedValue
        open var id: Int? = null
        constructor(book: Book): this(book.title, book.author, book.categoryId, book.categoryName, book.description){
            id = book.id
            title = book.title
            author = book.author
            categoryId = book.categoryId
            categoryName = book.categoryName
            description = book.description
        }
}

