package com.example.test.controller

import com.example.test.models.AddBookModel
import com.example.test.models.ResponseModel
import com.example.test.models.UpdateBookModel
import com.example.test.service.BookService
import io.swagger.annotations.ApiOperation
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api")
class BookController(private val bookService: BookService) {

    @GetMapping(value = ["/books"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "")
    fun getAll(): ResponseEntity<ResponseModel> {
        return bookService.getAllBooks()
    }

    @PostMapping(value = ["/book"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addBook(@RequestBody request: AddBookModel): ResponseEntity<ResponseModel> {
        return bookService.addBook(request)
    }

    @PostMapping(value = ["/books"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addBooks(@RequestBody request: List<AddBookModel>): ResponseEntity<ResponseModel> {
        return bookService.addBooks(request)
    }

    @PutMapping(value = ["/book/{id}/edit"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateBookById(@PathVariable("id") bookId: Int,
                       @RequestBody request: UpdateBookModel): ResponseEntity<ResponseModel> {
        return bookService.editBookById(bookId, request)
    }

    @DeleteMapping(value = ["/book/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteBooksById(@PathVariable("id") bookId: Int): ResponseEntity<ResponseModel> {
        return bookService.deleteBookById(bookId)
    }

    @DeleteMapping(value = ["/books"], produces = [])
    fun deleteBooksById(@RequestBody bookIds: List<Int>): ResponseEntity<ResponseModel> {
        return bookService.deleteBooksByIds(bookIds)
    }

    @PutMapping(value = ["/book/{id}/assign"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun assignBookCategoryById(@PathVariable("id") bookId: Int,
                               @RequestParam categoryId: Int): ResponseEntity<ResponseModel> {
        return bookService.assignBookCategoryById(bookId, categoryId)
    }

    @PutMapping(value = ["/book/{id}/remove"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun removeBookCategoryById(@PathVariable("id") bookId: Int,
                               @RequestParam categoryId: Int): ResponseEntity<ResponseModel> {
        return bookService.removeBookCategoryById(bookId, categoryId)
    }
}