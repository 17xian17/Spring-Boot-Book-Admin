package com.example.test.service

import com.example.test.entity.Book
import com.example.test.entity.Category
import com.example.test.models.AddBookModel
import com.example.test.models.BookModel
import com.example.test.models.ResponseModel
import com.example.test.models.UpdateBookModel
import com.example.test.repository.BookRepository
import com.example.test.repository.CategoryRepository
import com.example.test.utils.FunctionService
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Example
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class BookService(private val bookRepository: BookRepository,
                  private val categoryRepository: CategoryRepository,
                  private val function: FunctionService,
                  private val bookModel: BookModel
) {

    private val log = LoggerFactory.getLogger(BookService::class.java)

    fun getAllBooks(): ResponseEntity<ResponseModel> {
        val (status, result) = try {
            //get all books
            val books  = bookRepository.findAll()
            HttpStatus.CREATED to bookModel.build(books)
        }catch (e: Exception){
            log.error(e.message)
            HttpStatus.BAD_REQUEST to e.message.toString()
        }
        return function.response(status, result, "adding book")
    }

    fun addBook(model: AddBookModel): ResponseEntity<ResponseModel> {
        val (status, result) = try {
            //new book to be created
            val newBook = Book(
                title = model.title,
                author = model.author,
                description = model.description
            )
            //check if the book is exist or not if yes throw an error
            if (bookRepository.exists(Example.of(newBook))){
                throw IllegalArgumentException("The book was already added")
            }else{
                bookRepository.save(newBook)
            }
            HttpStatus.CREATED to bookModel.build(newBook)
        }catch (e: Exception){
            log.error(e.message)
            HttpStatus.BAD_REQUEST to e.message.toString()
        }
        return function.response(status, result, "adding book")
    }

    fun addBooks(models: List<AddBookModel>): ResponseEntity<ResponseModel> {
        val (status, result) = try {
            //check first all the categories are not exist
            val books = models.map { model ->
                //new book to be created
                val book = Book(
                    title = model.title,
                    author = model.author,
                    description = model.description
                )
                //check if all books are exist or not if yes throw an error
                if (bookRepository.exists(Example.of(book))){
                    throw IllegalArgumentException("The book titled: ${model.title} was already added")
                }
                book
            }
            bookRepository.saveAll(books)
            HttpStatus.CREATED to bookModel.build(books)
        }catch (e: Exception){
            log.error(e.message)
            HttpStatus.BAD_REQUEST to e.message.toString()
        }
        return function.response(status, result, "adding books")
    }

    fun editBookById(bookId: Int, model: UpdateBookModel): ResponseEntity<ResponseModel> {
        val (status, result) = try {
            //get book
            val book = function.getBookById(bookId)
            //check if the value is null
            if (model.title != null){ book.title = model.title }
            if (model.author != null){ book.author = model.author }
            if (model.categoryId != null){ book.categoryId = model.categoryId }
            if (model.description != null){ book.description = model.description }
            //save the new details of the book
            val newBook = bookRepository.save(book)
            HttpStatus.CREATED to bookModel.build(newBook)
        }catch (e: Exception){
            log.error(e.message)
            HttpStatus.BAD_REQUEST to e.message.toString()
        }
        return function.response(status, result, "editing book")
    }

    fun deleteBookById(bookId: Int): ResponseEntity<ResponseModel> {
        val (status, result) = try {
            //get book
            val book = function.getBookById(bookId)
            //delete book
            bookRepository.delete(book)
            HttpStatus.CREATED to bookModel.build(book)
        }catch (e: Exception){
            log.error(e.message)
            HttpStatus.BAD_REQUEST to e.message.toString()
        }
        return function.response(status, result, "deleting book")
    }

    fun deleteBooksByIds(bookIds: List<Int>): ResponseEntity<ResponseModel> {
        val (status, result) = try {
            //check first if all the bookIds are correct
            val books = bookIds.map { bookId ->
                function.getBookById(bookId)
            }
            //delete books
            bookRepository.deleteAll(books)
            HttpStatus.CREATED to bookModel.build(books)
        }catch (e: Exception){
            log.error(e.message)
            HttpStatus.BAD_REQUEST to e.message.toString()
        }
        return function.response(status, result, "deleting books")
    }

    fun assignBookCategoryById(bookId: Int, categoryId: Int): ResponseEntity<ResponseModel> {
        val (status, result) = try {
            //get book
            val book = function.getBookById(bookId)
            //get category
            val getCategory = function.getCategoryById(categoryId)
            //set category for a book
            book.categoryId = categoryId
            //get all ids
            val bookIds = getCategory.bookIds!!.toMutableList()
            //add a new bookId
            bookIds.add(bookId.toString())
            val newCategory = Category(
                name = getCategory.name,
                bookIds = bookIds
            )
            //save book
            bookRepository.save(book)
            //save category
            categoryRepository.save(newCategory)
            HttpStatus.CREATED to bookModel.build(book)
        }catch (e: Exception){
            log.error(e.message)
            HttpStatus.BAD_REQUEST to e.message.toString()
        }
        return function.response(status, result, "assigning category of a book")
    }

    fun removeBookCategoryById(bookId: Int, categoryId: Int): ResponseEntity<ResponseModel> {
        val (status, result) = try {
            //get book
            val book = function.getBookById(bookId)
            //get category
            val getCategory = function.getCategoryById(categoryId)
            //set category for a book
            book.categoryId = null
            //get all ids
            val bookIds = getCategory.bookIds!!.toMutableList()
            //add a new bookId
            bookIds.add(bookId.toString())
            val newCategory = Category(
                name = getCategory.name,
                bookIds = bookIds
            )
            //save book
            bookRepository.save(book)
            //save category
            categoryRepository.save(newCategory)
            HttpStatus.CREATED to bookModel.build(book)
        }catch (e: Exception){
            log.error(e.message)
            HttpStatus.BAD_REQUEST to e.message.toString()
        }
        return function.response(status, result, "removing category of a books")
    }
}