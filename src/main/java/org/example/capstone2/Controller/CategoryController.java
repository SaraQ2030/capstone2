package org.example.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.capstone2.API.ApiResponse;
import org.example.capstone2.Model.Category;
import org.example.capstone2.Service.CategoryService;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("api/v1/vision/category")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/get")
    public ResponseEntity getCategory(){
        return ResponseEntity.status(200).body(categoryService.getAllCategories());
    }
    @PostMapping("/add/{id}")
    public ResponseEntity addCategory(@PathVariable Integer id,@RequestBody @Valid Category category, Errors errors){
        if (errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.addCategory(category,id);
        return ResponseEntity.status(200).body(new ApiResponse("category added "));
    }
    @PutMapping("/update/{id}/{adminId}")
    public ResponseEntity updateCategory(@PathVariable Integer id,@PathVariable Integer adminId, @RequestBody @Valid Category category, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.updateCategory(id,category,adminId);
        return ResponseEntity.status(200).body(new ApiResponse("category Updated "));
    }
    @DeleteMapping("/delete/{id}/{adminId}")
    public ResponseEntity deleteCategory(@PathVariable Integer id,@PathVariable Integer adminId){
        categoryService.deleteCategory(id,adminId);
        return ResponseEntity.status(200).body(new ApiResponse("category deleted"));
    }
}
