package com.manojb.blog.service;

import com.manojb.blog.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, int postId);

    void deleteComment(int commentId);
}
