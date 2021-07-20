package com.example.test.controller

import com.example.test.models.ResponseModel
import com.example.test.service.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api")
class CategoryController(private val categoryService: CategoryService) {

    @PostMapping("/category")
    fun addCategory(@RequestParam category: String): ResponseEntity<ResponseModel> {
        return categoryService.addCategory(category)
    }

    @PostMapping("/categories")
    fun addCategories(@RequestBody categories: List<String>): ResponseEntity<ResponseModel> {
        return categoryService.addCategories(categories)
    }

    @GetMapping("/categories")
    fun getAllCategories(): ResponseEntity<ResponseModel> {
        return categoryService.getAllCategories()
    }
}