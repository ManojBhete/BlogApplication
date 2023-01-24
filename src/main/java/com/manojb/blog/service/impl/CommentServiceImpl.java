package com.manojb.blog.service.impl;

import com.manojb.blog.entity.Comment;
import com.manojb.blog.entity.Post;
import com.manojb.blog.exception.ResourceNotFoundException;
import com.manojb.blog.payloads.CommentDto;
import com.manojb.blog.repository.CommentRepo;
import com.manojb.blog.repository.PostRepo;
import com.manojb.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
     private PostRepo postRepo;

    @Autowired
     private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CommentDto createComment(CommentDto commentDto, int postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(() -> new  ResourceNotFoundException("Post", "post id", postId));

          Comment comment   =  this.modelMapper.map(commentDto, Comment.class);
            comment.setPost(post);

          Comment savedComment = commentRepo.save(comment);

          return this.modelMapper.map(savedComment, CommentDto.class);

    }

    @Override
    public void deleteComment(int commentId) {

        Comment comment= commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id", commentId));

        this.commentRepo.delete(comment);
    }
}
