package com.manojb.blog.controller;

import com.manojb.blog.payloads.ApiResponse;
import com.manojb.blog.payloads.CategoryDto;
import com.manojb.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

       @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory( @Valid @RequestBody CategoryDto categoryDto )
    {
        CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
    }


    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory( @Valid @RequestBody CategoryDto categoryDto, @PathVariable("catId") int categoryId )
    {
        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto,categoryId);
        return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
    }

     @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int categoryId)
     {
         this.categoryService.deleteCategory(categoryId);
         return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully ", true ), HttpStatus.OK);
     }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getSCategory(@PathVariable int categoryId)
    {
        CategoryDto category = this.categoryService.getCategoryById(categoryId);
        return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategories()
    {
      List<CategoryDto> categories =  this.categoryService.getCategories();
      return ResponseEntity.ok(categories);
    }


}
