package com.manojb.blog.service;

import com.manojb.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {


    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);

    void deleteCategory(int categoryId);

    CategoryDto getCategoryById(int categoryId);

    List<CategoryDto> getCategories();

}
