package com.example.test.entity

import javax.persistence.*

@Entity
@Table(name = "CATEGORY")
open class Category(open var name: String? = "",
                    @ElementCollection
                    open var bookIds: List<String>? = mutableListOf()
) {
    @Id
    @GeneratedValue
    open var id: Int? = null
    constructor(category: Category): this(category.name, category.bookIds) {
        id = category.id
        name = category.name
        bookIds = category.bookIds
    }
}