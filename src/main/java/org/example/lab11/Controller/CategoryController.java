package org.example.lab11.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.lab11.ApiResponse.ApiResponse;
import org.example.lab11.Model.Category;
import org.example.lab11.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getSingleCategory(@PathVariable Integer id){
        return ResponseEntity.ok(categoryService.getSingleCategory(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody @Valid Category category, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        categoryService.addCategory(category);
        return ResponseEntity.ok(new ApiResponse("<category-controller> category added successfully"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, @RequestBody @Valid Category category, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        categoryService.updateCategory(id, category);
        return ResponseEntity.ok(new ApiResponse("<category-controller> category updated successfully"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new ApiResponse("<category-controller> category deleted successfully"));
    }
}
