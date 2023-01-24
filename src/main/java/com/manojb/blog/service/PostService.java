package com.manojb.blog.service;


import com.manojb.blog.entity.Post;
import com.manojb.blog.payloads.PostDto;
import com.manojb.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {

   PostDto createPost(PostDto postDto, int userId, int categoryId);

   PostDto updatePost(PostDto postDto, int postId);

   void deletePost(int postId);

   PostResponse getAllPost(int pageNumber, int pageSize, String sortBy, String sortDir);

   PostDto getPostById(int postId);

   List<PostDto> getPostsByCategory(int categoryId);

   List<PostDto> getPostsByUser(int userId);



   // search title

   public List<PostDto> searchPost(String keyword);




}
