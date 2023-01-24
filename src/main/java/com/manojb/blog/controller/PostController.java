package com.manojb.blog.controller;

import com.manojb.blog.config.AppConstant;
import com.manojb.blog.payloads.ApiResponse;
import com.manojb.blog.payloads.ImageResponse;
import com.manojb.blog.payloads.PostDto;
import com.manojb.blog.payloads.PostResponse;
import com.manojb.blog.service.FileService;
import com.manojb.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @Value("${project.images}")
    private String path;

    @Autowired
    private FileService fileService;


     @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable int userId, @PathVariable int categoryId )
     {

        PostDto createPost = this.postService.createPost(postDto, userId, categoryId);

        return new ResponseEntity<PostDto>( createPost, HttpStatus.CREATED);

     }
     // GET by use

     @GetMapping("/user/{userId}/posts")
     public ResponseEntity<List<PostDto>>  getPostByUser(@PathVariable int userId)
     {
       List<PostDto> posts = this.postService.getPostsByUser(userId);
       return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

     }

    // GET by category

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>>  getPostByCategory(@PathVariable int categoryId)
    {
        List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

    }

    // get all post
     @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam( value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) int pageNumber ,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_NUMBER, required = false) int pageSize ,
            @RequestParam( value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy ,
            @RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required = false) String sortDir
     )
    {
     PostResponse  postResponse  =  this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
       return new ResponseEntity<PostResponse>( postResponse, HttpStatus.OK);
    }


    // get post by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable int postId)
    {
       PostDto postDto =  this.postService.getPostById(postId);
       return new ResponseEntity<PostDto>(postDto, HttpStatus.OK );
    }

    // delete

    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable int postId)
    {
            this.postService.deletePost(postId);
            return new ApiResponse("post is successfully deleted", true);
    }

    // update post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable int postId)
    {
      PostDto updatePostDto =  this.postService.updatePost(postDto, postId);
      return new ResponseEntity<PostDto>(updatePostDto, HttpStatus.OK);
    }

    // search by keyword title

    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchByPostTitle(@PathVariable("keywords") String keywords)
    {
        List<PostDto> result =  this.postService.searchPost(keywords);
       return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);

    }

   // post image uploaded

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> UploadPostImage(
            @RequestParam("image") MultipartFile image,
            @PathVariable int postId
    ) throws IOException
    {

        PostDto postDto =   this.postService.getPostById(postId);
         String   fileName = this.fileService.uploadImage(path,image);

         postDto.setImageName(fileName);
         PostDto updatedPost = this.postService.updatePost(postDto, postId);
         return new ResponseEntity<PostDto>( updatedPost, HttpStatus.OK);

    }


    // method of serve files
     @GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
     public void downloadFile(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException
     {

         InputStream resource = this.fileService.getResource(path, imageName);
         response.setContentType(MediaType.IMAGE_JPEG_VALUE);
         StreamUtils.copy(resource,response.getOutputStream());

     }






}
