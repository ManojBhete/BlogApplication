package com.manojb.blog.service.impl;

import com.manojb.blog.entity.Category;
import com.manojb.blog.exception.ResourceNotFoundException;
import com.manojb.blog.payloads.CategoryDto;
import com.manojb.blog.repository.CategoryRepo;
import com.manojb.blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto)
    {
         // here  converting like use service .. i'm directly using model mapper .. I am converting categoryDto to category
       Category category   = this.modelMapper.map(categoryDto, Category.class);
       Category addedCategory = this.categoryRepo.save(category);

         // again converting from category to categoryDto
        return  this.modelMapper.map(addedCategory, CategoryDto.class) ;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId)
    {

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
       category.setCategoryTitle(categoryDto.getCategoryTitle());
       category.setCategoryDescription(categoryDto.getCategoryDescription());

       Category updatedCategory  = this.categoryRepo.save(category);

     return this.modelMapper.map(updatedCategory, CategoryDto.class);

    }

    @Override
    public void deleteCategory(int categoryId) {


      Category category =  this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
        this.categoryRepo.delete(category) ;

    }

    @Override
    public CategoryDto getCategoryById(int categoryId)
    {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories()
    {

        List<Category> categories = this.categoryRepo.findAll();

       List<CategoryDto> categoryDtos  = categories.stream().map((category -> this.modelMapper.map(category , CategoryDto.class))).collect(Collectors.toList());

        return categoryDtos;
    }
}
