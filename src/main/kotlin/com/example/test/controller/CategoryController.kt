package com.example.test.controller

import com.example.test.models.ResponseModel
import com.example.test.service.CategoryService
import io.swagger.annotations.ApiOperation
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api")
class CategoryController(private val categoryService: CategoryService) {

    @PostMapping(value = ["/category"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "Create a new category to be save on DB")
    fun addCategory(@RequestParam category: String): ResponseEntity<ResponseModel> {
        return categoryService.addCategory(category)
    }

    @PostMapping(value = ["/categories"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "Create a multiple category to be save on DB")
    fun addCategories(@RequestBody categories: List<String>): ResponseEntity<ResponseModel> {
        return categoryService.addCategories(categories)
    }

    @GetMapping(value = ["/categories"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "Get all the category that you created")
    fun getAllCategories(): ResponseEntity<ResponseModel> {
        return categoryService.getAllCategories()
    }
}