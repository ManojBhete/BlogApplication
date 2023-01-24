package com.manojb.blog.repository;

import com.manojb.blog.entity.Category;
import com.manojb.blog.payloads.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {




}
