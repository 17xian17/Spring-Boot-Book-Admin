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
    @ApiOperation(value = "Get all the book that you created")
    fun getAll(): ResponseEntity<ResponseModel> {
        return bookService.getAllBooks()
    }

    @PostMapping(value = ["/book"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "Create a new book to be save on DB")
    fun addBook(@RequestBody request: AddBookModel): ResponseEntity<ResponseModel> {
        return bookService.addBook(request)
    }

    @PostMapping(value = ["/books"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "Create a multiple books to be save on DB")
    fun addBooks(@RequestBody request: List<AddBookModel>): ResponseEntity<ResponseModel> {
        return bookService.addBooks(request)
    }

    @PutMapping(value = ["/book/{id}/edit"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "Edit the details of the book you wanted by passing the id of the book")
    fun updateBookById(@PathVariable("id") bookId: Int,
                       @RequestBody request: UpdateBookModel): ResponseEntity<ResponseModel> {
        return bookService.editBookById(bookId, request)
    }

    @DeleteMapping(value = ["/book/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "Delete a book by passing id of the book")
    fun deleteBooksById(@PathVariable("id") bookId: Int): ResponseEntity<ResponseModel> {
        return bookService.deleteBookById(bookId)
    }

    @DeleteMapping(value = ["/books"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "Delete books by passing multiple id of the books")
    fun deleteBooksById(@RequestBody bookIds: List<Int>): ResponseEntity<ResponseModel> {
        return bookService.deleteBooksByIds(bookIds)
    }

    @PutMapping(value = ["/book/{id}/assign"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "Assign category for a book by passing the id of the category and book")
    fun assignBookCategoryById(@PathVariable("id") bookId: Int,
                               @RequestParam categoryId: Int): ResponseEntity<ResponseModel> {
        return bookService.assignBookCategoryById(bookId, categoryId)
    }

    @PutMapping(value = ["/book/{id}/remove"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "Remove category for a book by passing the id of the category and book")
    fun removeBookCategoryById(@PathVariable("id") bookId: Int,
                               @RequestParam categoryId: Int): ResponseEntity<ResponseModel> {
        return bookService.removeBookCategoryById(bookId, categoryId)
    }
}