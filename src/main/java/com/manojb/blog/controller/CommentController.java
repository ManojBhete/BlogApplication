package com.manojb.blog.controller;

import com.manojb.blog.payloads.ApiResponse;
import com.manojb.blog.payloads.CommentDto;
import com.manojb.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

     @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable int postId)
    {
         CommentDto createdComment = commentService.createComment(commentDto,postId);
         return new ResponseEntity<CommentDto>(createdComment, HttpStatus.CREATED);
    }

     @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable int commentId)
    {
          commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>( new ApiResponse("deleted successfully ", true) , HttpStatus.OK);
    }

}
