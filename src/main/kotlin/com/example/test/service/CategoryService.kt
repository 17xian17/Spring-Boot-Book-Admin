package com.example.test.service

import com.example.test.entity.Category
import com.example.test.models.ResponseModel
import com.example.test.repository.CategoryRepository
import com.example.test.utils.FunctionService
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Example
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository,
                      private val functions: FunctionService
) {

    private val log = LoggerFactory.getLogger(CategoryService::class.java)

    fun addCategory(category: String): ResponseEntity<ResponseModel> {
        val (status, result) = try {
            //check if the category is exist or not if yes throw an error
            if (categoryRepository.exists(Example.of(Category(category)))) {
                throw IllegalArgumentException("$category is already added")
            }else{
                categoryRepository.save(Category(category))
            }
            HttpStatus.CREATED to category
        } catch (e: Exception) {
            log.error(e.message)
            HttpStatus.BAD_REQUEST to e.message.toString()
        }
        return functions.response(status, result, "adding category")
    }

    fun addCategories(categories: List<String>): ResponseEntity<ResponseModel> {
        val (status, result) = try {
            //check first if all the categories to be added are not exist.
            categories.map { category ->
                if (categoryRepository.exists(Example.of(Category(category)))) {
                    throw IllegalArgumentException("$category is already added")
                }
            }
            //get all the categories
            val newCategories = categories.map { category -> Category(category) }
            //save all the categories
            categoryRepository.saveAll(newCategories)
            HttpStatus.CREATED to newCategories
        } catch (e: Exception) {
            log.error(e.message)
            HttpStatus.BAD_REQUEST to e.message.toString()
        }
        return functions.response(status, result, "adding category")
    }

    fun getAllCategories(): ResponseEntity<ResponseModel> {
        val (status, result) = try {
            //get all the categories
            val categories = categoryRepository.findAll()
            HttpStatus.CREATED to categories
        } catch (e: Exception) {
            log.error(e.message)
            HttpStatus.BAD_REQUEST to e.message.toString()
        }
        return functions.response(status, result, "get all categories")
    }
}