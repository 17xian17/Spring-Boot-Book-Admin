package com.example.test.utils

import com.example.test.entity.Book
import com.example.test.entity.Category
import com.example.test.models.ResponseModel
import com.example.test.repository.BookRepository
import com.example.test.repository.CategoryRepository
import javassist.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class FunctionService(private val bookRepository: BookRepository,
                      private val categoryRepository: CategoryRepository
) {
    fun response(status: HttpStatus,
                 result: Any,
                 action: String
    ): ResponseEntity<ResponseModel> {
        val res = if(status.value() == 200 || status.value() == 201){
            ResponseModel("success", "$action successful", result)
        }else {
            ResponseModel("failed", "$action failed", result)
        }
        return ResponseEntity.status(status).body(res)
    }

    fun getBookById(bookId: Int): Book {
        val book = bookRepository.findById(bookId)
        if (!book.isPresent){
            throw NotFoundException("No matching book was found")
        }
        return book.get()
    }

    fun getCategoryById(categoryId: Int): Category {
        val category = categoryRepository.findById(categoryId)
        if (!category.isPresent){
            throw NotFoundException("No matching book was found")
        }
        return category.get()
    }
}