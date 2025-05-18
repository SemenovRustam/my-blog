package com.semenov.repository;

import com.semenov.model.Comment;

import java.util.List;

public interface CommentRepository {

    void save(Long postId, String text);

    void update(Long commentId, String text);

    void deleteById(Long commentId);

    List<Comment> findByPostId(Long postId);
}

