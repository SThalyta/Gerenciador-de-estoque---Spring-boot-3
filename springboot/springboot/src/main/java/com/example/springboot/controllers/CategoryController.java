package com.example.springboot.controllers;

import com.example.springboot.dtos.CategoryRecordDto;
import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.CategoryModel;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.CategoryRepository;
import com.example.springboot.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("/categories")
    public ResponseEntity<CategoryModel> saveCategory(@RequestBody @Valid CategoryRecordDto categoryRecordDto) {
        var categoryModel = new CategoryModel();
        BeanUtils.copyProperties(categoryRecordDto, categoryModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryRepository.save(categoryModel));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryModel>> getAllCategory(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryRepository.findAll());
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Object> getOneCategory(@PathVariable(value="id") Long id){
        Optional<CategoryModel> category0 = categoryRepository.findById(id);
        if(category0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(category0.get());
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable(value="id") Long id, @RequestBody @Valid CategoryRecordDto categoryRecordDto) {
        Optional<CategoryModel> category0 = categoryRepository.findById(id);
        if(category0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
        }
        var categoryModel = category0.get();
        BeanUtils.copyProperties(categoryRecordDto, categoryModel);
        return ResponseEntity.status(HttpStatus.OK).body(categoryRepository.save(categoryModel));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable(value="id") Long id) {
        Optional<CategoryModel> category0 = categoryRepository.findById(id);
        if(category0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
        }
        categoryRepository.delete(category0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Category deleted successfully");
    }
}
