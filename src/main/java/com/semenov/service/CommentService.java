package com.semenov.service;

public interface CommentService {

    void addComment(Long postId, String text);

    void editComment(Long commentId, String text);

    void deleteComment(Long commentId);
}
